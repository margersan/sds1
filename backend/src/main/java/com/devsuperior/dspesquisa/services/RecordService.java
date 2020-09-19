package com.devsuperior.dspesquisa.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.Record;
import com.devsuperior.dspesquisa.repositories.GameRepository;
import com.devsuperior.dspesquisa.repositories.RecordRepository;

//Trafegar Dados entre a camada de acesso a dados (Repositories) com a camada Controladores REST através de DTO;

@Service //injeção de dependência para trazer os dados do repositório para o resource;
public class RecordService {
	
	@Autowired //injeção de dependência para trazer os dados do repositório para o service;
	private RecordRepository repository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional //para garantir que toda a parte de BD será feita apenas no Service
	public RecordDTO insert(RecordInsertDTO dto) { //insert é o método que vai inserir no repository
		Record entity = new Record();
		entity.setName(dto.getName());   //Dado que está vindo do POST (aplicativo)
		entity.setAge(dto.getAge());     //Dado que está vindo do POST (aplicativo)
		entity.setMoment(Instant.now()); //Momento atual
		
		Game game = gameRepository.getOne(dto.getGameId()); //get.one - instancia um objeto mas não vai ainda no BD
		entity.setGame(game); //precisamos passar o game completo e não apenas o ID
		
		entity = repository.save(entity); //salvando no BD e tendo o entity atualizado
		
		return new RecordDTO(entity); //Retorna instanciando o RecordDTO;
	}
}
