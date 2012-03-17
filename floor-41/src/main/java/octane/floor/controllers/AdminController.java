package octane.floor.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_Admin2')")
	public String index()
	{
		return "admin/index";
	}

}
