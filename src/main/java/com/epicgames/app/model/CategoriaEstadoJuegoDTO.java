package com.epicgames.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "")
public class CategoriaEstadoJuegoDTO {
	private int idJuego;
	private String titulo;
	private String descripcion;
	private double precio;
	private String fechReg;
	private String categoria;
	private String estado;
}
