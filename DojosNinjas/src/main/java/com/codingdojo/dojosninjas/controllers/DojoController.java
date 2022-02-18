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
import com.codingdojo.dojosninjas.services.DojoService;


@Controller
public class DojoController {
	private final DojoService dojoService;

	public DojoController(DojoService dojoService){
	        this.dojoService = dojoService;
	    }

	@RequestMapping("/dojos")
	 public String index(Model model, @ModelAttribute("dojo") Dojo dojo) {
        List<Dojo> dojos = dojoService.allDojos();
        model.addAttribute("dojos", dojos);
        return "/dojos/index.jsp";
    }
	

	@RequestMapping(value = "/dojos", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("dojos", dojoService.allDojos());
			return "/dojos/index.jsp";
		}
		
		dojoService.createDojo(dojo);
		return "redirect:/dojos";
	}
	
	@RequestMapping("/dojos/new")
	 public String newDojo(@ModelAttribute("dojo") Dojo dojo) {
       return "/dojos/new.jsp";
   }

	@RequestMapping("/dojos/{id}")
	public String show(Model model, @PathVariable("id") Long id) {
		Dojo dojo = dojoService.findDojo(id);
		model.addAttribute("dojo", dojo);
		return "show.jsp";
	}
	
	@GetMapping("/dojos/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Dojo dojo = dojoService.findDojo(id);
		model.addAttribute("dojo", dojo);
		return "/dojos/edit.jsp";	
	}

	@PutMapping("/dojos/{id}")
	public String update(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
		if (result.hasErrors()) {
			return "/dojos/edit.jsp";
		} else {
			dojoService.updateDojo(dojo);
			return "redirect:/dojos";
		}
	}
	
	@DeleteMapping("/dojos/{id}")
    public String destroy(@PathVariable("id") Long id) {
		dojoService.deleteDojo(id);
        return "redirect:/dojos";
    }
}
