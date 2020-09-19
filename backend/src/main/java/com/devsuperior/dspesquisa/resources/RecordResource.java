package com.devsuperior.dspesquisa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.services.RecordService;

//Camada de Controladores REST

@RestController  //define que a classe vai ser recurso rest
@RequestMapping(value = "/records") //mapeamento de url / rotas
public class RecordResource {
	
	@Autowired //injeção de dependência para trazer os dados do repositório para o resource;
	private RecordService service;
	
	@PostMapping //Avisando que será endpoint do tipo POST (envio)
	public ResponseEntity<RecordDTO> insert(@RequestBody RecordInsertDTO dto) { //Anotation RequestBody pois o método é POST
		RecordDTO newDTO = service.insert(dto);
		return ResponseEntity.ok().body(newDTO); //Efetiva a gravação do POST com os dados do body
	}
	/*
	@GetMapping(value = "/{id}")
	public ResponseEntity<GameDTO> findById(@PathVariable Long id) { //anotatoin @PathVariable (id colocado no endpoint)
		GameDTO gameDTO = service.findById(id).get(); //inserido um .get pois o findById retorna um optional
		return ResponseEntity.ok().body(gameDTO);
	}
	*/

}
