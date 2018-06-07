package es.ucm.fdi.iw.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.controller.ChatSocketHandler;
import es.ucm.fdi.iw.model.Conversacion;
import es.ucm.fdi.iw.model.ConversacionQueries;
import es.ucm.fdi.iw.model.Match;
import es.ucm.fdi.iw.model.MatchQueries;
import es.ucm.fdi.iw.model.Quest;
import es.ucm.fdi.iw.model.QuestQueries;
import es.ucm.fdi.iw.model.Reporte;
import es.ucm.fdi.iw.model.ReporteQueries;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserQueries;
import es.ucm.fdi.iw.model.RespuestasQuest;
import es.ucm.fdi.iw.model.RespuestasQuestQueries;

@Controller	
public class RootController {

	private static Logger log = Logger.getLogger(RootController.class);
	
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
	
	@Transactional
	@GetMapping("/chat")
	@RequestMapping(value = "/chat")
	public String chat(Model model, HttpServletRequest request, Authentication authentication) {
		/*model.addAttribute("endpoint", request.getRequestURL().toString()
				.replaceFirst("[^:]*", "ws")
				.replace("chat", "chatsocket"));
		model.addAttribute("endpoint", id);
			model.addAttribute("endpoint", authentication.getPrincipal().toString()
					.replaceFirst("[^:]*", "ws")
					.replace("chat", "chatsocket"));
					*/
		int user1 = Integer.parseInt((request.getParameter("u1")));		
		int user2 = Integer.parseInt((request.getParameter("u2")));
		
		
		if(!ConversacionQueries.existeConversacion(entityManager, user1,user2)) {
			User u1 = UserQueries.findWithId(entityManager, user1);
			User u2 = UserQueries.findWithId(entityManager, user2);
						
			ConversacionQueries.crearConversacion(entityManager, u1, u2);
		}	
		
		Conversacion c = ConversacionQueries.findConversacion(entityManager, user1, user2);		

		model.addAttribute("conversacion", c);
		return "chat";
		
	}	
	
