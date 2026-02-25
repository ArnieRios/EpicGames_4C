package com.epicgames.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epicgames.app.model.JuegoDTO;
import com.epicgames.app.repository.ICategoriaRepository;
import com.epicgames.app.repository.IJuegoRepository;

@Controller
@RequestMapping("/epicgames")
public class CatalogoController {
	
    @Autowired
    private IJuegoRepository repoJuego;
    
    @Autowired
    private ICategoriaRepository repoCategoria;

    // --- VISTA 1: Catálogo General con Filtro ---
    // Recibe el parámetro "cat" del <select> del HTML
    @GetMapping("/catalogo")
    public String verCatalogo(
           @RequestParam(name = "cat", required = false, defaultValue = "0") int idCat, 
            Model model) {
        
        // 1. Siempre cargamos las categorías para el combo select
        model.addAttribute("lstCategorias", repoCategoria.findAll());
        
        
        // 2. Lógica de filtrado:
        if (idCat == 0) {
            // Si elige "Todas" (0), mandamos todos los juegos
            model.addAttribute("lstJuegos", repoJuego.findAll());
        } else {
            // Si elige una categoría, usamos el método de búsqueda filtrada
            model.addAttribute("lstJuegos", repoJuego.findByIdCategoria(idCat));
        }
        
        // 3. Mandamos el idCat de vuelta para que el combo mantenga la selección
        model.addAttribute("catSel", idCat);
        
        return "catalogo"; // Asegúrate de que este sea el nombre de tu HTML
    }

    // --- VISTA 2: Juegos por Categoría (Ruta directa) ---
    @GetMapping("/catalogo/categoria/{id}")
    public String verJuegosPorCategoria(@PathVariable("id") int idCat, Model model) {
        model.addAttribute("lstJuegos", repoJuego.findByIdCategoria(idCat));
        model.addAttribute("categoria", repoCategoria.findById(idCat).orElse(null));
        return "redirect:/epicgames/catalogo?cat=" + idCat;
    }

    // --- VISTA 3: Detalle de juego ---
    @GetMapping("/catalogo/juego/{id}")
    public String verDetalleJuego(@PathVariable("id") int idJuego, Model model) {
        JuegoDTO juego = repoJuego.findById(idJuego).orElse(null);
        model.addAttribute("p", juego); // Usamos 'p' para que coincida con tu vista de detalle
        return "compra";
    }

}