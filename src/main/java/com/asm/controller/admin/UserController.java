package com.asm.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.BichNhungJava5Application;
import com.asm.Repository.UserRepository;
import com.asm.domain.User;
import com.asm.service.UserService;

import ch.qos.logback.core.joran.action.Action;



@Controller
public class UserController {
	 @Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/admin/tables-basic")
	public String index(Model model) {
		List<User> items = userRepository.findAll();
		model.addAttribute("items", items);
		model.addAttribute("user", new User());
		return "admin/tables-basic";
	}
	
	 @PostMapping("/users")
	    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		 if (bindingResult.hasErrors()) {
	            model.addAttribute("message", "Lỗi khi cập nhật sản phẩm");
	            return "/admin/tables-basic";
	        }else {
	        	userService.addUser(user);
		        return "redirect:/admin/tables-basic"; 
			}

		    // Điều hướng tới trang thành công
	    }
	 
	 @GetMapping("/admin/tables-basic/delete/{id}")
	 public String delete(@PathVariable("id") int id) {
	     try {
	         userRepository.deleteById(id);
	     } catch (Exception e) {
	         System.out.println("Lỗi " + e.getMessage());
	         
	         return "error";
	     }

	     return "redirect:/admin/tables-basic";
	 }
	 
	 @GetMapping("/admin/tables-basic/edit/{id}")
		public String edit(Model model, @PathVariable("id") int id) {
			User item = userRepository.findById(id).orElse(null);
			model.addAttribute("user", item);
			List<User> items = userRepository.findAll();
			model.addAttribute("items", items);
			return "admin/tables-basic";
		}
	 @PostMapping("/admin/tables-basic/update")
		public String update(@Valid User item) {
			userRepository.save(item);
			return "redirect:/admin/tables-basic";
		}
}
