
package com.asm.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm.Repository.UserRepository;
import com.asm.domain.User;
import com.asm.service.UserService;

import com.mysql.cj.Session;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
@Autowired
private UserRepository userRepository;
@Autowired
private HttpSession session;
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
            RedirectAttributes redirectAttributes) {
		System.out.println(username + password);
		User check = userRepository.findByName(username);
		if (check != null&&check.getPassword().equals(password)) {
			String role = "";
			session.setAttribute("userId", check.getId());//controller á

			// mailService.sendEmail("truongvan6322@gmail.com", "test sendmail", "<h1> hello
			// </h1>");
			if (check.getRole().equals("admin")) {
				role = "admin";
				System.err.println("adminnnnn " + check);
				session.setAttribute("username", check.getName());
				session.setAttribute("role", role);
				return "redirect:/admin/tables-basic";
			} else {
				role = "user";
				session.setAttribute("username", check.getName());
				session.setAttribute("role", role);
				return "redirect:/index";
			}

			// session.setAttribute("username", check.getUsername());
			// session.setAttribute("role", role);
			// if (session.getAttribute("request-url") != null) {
			// return "redirect:" + session.getAttribute("request-url");
			// }
			// return "redirect:/dashboard/home";
		} else if (check == null) {
			
			redirectAttributes.addFlashAttribute("error", "Username or password incorrect");
		}
		return "redirect:/login";
	}
	
	 @GetMapping("logout")
	    public String logout(Model model) {
	        User accountLoginDto = new User();
	        if (session.getAttribute("username") != null) {
	            session.removeAttribute("username");
	            session.removeAttribute("role");
	            session.removeAttribute("mess");
	            return "redirect:/login";
	        }
	        return "redirect:/login";

	/*@PostMapping("/login") public String login(@RequestParam("username") String
  username, @RequestParam("password") String password, Model model) { 

		User user = userService.getUserByName(username);
		if(user != null && user.getPassword().equals(password)) { 

	  return "redirect:/index";
	  
  } else { 
	  model.addAttribute("errorMessage",
  "Invalid username or password"); return "login"; } }*/
	 }
}