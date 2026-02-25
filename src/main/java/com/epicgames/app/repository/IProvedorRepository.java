package com.epicgames.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicgames.app.model.ProvedorDTO;

public interface IProvedorRepository extends JpaRepository<ProvedorDTO, Integer> {

}
