package com.devsuperior.dspesquisa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dspesquisa.entities.Game;

@Repository //anotation para usar a interface de repository do spring boot
public interface GameRepository extends JpaRepository<Game, Long>{

}
