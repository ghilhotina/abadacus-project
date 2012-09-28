
package com.projeto.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.persistence.vo.Person;
import com.projeto.service.PersonService;

@Controller
@SessionAttributes("PersonForm")
public class PersonController {
	
	@Autowired
	private PersonService PersonService;
	
	@Autowired  
	private MessageSource messageSource;
	
	
	
	@RequestMapping("/showUserRegister")
	public ModelAndView showUserRegister() {
		return new ModelAndView("person_register", "personForm", new Person());
	}
	
	
	@RequestMapping(value="/personRegister", method=RequestMethod.POST)
	public ModelAndView personRegister(@ModelAttribute("personForm")Person person, BindingResult result) {
		userService.insertPerson(person);
		
		return new ModelAndView("login", "loginForm", new Login());
	}


	public PersonService getPersonService() {
		return personService;
	}


	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
}