package com.epicgames.app.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_provedor")
public class ProvedorDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_provedor;
	private String nom_prov;
}
