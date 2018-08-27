package es.ucm.fdi.iw.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Match;
import es.ucm.fdi.iw.model.MatchQueries;
import es.ucm.fdi.iw.model.Quest;
import es.ucm.fdi.iw.model.QuestQueries;
import es.ucm.fdi.iw.model.RespuestasQuest;
import es.ucm.fdi.iw.model.RespuestasQuestQueries;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserQueries;

@Controller
public class HacerQuestController {
	private static Logger log = Logger.getLogger(HacerQuestController.class);
	
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
			return "/error";
		} else {
			Quest q = QuestQueries.findQuestById(entityManager, Integer.parseInt(idQuest));
			m.addAttribute("quest_actual", q);
			return "hacer_quest";
			
		}
	}
	
	@RequestMapping(value = "/get_quest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String get_quest_url(HttpServletRequest request, HttpServletResponse response) {
		long id = Long.parseLong(request.getParameter("id"));
		ObjectMapper o = new ObjectMapper();
		String json = null;
		
		Quest q = QuestQueries.findQuestById(entityManager, id);
		File f = localData.getFile("quest", q.getUrl());
		try {
			JsonNode node = o.readTree(f);
			json = o.writeValueAsString(node);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*InputStream in = null;
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
	    }*/
	    return json;
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
		return "quests";
	}
}
