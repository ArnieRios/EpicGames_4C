package com.epicgames.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epicgames.app.model.UsuarioDTO;
import com.epicgames.app.repository.IUsuarioRepository;

@Controller
@RequestMapping("/epicgames")
public class LoginController { 
	
	@GetMapping("/login")
	public String abrirLogin() {
		return "login";
	}
	
	@Autowired
	private IUsuarioRepository repoUsu;
	@PostMapping("/login")
	public String validarAcceso(
			@RequestParam("email") String usuario,
			@RequestParam("clave") String clave,
			Model model) {
		UsuarioDTO u = repoUsu.findByEmailAndClave(usuario, clave);
		System.out.println("Buscando a: " + usuario + " con clave: " + clave);
		if (u != null) {
			System.out.println("DEBUG: Usuario encontrado: " + u.getNombre());
			model.addAttribute("mensaje", "Bienvenido: " + u.getNombre());
			model.addAttribute("cssmensaje", "alert alert-success");
			return "principal";
		} else {
			System.out.println("DEBUG: No se encontró el usuario en la base de datos.");
			model.addAttribute("mensaje", "Usuario o clave incorrecto");
			model.addAttribute("cssmensaje", "alert alert-danger");
			return "login";
		}		
	}
	
			
	
	
	@GetMapping("/crearcuenta")
	public String abrirCrearCuenta() {
		return "crearcuenta";
	}
		
	@GetMapping("/actualizarcuenta") 
	public String abrirActualizarCuenta() {
		return "actualizarcuenta";
	}
	

}
