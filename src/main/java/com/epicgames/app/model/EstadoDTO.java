package com.epicgames.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "tb_Estado")
public class EstadoDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_estado;
	private String nom_estado;
	//Setters and getters
	public int getId_estado() {
		return id_estado;
	}
	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}
	public String getNom_estado() {
		return nom_estado;
	}
	public void setNom_estado(String nom_estado) {
		this.nom_estado = nom_estado;
	}
	
}
