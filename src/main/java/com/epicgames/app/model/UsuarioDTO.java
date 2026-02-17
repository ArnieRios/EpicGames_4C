package com.webjuegos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	private int id_Usuario;
	private String nombre;
	private String apellido;
	private String email;
	private int id_tipo ;
	private String clave;
	private String fech_Reg;
	private int Id_Estado;  

}
