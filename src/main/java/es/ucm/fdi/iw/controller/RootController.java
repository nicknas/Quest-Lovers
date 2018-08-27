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
import java.util.Enumeration;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.controller.ChatSocketHandler;
import es.ucm.fdi.iw.model.Conversacion;
import es.ucm.fdi.iw.model.ConversacionQueries;
import es.ucm.fdi.iw.model.Match;
import es.ucm.fdi.iw.model.MatchQueries;
import es.ucm.fdi.iw.model.MensajeChat;
import es.ucm.fdi.iw.model.Quest;
import es.ucm.fdi.iw.model.QuestQueries;
import es.ucm.fdi.iw.model.Reporte;
import es.ucm.fdi.iw.model.ReporteQueries;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserPhoto;
import es.ucm.fdi.iw.model.UserPhotoQueries;
import es.ucm.fdi.iw.model.UserQueries;
import es.ucm.fdi.iw.model.RespuestasQuest;
import es.ucm.fdi.iw.model.RespuestasQuestQueries;

import org.owasp.encoder.Encode;

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
	
	@GetMapping({"/", "/index"})
	public String root(Model model, Authentication auth) {
		log.info(auth.getName() + " de tipo " + auth.getAuthorities().toArray()[0]);		
		// org.springframework.security.core.userdetails.User
		if (auth.getAuthorities().toArray()[0].toString().contains("ADMIN")) {
			model.addAttribute("users", entityManager
					.createQuery("select u from User u").getResultList());
			return "admin";
		}
		else {
			return "home";
		}

	}
	
}
