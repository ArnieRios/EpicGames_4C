package com.epicgames.app.controller;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epicgames.app.model.JuegoDTO;
import com.epicgames.app.repository.ICategoriaRepository;
import com.epicgames.app.repository.IEstadoRepository;
import com.epicgames.app.repository.IJuegoRepository;
import com.epicgames.app.repository.IProvedorRepository;

@Controller
@RequestMapping("/epicgames")
public class JuegoController {

	@Autowired
	private IJuegoRepository repoJuego;
	@Autowired
	private ICategoriaRepository repoCategoria;
	@Autowired
	private IEstadoRepository repoEstado;
	@Autowired
	private IProvedorRepository repoProvedor;

	@GetMapping("/juegos")
	public String listar(Model model) {
	    List<JuegoDTO> listado = repoJuego.findAll();
	  

	    model.addAttribute("lstJuegos", listado);
	    model.addAttribute("lstProvedores", repoProvedor.findAll());
	    model.addAttribute("lstCategorias", repoCategoria.findAll());
	    model.addAttribute("lstEstados", repoEstado.findAll());

	    model.addAttribute("nuevoJuego", new JuegoDTO());

	    return "mantenimientojuegos";
	}

	@PostMapping("/juegos/registrar")
	public String registrar(@ModelAttribute("nuevoJuego") JuegoDTO juego, RedirectAttributes ra) {
		if (juego.getFechReg() == null || juego.getFechReg().isBlank()) {
			juego.setFechReg(LocalDate.now().toString());
		}
		juego.setIdEstado(1);

		repoJuego.save(juego);
		ra.addFlashAttribute("mensaje", "Juego registrado correctamente");
		ra.addFlashAttribute("cssmensaje", "alert alert-success");
		return "redirect:/epicgames/juegos";
	}

	@GetMapping("/juegos/editar/{id}")
	public String editar(@PathVariable("id") int idJuego, Model model, RedirectAttributes ra) {
		Optional<JuegoDTO> opt = repoJuego.findById(idJuego);
		if (opt.isEmpty()) {
			ra.addFlashAttribute("mensaje", "No se encontró el juego con ID: " + idJuego);
			ra.addFlashAttribute("cssmensaje", "alert alert-danger");
			return "redirect:/epicgames/juegos";
		}

		model.addAttribute("juego", opt.get());
		model.addAttribute("lstProvedores", repoProvedor.findAll());
		model.addAttribute("lstCategorias", repoCategoria.findAll());
		model.addAttribute("lstEstados", repoEstado.findAll());
		return "actualizarjuegos";
	}

	@PostMapping("/juegos/actualizar")
	public String actualizar(@ModelAttribute("juego") JuegoDTO juegoEditado, RedirectAttributes ra) {
		Optional<JuegoDTO> opt = repoJuego.findById(juegoEditado.getIdJuego());
		if (opt.isEmpty()) {
			ra.addFlashAttribute("mensaje", "No se pudo actualizar: juego no encontrado");
			ra.addFlashAttribute("cssmensaje", "alert alert-danger");
			return "redirect:/epicgames/juegos";
		}

		JuegoDTO existente = opt.get();
		existente.setTitulo(juegoEditado.getTitulo());
		existente.setDescripcion(juegoEditado.getDescripcion());
		existente.setPrecio(juegoEditado.getPrecio());
		existente.setIdProvedor(juegoEditado.getIdProvedor());
		existente.setIdCategoria(juegoEditado.getIdCategoria());
		existente.setIdEstado(juegoEditado.getIdEstado());

		repoJuego.save(existente);
		ra.addFlashAttribute("mensaje", "Juego actualizado correctamente");
		ra.addFlashAttribute("cssmensaje", "alert alert-success");
		return "redirect:/epicgames/juegos";
	}

	@PostMapping("/juegos/eliminar/{id}")
	public String eliminarLogico(@PathVariable("id") int idJuego, RedirectAttributes ra) {
		Optional<JuegoDTO> opt = repoJuego.findById(idJuego);
		if (opt.isPresent()) {
			JuegoDTO juego = opt.get();
			juego.setIdEstado(2); // 2 = eliminado
			repoJuego.save(juego);
			ra.addFlashAttribute("mensaje", "Juego eliminado (lógico) correctamente");
			ra.addFlashAttribute("cssmensaje", "alert alert-warning");
		} else {
			ra.addFlashAttribute("mensaje", "No se encontró el juego para eliminar");
			ra.addFlashAttribute("cssmensaje", "alert alert-danger");
		}

		return "redirect:/epicgames/juegos";
	}
}
