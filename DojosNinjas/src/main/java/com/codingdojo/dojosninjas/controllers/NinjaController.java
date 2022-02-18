package com.codingdojo.dojosninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.dojosninjas.models.Dojo;
import com.codingdojo.dojosninjas.models.Ninja;
import com.codingdojo.dojosninjas.services.DojoService;
import com.codingdojo.dojosninjas.services.NinjaService;


@Controller
public class NinjaController {
	private final NinjaService ninjaService;
	private final DojoService dojoService;

	public NinjaController(NinjaService ninjaService, DojoService dojoService){
	        this.ninjaService = ninjaService;
	        this.dojoService = dojoService;
	    }

	@RequestMapping("/ninjas")
	 public String index(Model model, @ModelAttribute("ninja") Ninja ninja) {
        List<Ninja> ninjas = ninjaService.allNinjas();        
        List<Dojo> dojos = dojoService.allDojos();
        model.addAttribute("ninjas", ninjas);
        model.addAttribute("dojos", dojos);
        return "/ninjas/index.jsp";
    }
	

	@RequestMapping(value = "/ninjas", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("dojos", dojoService.allDojos());
			model.addAttribute("ninjas", ninjaService.allNinjas());
			return "/ninjas/index.jsp";
		}
		
		ninjaService.createNinja(ninja);
		return "redirect:/ninjas";
	}
	
	@RequestMapping("/ninjas/new")
	 public String newNinja(@ModelAttribute("ninja") Ninja ninja) {
       return "/ninjas/new.jsp";
   }

	@RequestMapping("/ninjas/{id}")
	public String show(Model model, @PathVariable("id") Long id) {
		Ninja ninja = ninjaService.findNinja(id);
		model.addAttribute("ninja", ninja);
		return "show.jsp";
	}
	
	@GetMapping("/ninjas/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Ninja ninja = ninjaService.findNinja(id);
		model.addAttribute("ninja", ninja);
		return "/ninjas/edit.jsp";	
	}

	@PutMapping("/ninjas/{id}")
	public String update(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result) {
		if (result.hasErrors()) {
			return "/ninjas/edit.jsp";
		} else {
			ninjaService.updateNinja(ninja);
			return "redirect:/ninjas";
		}
	}
	
	@DeleteMapping("/ninjas/{id}")
    public String destroy(@PathVariable("id") Long id) {
		ninjaService.deleteNinja(id);
        return "redirect:/ninjas";
    }
}
