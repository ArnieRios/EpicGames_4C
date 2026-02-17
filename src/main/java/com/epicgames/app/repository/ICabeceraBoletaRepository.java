package com.epicgames.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epicgames.app.model.CabeceraBoletaDTO;



@Repository
public interface ICabeceraBoletaRepository 
				extends JpaRepository<CabeceraBoletaDTO, String>{

}
