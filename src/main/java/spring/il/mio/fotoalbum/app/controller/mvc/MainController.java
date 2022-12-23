package spring.il.mio.fotoalbum.app.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// REDIRECT-ONLY CONTROLLER

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping
	public String goToHome() {
		
		return "redirect:/admin";
	}
}
