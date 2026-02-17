package com.webjuegos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DetalleBoletaDTO {
	private int id_juego;
	private double preciovta;
	private int cantidad;
	private String nomjuego;
	private double importe;

}
