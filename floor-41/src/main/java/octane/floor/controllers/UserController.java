package octane.floor.controllers;

import javax.validation.Valid;

import octane.floor.exceptions.DuplicateUsernameException;
import octane.floor.models.FloorUser;
import octane.floor.services.FloorUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@Autowired
	private FloorUserDetailsService userDetailsService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerUser(Model model)
	{
		FloorUser user = new FloorUser();
		model.addAttribute("user", user);
		return "register";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String executeRegistration(@ModelAttribute("user") @Valid FloorUser floorUser, BindingResult result)
	{
		if(result.hasErrors())
		{
			return "register";
		}
		try
		{
			userDetailsService.createFloorUser(floorUser);
		}
		catch(DuplicateUsernameException e)
		{
			result.addError(new FieldError("user", "username", e.getMessage()));
			return "register";
		}
		return "redirect:/";
	}
	
}
