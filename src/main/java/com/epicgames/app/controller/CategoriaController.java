package com.epicgames.app.controller;


import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epicgames.app.model.CategoriaDTO;
import com.epicgames.app.repository.ICategoriaRepository;
import com.epicgames.app.repository.IEstadoRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/epicgames")
public class CategoriaController {


	@Autowired
	private ICategoriaRepository repoCategoria;
	
	@Autowired
	private IEstadoRepository repoEstado;


	@GetMapping("/categorias")
	public String listar(Model model) {
	    List<CategoriaDTO> listado = repoCategoria.findAll();
	  

	    model.addAttribute("lstCategorias", listado);
	    model.addAttribute("lstEstados", repoEstado.findAll());

	    model.addAttribute("nuevaCategoria", new CategoriaDTO());

	    return "mantenimientocategorias";
	}

	@PostMapping("/categorias/registrar")
	public String registrar(@ModelAttribute("nuevaCategoria") CategoriaDTO categoria, RedirectAttributes ra) {
		
		categoria.setIdEstado(1);
	

		repoCategoria.save(categoria);
		ra.addFlashAttribute("mensaje", "Categoria registrada correctamente");
		ra.addFlashAttribute("cssmensaje", "alert alert-success");
		return "redirect:/epicgames/categorias";
	}
	
	
	@GetMapping("/categorias/editar/{id}")
	public String editar(@PathVariable("id") int id_categoria, Model model, RedirectAttributes ra) {
		Optional<CategoriaDTO> opt = repoCategoria.findById(id_categoria);
		if (opt.isEmpty()) {
			ra.addFlashAttribute("mensaje", "No se encontró la categoria con ID: " + id_categoria);
			ra.addFlashAttribute("cssmensaje", "alert alert-danger");
			return "redirect:/epicgames/categorias";
		}

		model.addAttribute("categoria", opt.get());
		model.addAttribute("lstCategorias", repoCategoria.findAll());
		model.addAttribute("lstEstados", repoEstado.findAll());
		return "actualizarcategoria";
	}

	@PostMapping("/categorias/actualizar")
	public String actualizar(@ModelAttribute("categoria") CategoriaDTO CategoriaEditada, RedirectAttributes ra) {
		Optional<CategoriaDTO> opt = repoCategoria.findById(CategoriaEditada.getId_categoria());
		if (opt.isEmpty()) {
			ra.addFlashAttribute("mensaje", "No se pudo actualizar: categoría no encontrada");
			ra.addFlashAttribute("cssmensaje", "alert alert-danger");
			return "redirect:/epicgames/categorias";
		}

		CategoriaDTO existente = opt.get();
		existente.setNom_categoria(CategoriaEditada.getNom_categoria());
		existente.setDescripcion(CategoriaEditada.getDescripcion());
		existente.setIdEstado(CategoriaEditada.getIdEstado());

	

		repoCategoria.save(existente);
		ra.addFlashAttribute("mensaje", "Categoria actualizada correctamente");
		ra.addFlashAttribute("cssmensaje", "alert alert-success");
		return "redirect:/epicgames/categorias";
	}

	@PostMapping("/categorias/eliminar/{id}")
	public String eliminarLogico(@PathVariable("id") int id_categoria, RedirectAttributes ra) {
		Optional<CategoriaDTO> opt = repoCategoria.findById(id_categoria);
		if (opt.isPresent()) {
			CategoriaDTO categoria = opt.get();
			categoria.setIdEstado(2); // 2 = eliminado
			repoCategoria.save(categoria);
			ra.addFlashAttribute("mensaje",  "Categoria eliminada (lógica) correctamente");
			ra.addFlashAttribute("cssmensaje", "alert alert-warning");
		} else {
			ra.addFlashAttribute("mensaje", "No se encontró la categoria para eliminar");
			ra.addFlashAttribute("cssmensaje", "alert alert-danger");
		}

		return "redirect:/epicgames/categorias";
	}
	

	
	
}
