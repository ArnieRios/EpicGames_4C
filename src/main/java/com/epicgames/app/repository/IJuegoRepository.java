package com.epicgames.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicgames.app.model.JuegoDTO;


@Repository
public interface IJuegoRepository 
				extends JpaRepository<JuegoDTO, Integer>{

}
