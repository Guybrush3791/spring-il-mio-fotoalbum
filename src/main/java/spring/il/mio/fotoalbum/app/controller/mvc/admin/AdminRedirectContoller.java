package spring.il.mio.fotoalbum.app.controller.mvc.admin;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// REDIRECT-ONLY CONTROLLER

@Controller
@RequestMapping("/admin")
public class AdminRedirectContoller {

	@GetMapping
	public String goHome() {
		
		return "redirect:/admin/photo";
	}
}
