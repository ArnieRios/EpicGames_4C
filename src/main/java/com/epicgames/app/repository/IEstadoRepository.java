package com.epicgames.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicgames.app.model.EstadoDTO;

@Repository
public interface IEstadoRepository 
				extends JpaRepository<EstadoDTO, Integer>{

}
