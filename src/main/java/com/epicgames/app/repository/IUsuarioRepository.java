package com.epicgames.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicgames.app.model.UsuarioDTO;

@Repository
public interface IUsuarioRepository 
				extends JpaRepository<UsuarioDTO, Integer>{
		UsuarioDTO findByEmailAndClave(String email, String clave);
		
		List<UsuarioDTO> findByIdtipoEquals(int tipo);
}
