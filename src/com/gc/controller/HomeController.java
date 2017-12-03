package com.gc.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gc.dto.*;

// Step 1 import sql
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/*
 * author: Jenna Price
 *
 */

@Controller
@SessionAttributes({"authenticated", "customername"})
public class HomeController {
	private JDCBImpl database = new JDCBImpl();
	
	// this is an example showing how to take data from a form and just add it to a page
	@RequestMapping("/")	public String homepage(HttpSession hSession) {
		hSession.setAttribute("authenticated", false);
		return "index";
	}
	@RequestMapping("/index")	public String returnToHomepage(HttpSession hSession) {
		hSession.setAttribute("authenticated", false);
		return "index";
	}
		
	@RequestMapping("/register")
	public String registration() {
		return "register";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginCustomer(@RequestParam("emailaddress") String emailAddress,
			@RequestParam("password") String entered_password, Model model, HttpSession hSession, @ModelAttribute("authenticated") boolean authenticated )throws ClassNotFoundException, SQLException {
		UserDto user = new UserDto(emailAddress);
		user = (UserDto) database.searchByEmail(user);
		
		if(user.getFullName() == null) {
			String warning = "<p class='warning'> You do not have an account associated with this email address."+
		"Please create an account below or <a href='index.html> click here  </a> to try a different account. </p>";
			return new ModelAndView("register", "noAccountMessage", warning);
		}
		boolean passwordMatch = BCrypt.checkpw(entered_password, user.getPw_hash());
		if(!passwordMatch) {
			return new ModelAndView("index", "update", "<p> Please try again, your password didn't match. </p>");		
		}
		user.setAuthenticated(true);
		hSession.setAttribute("authenticated", true);
		return new ModelAndView("welcome", "customername", user.getFullName());
	}
	

	@RequestMapping(value = "/submitform", method = RequestMethod.POST)
	public ModelAndView registerCustomer(@RequestParam("customername") String customerName, @RequestParam("emailaddress") String emailAddress,
			@RequestParam("password") String password, HttpSession hSession) throws ClassNotFoundException, SQLException {
		UserDto user = new UserDto(customerName, emailAddress, password, false);
		
		database.insert(user);
		hSession.setAttribute("authenticated", true);
		
		return new ModelAndView("welcome", "customername", customerName);
	}
	
	@RequestMapping("/welcome")
	public ModelAndView welcomePage(HttpSession hSession, @ModelAttribute("authenticated") boolean authenticated, Model model) throws ClassNotFoundException, SQLException {
		
		
		/*if (!authenticated) {
			return new ModelAndView("index", "update", "<p class=\"warning\">Your session has expired, please login again.</p>");
		}*/
		
		List<DBRecordDto> productList = database.listAll();
		ItemDto product = (ItemDto) productList.get(0);
		System.out.println("Product List " + product.getName());
		return new ModelAndView("welcome", "table", productList);
	}
	
	@RequestMapping("/purchase")
	public ModelAndView placeItemInCart(HttpSession hSession, @ModelAttribute("authenticated") boolean authenticated, @RequestParam("name") String name) {
	return new ModelAndView("welcome", "update", "<p> We have added " + name + " to your cart.</p>");
	}
	
	
	

}