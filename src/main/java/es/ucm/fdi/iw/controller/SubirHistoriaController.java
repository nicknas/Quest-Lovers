package es.ucm.fdi.iw.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Quest;
import es.ucm.fdi.iw.model.QuestQueries;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserQueries;

@Controller
public class SubirHistoriaController {
	private static Logger log = Logger.getLogger(SubirHistoriaController.class);
	
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
	
	@GetMapping("/subir_historia")
	public String subirHistoria(Model m){
		return "subir_historia";
	}
	
	@Transactional
	@RequestMapping(value = "/add_quest", method=RequestMethod.POST)
	@ResponseBody
	public String addQuest(@RequestBody String data, Authentication auth){
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
			String titulo = null;
			titulo = nodeJson.at("/quest/titulo").asText();
			String descripcion = null;
			descripcion = nodeJson.at("/quest/descripcion").asText();
			q.setTitulo(titulo);
			q.setDescripcion(descripcion);	
			entityManager.persist(q);
			entityManager.flush();
			q.setUrl("esqueleto" + q.getId() + ".json");	
			entityManager.merge(q);
			entityManager.flush();
			ObjectNode objectNode = (ObjectNode) nodeJson;
			objectNode.with("quest").put("id", q.getId());
			try {
				data = objectMapper.writeValueAsString(objectNode);
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			User u = UserQueries.findWithName(entityManager, auth.getName());
			List<Quest> quests = QuestQueries.findQuestsByEditorName(entityManager, auth.getName());
			if (quests == null) {
				quests = new ArrayList<Quest>();
			}
			quests.add(q);
			u.setQuestsEditor(quests);
			entityManager.merge(u);
			entityManager.flush();
			q.setEditor_fk(u);
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
