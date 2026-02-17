package com.webjuegos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaEstadoJuegoDTO {
	private int idJuego;
	private String titulo;
	private String descripcion;
	private double precio;
	private String fechReg;
	private String categoria;
	private String estado;
}
