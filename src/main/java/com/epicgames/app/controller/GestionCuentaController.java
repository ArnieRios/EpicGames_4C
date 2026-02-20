package com.epicgames.app.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epicgames.app.model.UsuarioDTO;
import com.epicgames.app.repository.IUsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/epicgames")
public class GestionCuentaController { 
	
	// --> Cargar Página Principal
	@GetMapping("/principal")
	//Agregamos parámetros al método
	public String abrirInicio(HttpSession session, Model model) {
		UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute("u");
		if(session.getAttribute("u") == null) {
			return "redirect:/epicgames/login";
		}else {
			model.addAttribute("datosUsuario", usuarioLogueado);
			return "principal";
		}
		
	}
	
	
	//--> Validar Acceso
	@Autowired
	private IUsuarioRepository repoUsu;
	@PostMapping("/login")
	public String validarAcceso(
			@RequestParam("email") String usuario,
			@RequestParam("clave") String clave,
			Model model,
			//1. Añadimos una sesión
			HttpSession session) {
		UsuarioDTO u = repoUsu.findByEmailAndClave(usuario, clave);
		System.out.println("Buscando a: " + usuario + " con clave: " + clave);
		if (u != null) {
			session.setAttribute("u", u);
			System.out.println("DEBUG: Usuario encontrado: " + u.getNombre());
			model.addAttribute("mensaje", "Bienvenido: " + u.getNombre());
			model.addAttribute("cssmensaje", "alert alert-success");
			//2. Implementamos un redirect
			return "redirect:/epicgames/principal";
		} else {
			System.out.println("DEBUG: No se encontró el usuario en la base de datos.");
			model.addAttribute("mensaje", "Usuario o clave incorrecto");
			model.addAttribute("cssmensaje", "alert alert-danger");
			return "login";
		}		
	}
	
			
	
	//--> Registrar Cuenta
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

		
		//--> Actualizar Datos (Update)
	@GetMapping("/actualizarperfil")
	public String abrirActualizarPerfil(HttpSession session, Model model) {
	    // 1. Obtenemos al usuario de la sesión
	    UsuarioDTO u = (UsuarioDTO) session.getAttribute("u");
	    
	    // 2. Seguridad: si no hay sesión, al login
	    if (u == null) {
	        return "redirect:/epicgames/login";
	    }
	    
	    // 3. Pasamos los datos del usuario al modelo para llenar el formulario
	    model.addAttribute("usuario", u);
	    return "actualizarcuenta"; 
	}
	
	@PostMapping("/actualizardatos")
	public String actualizarDatos(@ModelAttribute("usuario") UsuarioDTO usuarioEditado, 
	                              HttpSession session, 
	                              Model model) {
	    try {
	        UsuarioDTO actualizado = repoUsu.save(usuarioEditado);

	        if (actualizado != null) {
	            session.setAttribute("u", actualizado);
	            
	            model.addAttribute("mensaje", "Datos actualizados correctamente");
	            model.addAttribute("cssmensaje", "alert alert-success");
	        }
	    } catch (Exception e) {
	        model.addAttribute("mensaje", "Error al actualizar: ");
	        model.addAttribute("cssmensaje", "alert alert-danger");
	        return "actualizarcuenta";
	    }
	    
	    return "redirect:/epicgames/principal";
	}
		
		
	

		
	}
	


