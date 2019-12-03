package com.demo.springboot.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.springboot.app.models.dao.IClienteDao;
import com.demo.springboot.app.models.entity.Cliente;

@Controller
public class ClienteController {
	
	@Autowired
	private IClienteDao clienteDao;
	
	@RequestMapping(value="/listar", method =RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Formulario de cliente");
		return "form";
		
	}
	
	@RequestMapping(value="/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "form";
			
		}
		
		clienteDao.save(cliente);
		return "redirect:listar";
	}

}
