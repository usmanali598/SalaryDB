package salary.web;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import salary.domain.Salary;
import salary.domain.SalaryRepository;



@Controller
public class SalaryController {
	@Autowired
	private SalaryRepository repository;
	
	// Show all salaries
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	@RequestMapping(value={"/salarylist"})
    public String displaySalary(Model model) {
		// Check loggedin user's username
		// Call repository.findByUsername instead of findAll
		// If user is admin then call findAll
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//String[] auth = getAuthorities();
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();;
		boolean authorized = authorities.contains(new SimpleGrantedAuthority("ADMIN"));
		if (authorized) {
		model.addAttribute("salaries", repository.findAll());
		} else {
		model.addAttribute("salaries", repository.findByUsername("ruman123"));
		}
        model.addAttribute("datetime", new Date());
        return "salarylist";
    }
	
	// RESTful service to get all salariess   ,  it will show all students in JSON form
    @RequestMapping(value="/salaries", method = RequestMethod.GET)
    public @ResponseBody List<Salary> salaryListRest() {	
        return (List<Salary>) repository.findAll();	
    }    

	// RESTful service to get salary by id
    @RequestMapping(value="/salary/{id}", method = RequestMethod.GET)
    public @ResponseBody Salary findSalaryRest(@PathVariable("id") Long salaryId) {	
    	return repository.findOne(salaryId);	
    } 
	
    // Add new salary
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping (value = "/add")
	public String addSalary(Model model){
			model.addAttribute("salary", new Salary());
			
			return "addsalary";
		}
		
		@RequestMapping (value = "/save", method=RequestMethod.POST)
		public String Savesalary(Salary salary){
			repository.save(salary);
			return "redirect:salarylist";
		}
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteSalary(@PathVariable("id") Long salaryid, Model model ){
			repository.delete(salaryid);
			return "redirect:../salarylist";
		}
		//Edit salary record
		@RequestMapping(value = "/edit/{id}")
		public String editSalary(@PathVariable("id") Long salaryId, Model model) {
			model.addAttribute("salary", repository.findOne(salaryId));
			return "editsalary";
		}
	}