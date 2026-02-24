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

import com.epicgames.app.model.CategoriaDTO;
import com.epicgames.app.model.JuegoDTO;
import com.epicgames.app.repository.ICategoriaRepository;
import com.epicgames.app.repository.IEstadoRepository;
import com.epicgames.app.repository.IJuegoRepository;

@Controller
@RequestMapping("/epicgames")
public class CategoriaController {


	@Autowired
	private ICategoriaRepository repoCategoria;


	@GetMapping("/categorias")
	public String listar(Model model) {
	    List<CategoriaDTO> listado = repoCategoria.findAll();
	  

	    model.addAttribute("lstCategorias", listado);

	    model.addAttribute("nuevoJuego", new CategoriaDTO());

	    return "mantenimientocategorias";
	}

	@PostMapping("/categorias/registrar")
	public String registrar(@ModelAttribute("nuevaCategoria") CategoriaDTO categoria, RedirectAttributes ra) {
	

		repoCategoria.save(categoria);
		ra.addFlashAttribute("mensaje", "Categoria registrada correctamente");
		ra.addFlashAttribute("cssmensaje", "alert alert-success");
		return "redirect:/epicgames/mantenimientocategorias";
	}
	
	
	/*



	@PostMapping("/categorias/actualizar")
	public String actualizar(@ModelAttribute("categoria") CategoriaDTO categoriaEditada, RedirectAttributes ra) {
		Optional<JuegoDTO> opt = repoCategoria.findById(juegoEditado.getIdJuego());
		if (opt.isEmpty()) {
			ra.addFlashAttribute("mensaje", "No se pudo actualizar: juego no encontrado");
			ra.addFlashAttribute("cssmensaje", "alert alert-danger");
			return "redirect:/epicgames/juegos";
		}

		CategoriaDTO existente = opt.get();
		existente.setTitulo(juegoEditado.getTitulo());
		existente.setDescripcion(juegoEditado.getDescripcion());
		existente.setPrecio(juegoEditado.getPrecio());
		existente.setIdCategoria(juegoEditado.getIdCategoria());
		existente.setIdEstado(juegoEditado.getIdEstado());

		repoJuego.save(existente);
		ra.addFlashAttribute("mensaje", "Juego actualizado correctamente");
		ra.addFlashAttribute("cssmensaje", "alert alert-success");
		return "redirect:/epicgames/juegos";
	}

	@PostMapping("/categorias/eliminar/{id}")
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
	
	*/
}
