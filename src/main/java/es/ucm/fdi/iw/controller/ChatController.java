package es.ucm.fdi.iw.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Conversacion;
import es.ucm.fdi.iw.model.ConversacionQueries;
import es.ucm.fdi.iw.model.MensajeChat;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserQueries;

@Controller
public class ChatController {
	private static Logger log = Logger.getLogger(ChatController.class);
	
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
		User user_actual = UserQueries.findWithName(entityManager, authentication.getName());
		
		if(!ConversacionQueries.existeConversacion(entityManager, user1,user2)&&!ConversacionQueries.existeConversacion(entityManager, user2,user1)) {
			User u1 = UserQueries.findWithId(entityManager, user1);
			User u2 = UserQueries.findWithId(entityManager, user2);
						
			ConversacionQueries.crearConversacion(entityManager, u1, u2);
		}	
		
		Conversacion c = ConversacionQueries.findConversacion(entityManager, user1, user2);		
		if(c==null) {
			c = ConversacionQueries.findConversacion(entityManager, user2, user1);
		}
		List<MensajeChat> lista = ConversacionQueries.findMensajes(entityManager, c);
		
		model.addAttribute("lista_mensajes", lista);
		model.addAttribute("conversacion", c);
		model.addAttribute("user_actual", user_actual);
		return "chat";
		
	}
	

	@RequestMapping(value="/enviar_mensaje", method=RequestMethod.POST)
	@Transactional
	public RedirectView enviar_mensaje(Model m,
			@RequestParam String id,
			@RequestParam String texto1,
			@RequestParam String user) {
		id = scapedparameter(id);
		texto1 = scapedparameter(texto1);
		user = scapedparameter(user);
		MensajeChat mensaje = new MensajeChat();
		mensaje.setSender(UserQueries.findWithId(entityManager, Integer.parseInt(user)));
		mensaje.setTexto(texto1);
		
		Conversacion c = ConversacionQueries.findConversacionById(entityManager, Integer.parseInt((id)));
		//List<MensajeChat> lista = ConversacionQueries.findMensajes(entityManager, c);
		//c.setTexto(texto1);
		c.setMensajes(mensaje);
		
		
		entityManager.persist(mensaje);
		entityManager.merge(c);
		int es = c.getUser1().getId();
		int es2 = c.getUser2().getId();
		String aux = "/chat?u1="+es+"&u2="+es2;
		/*aux.concat(Integer.toString(es));
		aux.concat("&u2=");
		aux.concat(Integer.toString(es2));*/
		//return "/chat?u1="+c.getUser1().getId()+"&u2="+c.getUser2().getId();
		return new RedirectView(aux);
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
