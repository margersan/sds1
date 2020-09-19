package com.devsuperior.dspesquisa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.dto.GameDTO;
import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.repositories.GameRepository;

//Trafegar Dados entre a camada de acesso a dados (Repositories) com a camada Controladores REST através de DTO;

@Service //injeção de dependência para trazer os dados do repositório para o resource;
public class GameService {
	
	@Autowired
	private GameRepository repository;
	
	@Transactional(readOnly = true) //para garantir que toda a parte de BD será feita apenas no Service - readOnly = true para evitar lock.
	public List<GameDTO> findAll() {
			List<Game> list = repository.findAll();
			return list.stream().map(x -> new GameDTO(x)).collect(Collectors.toList());
	}
	

}
