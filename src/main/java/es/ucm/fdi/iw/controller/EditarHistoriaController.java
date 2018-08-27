package es.ucm.fdi.iw.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Quest;
import es.ucm.fdi.iw.model.QuestQueries;

@Controller
public class EditarHistoriaController {
	private static Logger log = Logger.getLogger(EditarHistoriaController.class);
	
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
	
	@RequestMapping(value = "/editar_quest", method = RequestMethod.POST)
	public String editarQuest(HttpServletRequest request, Model m) {
		long id = Long.parseLong(request.getParameter("id_quest"));
		Quest q = QuestQueries.findQuestById(entityManager, id);
		m.addAttribute("titulo", q.getTitulo());
		m.addAttribute("descripcion", q.getDescripcion());
		m.addAttribute("id", q.getId());
		return "editar_historia";
	}
	
	@Transactional
	@RequestMapping(value="/update_quest", method=RequestMethod.POST)
	@ResponseBody
	public String updateQuest(@RequestBody String data, Authentication auth) {
		if (!data.isEmpty()) {
			Quest q = new Quest();
			JsonNode nodeJson = null;
			ObjectMapper objectMapper = new ObjectMapper(); 
			try {
				nodeJson = objectMapper.readTree(data);
			} catch (JsonProcessingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			q = QuestQueries.findQuestById(entityManager, nodeJson.at("/quest/id").asLong());
			String titulo = null;
			titulo = nodeJson.at("/quest/titulo").asText();
			String descripcion = null;
			descripcion = nodeJson.at("/quest/descripcion").asText();
			q.setTitulo(titulo);
			q.setDescripcion(descripcion);
			entityManager.merge(q);
			entityManager.flush();
			File file = localData.getFile("quest", q.getUrl());
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				 byte[] bytes = data.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(
	                        		new FileOutputStream(file));
	                stream.write(bytes);
	                stream.close();
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "ok";
			
		}
		else {
			return "fail";
		}
	}
}
