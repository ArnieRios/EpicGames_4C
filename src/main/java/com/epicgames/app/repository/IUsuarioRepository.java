package com.epicgames.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicgames.app.model.UsuarioDTO;

@Repository
public interface IUsuarioRepository 
				extends JpaRepository<UsuarioDTO, Integer>{

}
