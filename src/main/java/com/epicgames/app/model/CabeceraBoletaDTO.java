package com.epicgames.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_cab_boleta")
public class CabeceraBoletaDTO {
	@Id
	private String num_bol;
	private String fch_bol;
	private int cod_cliente;
	
	//Setters and Getters 
	
	public String getNum_bol() {
		return num_bol;
	}
	public void setNum_bol(String num_bol) {
		this.num_bol = num_bol;
	}
	public String getFch_bol() {
		return fch_bol;
	}
	public void setFch_bol(String fch_bol) {
		this.fch_bol = fch_bol;
	}
	public int getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	
	

}
