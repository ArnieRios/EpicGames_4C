package com.epicgames.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "tb_Categorias")
public class CategoriaDTO {
	
	private int id_categoria;
	private String nom_categoria;
	//Setters and getters
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNom_categoria() {
		return nom_categoria;
	}
	public void setNom_categoria(String nom_categoria) {
		this.nom_categoria = nom_categoria;
	}
	

}
