package spring.il.mio.fotoalbum.app.controller.mvc.guest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class GuestController {

	@GetMapping("/home")
	public String getHome(Authentication authentication) {
		
		return "guest/home";
	}
}
