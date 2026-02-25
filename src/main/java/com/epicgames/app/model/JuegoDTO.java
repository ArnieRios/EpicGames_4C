package com.epicgames.app.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "tb_Juegos")
public class JuegoDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idJuego;
	private String titulo;
	private String descripcion;
	private Double precio;
	private String fechReg;
	@Column(name = "id_provedor")
	private Integer idProvedor;
	@Column(name = "id_categoria")
	  private Integer idCategoria;
	@Column(name = "id_estado")
	  private Integer idEstado;
	
	@ManyToOne
	@JoinColumn(name = "id_provedor", insertable = false, updatable = false)
	private ProvedorDTO objProvedor;
	@ManyToOne
	@JoinColumn(name = "id_categoria", insertable = false, updatable = false)
	private CategoriaDTO objCategoria;
	
	@ManyToOne
	@JoinColumn(name = "id_estado", insertable = false, updatable = false)
	private EstadoDTO objEstado;
}
