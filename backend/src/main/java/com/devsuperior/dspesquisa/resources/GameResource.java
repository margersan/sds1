package com.devsuperior.dspesquisa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dspesquisa.dto.GameDTO;
import com.devsuperior.dspesquisa.services.GameService;

//Camada de Controladores REST

@RestController  //define que a classe vai ser recurso rest
@RequestMapping(value = "/games") //mapeamento de url / rotas
public class GameResource {
	
	@Autowired //injeção de dependência para trazer os dados do repositório para o resource;
	private GameService service;
	
	@GetMapping //Avisando que será uma requisição get
	public ResponseEntity<List<GameDTO>> findAll() {	//Resposta de requisição com algum conteúdo
		List<GameDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	/*
	@GetMapping(value = "/{id}")
	public ResponseEntity<GameDTO> findById(@PathVariable Long id) { //anotatoin @PathVariable (id colocado no endpoint)
		GameDTO gameDTO = service.findById(id).get(); //inserido um .get pois o findById retorna um optional
		return ResponseEntity.ok().body(gameDTO);
	}
	*/

}
