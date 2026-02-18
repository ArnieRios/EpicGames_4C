package com.epicgames.app.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_usuarios")
public class UsuarioDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_Usuario;
	private String nombre;
	private String apellido;
	@Column(unique = true) 
	private String email;
	@Column(name="id_tipo")
	private int idtipo = 2;
	public LocalDate getFech_Reg() {
		return fech_Reg;
	}
	public void setFech_Reg(LocalDate fech_Reg) {
		this.fech_Reg = fech_Reg;
	}
	private String clave;
	@Column(name="fech_Reg")
	@DateTimeFormat(pattern= "yyyy-MM-dd")
	private LocalDate fech_Reg;
	private int Id_Estado = 1;
	
	//Setters and getters
	
	public int getIdtipo() {
		return idtipo;
	}
	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}
	public int getId_Usuario() {
		return id_Usuario;
	}
	public void setId_Usuario(int id_Usuario) {
		this.id_Usuario = id_Usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public int getId_Estado() {
		return Id_Estado;
	}
	public void setId_Estado(int id_Estado) {
		Id_Estado = id_Estado;
	}
	
	
	

}