	@GetMapping({"/", "/index"})
	public String root(Model model, Principal principal) {
		log.info(principal.getName() + " de tipo " + principal.getClass());		
		// org.springframework.security.core.userdetails.User
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {	
		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	@GetMapping("/upload")
	public String upload() {
		return "upload";
	}
		
	@GetMapping("/quest")
	public String quest(Model m, Authentication authentication) {
		List<Quest> listaQuests = QuestQueries.findAllQuests(entityManager);
		m.addAttribute("all_quests", listaQuests);
		User u = UserQueries.findWithName(entityManager, authentication.getName());
		
		return "quest";
	}
	
	@RequestMapping(value = "/get_quest_url", method = RequestMethod.POST)
	@ResponseBody
	public String get_quest_url(HttpServletRequest request) {
		long id = Long.parseLong(request.getParameter("id"));
		Quest q = QuestQueries.findQuestById(entityManager, id);
		return q.getUrl();
	}
	
	@GetMapping("/matches")
	public String matches(Model m, Authentication authentication) {
		User u = UserQueries.findWithName(entityManager, authentication.getName());
		Set<User> lista_matches = MatchQueries.findMatchesUser(entityManager,u.getId());
		m.addAttribute("lista_matches", lista_matches);
		m.addAttribute("user_actual", u);
		return "matches";
	}
	
	@GetMapping("/user")
	public String user(Model m, Authentication authentication) {	
			User u = UserQueries.findWithName(entityManager, authentication.getName());
			m.addAttribute("user", u);
		return "user";
	}
	
	@Transactional
	@GetMapping("/hacer_quest")
	public String hacer_quest(Model m, Authentication authentication, HttpServletRequest request) {
		User u = UserQueries.findWithName(entityManager, authentication.getName());
		m.addAttribute("user_actual", u);
		String idQuest=request.getParameter("id");		
		
		List<RespuestasQuest> listaQuestsUser = RespuestasQuestQueries.findQuestsByUser(entityManager, u.getId());
		List<Integer> lista = new ArrayList<>();
		if(listaQuestsUser.size()!=0) {
			for(int i =0;i< listaQuestsUser.size(); i++) {
				lista.add(listaQuestsUser.get(i).getIdQuest());
			}
		}
		if(lista.contains(Integer.parseInt(idQuest))) {
			return "Ya has respondido esta quest";
		} else {
			Quest q = QuestQueries.findQuestById(entityManager, Integer.parseInt(idQuest));
			q.setUrl("/static/jsons/" + "esqueleto" + q.getId() + ".json");
			entityManager.persist(q);
			entityManager.flush();
			m.addAttribute("quest_actual", q);
			return "hacer_quest";
			
		}
	}

	@RequestMapping(value = "/match", method = RequestMethod.GET)
	@Transactional
	public String match(
			@RequestParam int id, Model m, Authentication authentication
			) {
		User u = UserQueries.findWithId(entityManager, id);
		User user_actual = UserQueries.findWithName(entityManager, authentication.getName());
		
		Match match = MatchQueries.findMatchId(entityManager, u.getId(), user_actual.getId());
		
		m.addAttribute("match", match);
		m.addAttribute("user", u);
		m.addAttribute("user_actual", user_actual);
		
		return "match";
	}
	@GetMapping("/reportes")
	public String reportes(Model m) {
		List<Reporte> listaNuevos = ReporteQueries.findNoVistos(entityManager);
		m.addAttribute("reportes_nuevos", listaNuevos);
		List<Reporte> listaVistos = ReporteQueries.findVistos(entityManager);
		m.addAttribute("reportes_vistos", listaVistos);
		return "reportes";
  }
	@GetMapping("/messages") 
	public String messages(){
		return "messages";
	}
	
	@GetMapping("/editores") 
	@Transactional
	public String editores(Model m){
		List<User> lista = UserQueries.findEditores(entityManager);
		m.addAttribute("editores", lista);
		return "editores";
	}
	
	@GetMapping("/registro") 
	public String registro(){
		return "registro";
	}
	@GetMapping("/quest_admin") 
	public String quest_admin(){
		return "quest_admin";
	}
	@RequestMapping(value = "/addUser", method=RequestMethod.POST)
	@Transactional
	public String addUser(
			@RequestParam String user, 
			@RequestParam String password,
			@RequestParam String password2,
			@RequestParam String email,
			@RequestParam int edad,
			@RequestParam String ciudad,
			@RequestParam String resumen,
		
			@RequestParam(required=false) String isAdmin, Model m) {
		User u = new User();
		if(!password.equals(password2)) {
			return "error";
		} else {
			u.setLogin(user);
			u.setPassword(passwordEncoder.encode(password));
			u.setCiudad(ciudad);
			u.setEnabled((byte) 1);
			u.setEdad(edad);
			u.setEmail(email);
			u.setResumen(resumen);
			u.setRoles("USER");
			
			entityManager.persist(u);
			
			entityManager.flush();
			
			m.addAttribute("users", entityManager
					.createQuery("select u from User u").getResultList());
			return "/login";
		}
	}
	@Transactional
	@GetMapping("/terminar_quest")
	public String terminar_quest(Model m, HttpServletRequest request) {
		String id_quest =request.getParameter("id_quest");
		String id_user = request.getParameter("id_user");
		String resultado = request.getParameter("resultado");
		RespuestasQuest r = new RespuestasQuest();
		r.setIdUser(Integer.parseInt(id_user));
		r.setIdQuest(Integer.parseInt(id_quest));
		r.setResultado(resultado);
		entityManager.persist(r);
		List<RespuestasQuest> listaMatches = RespuestasQuestQueries.findQuestsByRespuesta(entityManager, resultado);
		if(listaMatches!=null) {
			for(int i = 0; i<listaMatches.size();i++) {
				Match a = new Match();
				if(listaMatches.get(i).getIdUser()!= Integer.parseInt(id_user)) {
					if(!MatchQueries.existeMatch(entityManager, Integer.parseInt(id_user), listaMatches.get(i).getIdUser())) {
						a.setIdUser1(Integer.parseInt(id_user));
						a.setIdUser2(listaMatches.get(i).getIdUser());
						entityManager.persist(a);
					}
				}
			}
		}
		return "quest";
	}
	
	@Transactional
	@GetMapping("/confirmar_reporte")
	public String confirmar_reporte(Model m, HttpServletRequest request) {
		long id_reporte =Integer.parseInt(request.getParameter("id"));
		int baneado = Integer.parseInt(request.getParameter("baneado"));
		Reporte r = ReporteQueries.findReporteById(entityManager, id_reporte);
		User u = r.getReportado();
		
		if(baneado == 1) {
			r.setBaneado((byte) 1); 

			u.setEnabled((byte) 0);
		}else {
			r.setBaneado((byte) 0);
		}
		r.setVisto((byte) 1);
		entityManager.merge(r);
		entityManager.merge(u);
		
		
		
		return "redirect:/reportes";
	}
	@Transactional
	@GetMapping("/enviar_mensaje")
	public String enviar_mensaje(Model m, HttpServletRequest request) {
		String id_conversacion =request.getParameter("id");
		String texto = request.getParameter("texto1");
		Conversacion c = ConversacionQueries.findConversacionById(entityManager, Integer.parseInt((id_conversacion)));
		c.setTexto(texto);
		entityManager.merge(c);
		
		
		return "/chat";
	}
	
	@Transactional
	@GetMapping("/reportar")
	@ResponseBody
	public String reportar(Model m, HttpServletRequest request) {
		String id_reportador =request.getParameter("id1");
		String id_reportado = request.getParameter("id2");
		String motivo = request.getParameter("m");
		User reportador = UserQueries.findWithId(entityManager, Integer.parseInt(id_reportador));
		User reportado = UserQueries.findWithId(entityManager, Integer.parseInt(id_reportado));
		Reporte reporte = new Reporte();
		reporte.setComentario(motivo);
		reporte.setReportado(reportado);
		reporte.setReportador(reportador);
		reporte.setVisto((byte) 0);
		reporte.setBaneado((byte) 0);
		entityManager.persist(reporte);
		
		return "/";
	}
	
	@Transactional
	@GetMapping("/test")
	@ResponseBody
	public String testBd() {
		User u = new User();
		u.setLogin("admin");
		u.setPassword(passwordEncoder.encode("admin"));
		u.setRoles("ADMIN");
		u.setCiudad("Madrid");
		u.setEnabled((byte) 1);
		u.setEdad(33);
		u.setEmail("admin@admin.com");
		u.setResumen("resumen_del_administrador");
		entityManager.persist(u);
		return "/login";
	}
	
	
	@RequestMapping(value="/photo/{id}", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("photo") MultipartFile photo,
    		@PathVariable("id") String id){
        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(
                        		new FileOutputStream(localData.getFile("user", id)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + id + 
                		" into " + localData.getFile("user", id).getAbsolutePath() + "!";
            } catch (Exception e) {
                return "You failed to upload " + id + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload a photo for " + id + " because the file was empty.";
        }
	}
	
	@RequestMapping(value="/photo/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void userPhoto(@PathVariable("id") String id, 
			HttpServletResponse response) {
	    File f = localData.getFile("user", id);
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
		User u = new User();
			u.setLogin(user);
			//u.setPassword(passwordEncoder.encode(password));
			u.setCiudad(ciudad);
			u.setEnabled((byte) 1);
			u.setEdad(edad);
			u.setEmail(email);
			u.setResumen(resumen);
			
			entityManager.merge(u);
			
			entityManager.flush();
			
			m.addAttribute("users", entityManager
					.createQuery("select u from User u").getResultList());
			return "/login";
		
	}
	
	@GetMapping("/subir_historia")
	public String subirHistoria(){
		return "subir_historia";
	}
	
	@Transactional
	@RequestMapping(value = "/add_quest", method=RequestMethod.POST)
	public String addQuest(HttpServletRequest request, 
			@RequestParam MultipartFile historia, 
			@RequestParam String nombre_historia, 
			@RequestParam String descripcion){
		if (!historia.isEmpty() && historia.getContentType().equals("application/json")) {
			Quest q = new Quest();
			q.setTitulo(nombre_historia);
			q.setDescripcion(descripcion);
			List<Quest> queryList = QuestQueries.findAllQuests(entityManager);
			q.setId(queryList.get(queryList.size()-1).getId() + 1);
			q.setUrl("/static/jsons/" + "esqueleto" + q.getId() + ".json");
			entityManager.merge(q);
			entityManager.flush();
			Path filePath = Paths.get(request.getSession().getServletContext().getRealPath("/"));
			File file = new File (filePath.getParent().toString() + "/resources", q.getUrl());
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				 byte[] bytes = historia.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(
	                        		new FileOutputStream(file));
	                stream.write(bytes);
	                stream.close();
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			log.info("NO OK");
			log.info(historia.getContentType());
		}
		return "subir_historia";
	}
	
}
