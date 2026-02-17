package com.webjuegos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class JuegoDTO {
	private int idJuego;
	private String titulo;
	private String descripcion;
	private double precio;
	private String fechReg;
	private int id_categoria;
	private int id_estado;

}
