package com.epicgames.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_cab_boleta")
public class CabeceraBoletaDTO {
	@Id
	private String num_bol;
	private String fch_bol;
	private int cod_cliente;
	

	

}
