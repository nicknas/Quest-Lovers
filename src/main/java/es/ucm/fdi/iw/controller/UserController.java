package es.ucm.fdi.iw.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserPhoto;
import es.ucm.fdi.iw.model.UserQueries;

@Controller
public class UserController {
	private static Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private LocalData localData;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("s", "/static");
    }
	
	@GetMapping("/user")
	public String user(Model m, Authentication authentication, HttpServletRequest request) {	
		User u = UserQueries.findWithName(entityManager, authentication.getName());
		m.addAttribute("user", u);
		m.addAttribute("usuario", u.getLogin());
		boolean photoDeleted = Boolean.parseBoolean(request.getParameter("deletePhoto"));
		String fileUploaded = request.getParameter("fileUploaded");
		if (fileUploaded != null) {
			boolean fileUploadedBoolean = Boolean.parseBoolean(fileUploaded);
			m.addAttribute("fileUploaded", fileUploadedBoolean);
		}
		
		boolean fileEmpty = Boolean.parseBoolean(request.getParameter("fileEmpty"));
		m.addAttribute("fileEmpty", fileEmpty);
		m.addAttribute("photoDeleted", photoDeleted);
		
		return "user";
	}
	
	@Transactional
	@RequestMapping(value="/photo/{id}", method=RequestMethod.POST)
    public String handleFileUpload(@RequestParam("photo") MultipartFile photo,
    		@PathVariable("id") String id){
        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                User u = UserQueries.findWithName(entityManager, id);
                List<UserPhoto> userPhotos = u.getListPhotos();
                if (userPhotos == null) {
                	userPhotos = new ArrayList<UserPhoto>();
                }
                UserPhoto userPhoto = new UserPhoto();
                userPhoto.setPath(id + "-" + Integer.toString(userPhotos.size() + 1));
                userPhoto.setUserPhoto(u);
                entityManager.persist(userPhoto);
                entityManager.flush();
                userPhotos.add(userPhoto);
                u.setListPhotos(userPhotos);
                BufferedOutputStream stream =
                        new BufferedOutputStream(
                        		new FileOutputStream(localData.getFile("user", userPhoto.getPath() )));
                stream.write(bytes);
                stream.close();
                entityManager.merge(u);
                entityManager.flush();
                return "redirect:/user?fileUploaded=true";
            } catch (Exception e) {
                return "redirect:/user?fileUploaded=false";
            }
        } else {
            return "redirect:/user?fileEmpty=true";
        }
	}
	
	@RequestMapping(value="/photo/{id}/{photo_id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void userPhoto(@PathVariable("id") String id, @PathVariable("photo_id") int photo_id,
			HttpServletResponse response) {
	    File f = localData.getFile("user", id + "-" + photo_id);
	    InputStream in = null;
	    try {
		    if (f.exists()) {
		    	in = new BufferedInputStream(new FileInputStream(f));
		    } else {
		    	in = new BufferedInputStream(
		    			this.getClass().getClassLoader().getResourceAsStream("unknown-user.jpg"));
		    }
	    	FileCopyUtils.copy(in, response.getOutputStream());
	    } catch (IOException ioe) {
	    	log.info("Error retrieving file: " + f + " -- " + ioe.getMessage());
	    }
	}
	
	@Transactional
	@RequestMapping(value="/delete_photo", method = RequestMethod.POST)
	public String deletePhoto (HttpServletRequest request, Authentication auth) {
		int id = Integer.parseInt(request.getParameter("id_photo"));
		User u = UserQueries.findWithName(entityManager, auth.getName());
		List<UserPhoto> listPhotos = u.getListPhotos();
		File photoToDelete = localData.getFile("user", u.getLogin() + "-" + Integer.toString(id));
		photoToDelete.delete();
		for (int i = 1; i <= listPhotos.size(); i++) {
			if (i > id) {
				File photoFile = localData.getFile("user", u.getLogin() + "-" + Integer.toString(i));
				photoFile.renameTo(localData.getFile("user", u.getLogin() + "-" + Integer.toString(i - 1)));
			}
		}
		UserPhoto photo = listPhotos.get(id-1);
		entityManager.remove(photo);
		entityManager.flush();
		return "redirect:/user?deletePhoto=true";
	}
	
	/**
	 * actualiza un usuario con cambios en su informacion
	 * @param user
	 * @param password
	 * @param password2
	 * @param email
	 * @param edad
	 * @param ciudad
	 * @param resumen
	 * @param isAdmin
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/editUser", method=RequestMethod.POST)
	@Transactional
	public String editUser(
			@RequestParam String user, 
			//@RequestParam String password,
			//@RequestParam String password2,
			@RequestParam String email,
			@RequestParam int edad,
			@RequestParam String ciudad,
			@RequestParam String resumen,
		
			@RequestParam(required=false) String isAdmin, Model m) {
			
		User u = UserQueries.findWithName(entityManager, user);
		if( u!= null) {
			u.setLogin(scapedparameter(user));
			u.setCiudad(scapedparameter(ciudad));
			u.setEdad(edad);
			u.setEmail(scapedparameter(email));
			u.setResumen(scapedparameter(resumen));

			entityManager.merge(u);

			entityManager.flush();

			m.addAttribute("user", u);
			return "/user";	
		} else {
			return "Error al buscar usuario";
		}
		
		
	}
	
	String scapedparameter(String parameterToScape) {
		String e = Encode.forCDATA(parameterToScape);
		e = Encode.forHtml(e);
		e = Encode.forJavaScript(e);
		e = Encode.forJava(e);
		e = Encode.forCssString(e);
		
		return e;
		
	}
}
