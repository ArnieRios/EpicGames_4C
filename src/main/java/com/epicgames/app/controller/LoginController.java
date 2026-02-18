package com.epicgames.app.controller;

import java.time.LocalDate;

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
	@GetMapping("/principal")
	public String abrirInicio() {
		return "principal";
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
	
	
	@Autowired
	private IUsuarioRepository repoUsua;
	@PostMapping("/crearcuenta") 
	public String abrirCrearCuenta(
				@RequestParam("nombre") String Nom,
				@RequestParam("apellido") String Ape,
				@RequestParam("mail") String correo,
				@RequestParam("clave") String contra,
				@RequestParam("fecreg") String fecha,
				Model model) {
		//Crear la nueva instancia
		UsuarioDTO nuevoUsuario = new UsuarioDTO();
		
		
		//Settear los nuevos valores
		nuevoUsuario.setNombre(Nom);
		nuevoUsuario.setApellido(Ape);
		nuevoUsuario.setEmail(correo);
		nuevoUsuario.setClave(contra);
		LocalDate fechaConvertida = LocalDate.parse(fecha);
		nuevoUsuario.setFech_Reg(fechaConvertida);
		
		UsuarioDTO u = repoUsua.save(nuevoUsuario);
		
		if (u != null) {
			model.addAttribute("mensaje", "Registro exitoso: " + u.getNombre() + " puedes ingresar desde el login");
			model.addAttribute("cssmensaje", "alert alert-success");
			return "login";
		} else {
			model.addAttribute("mensaje", "Error al registrar");
			model.addAttribute("cssmensaje", "alert alert-danger");
			return "crearcuenta";
		}
	}
		
		
		
		
	}
	


