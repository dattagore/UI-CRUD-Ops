package com.crud.client.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crud.client.ui.model.Product;
import com.crud.client.ui.service.CrudUIService;

@Controller
public class CrudUIController {
	
	 @Autowired
	    private CrudUIService service;

	    @GetMapping("/")
	    public String viewHomePage(Model model) {
	        Object listProducts = service.listAll();
	        model.addAttribute("listProducts", listProducts);
	        return "index";
	    }
	    
	    @GetMapping("/new")
	    public String add(Model model) {
	        model.addAttribute("product", new Product());
	        return "new";
	    }

	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveProduct(@ModelAttribute("product") Product product) {
	        service.save(product);
	        return "redirect:/";
	    }

	    @RequestMapping(value="/edit/{productId}")
	    public ModelAndView showEditProductPage(@PathVariable(name = "productId") long productId) {
	        ModelAndView mav = new ModelAndView("edit");
	        Object prod = service.get(productId);
	        mav.addObject("product", prod);
	        return mav;
	        
	    }
	    
	    @RequestMapping(value="/edit", method = RequestMethod.POST)
	    public String edit(@ModelAttribute("product") Product product) {
	        service.edit(product);
	        return "redirect:/";
	        
	    }
	    @RequestMapping("/delete/{productId}")
	    public String deleteProduct(@PathVariable(name = "productId") long productId) {
	        service.delete(productId);
	        return "redirect:/";
	    }
}