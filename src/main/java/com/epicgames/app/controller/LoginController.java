package com.epicgames.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/epicgames")
public class LoginController {
	@GetMapping("/principal")
	public String abrirPrincipal() {
		return "principal";
	}
	
	@GetMapping("/login")
	public String abrirLogin() {
		return "login";
	}
	
	@GetMapping("/crearcuenta")
	public String abrirCrearCuenta() {
		return "crearcuenta";
	}
	
	@GetMapping("/mantenimiento")
	public String abrirMantenimiento() {
		return "mantenimientojuegos";
	}
	
	@GetMapping("/actualizarcuenta") 
	public String abrirActualizarCuenta() {
		return "actualizarcuenta";
	}
	
	@GetMapping("/actualizarjuego")
	public String abrirActualizarJuego() {
		return "actualizarjuegos";
	}
	
	@GetMapping("/canasta")
	public String abrirCanasta() {
		return "canasta";
	}
	
	@GetMapping("/catalogo")
	public String abrirCatalogo() {
		return "catalogo";
	}
	
	@GetMapping("/compra")
	public String abrirCompra() {
		return "compra";
	}

}
