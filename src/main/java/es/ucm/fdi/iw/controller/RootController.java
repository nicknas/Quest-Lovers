package es.ucm.fdi.iw.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller	
public class RootController {

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("s", "/static");
    }

	@GetMapping({"/", "/index"})
	String root(Model model, Principal principal) {
		
		Logger.getLogger(getClass()).info(principal.getName() + 
				" de tipo " + principal.getClass());
		// org.springframework.security.core.userdetails.User
		return "home";
	}
}
