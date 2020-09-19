package com.devsuperior.dspesquisa.resources;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping //Avisando que será uma requisição get
	public ResponseEntity<Page<RecordDTO>> findAll(
			@RequestParam(value = "min", defaultValue = "") String min, //Parâmetros de requisição
			@RequestParam(value = "max", defaultValue = "") String max,
			@RequestParam(value = "page", defaultValue = "0") Integer page,	
			@RequestParam(value = "linesPerPage", defaultValue = "0") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "moment") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {	//Resposta de requisição com algum conteúdo
		
		Instant minDate = ("".equals(min)) ? null : Instant.parse(min);  // lambda para converter min em instant sem dar erro
		Instant maxDate = ("".equals(max)) ? null : Instant.parse(max);
		
		if (linesPerPage == 0) {  //Na aplicação para a geração dos gráficos devemos trazer todos os registros
			linesPerPage = Integer.MAX_VALUE;
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); //Paginação com parâmetros
		
		Page<RecordDTO> list = service.findByMoments(minDate, maxDate, pageRequest);
		return ResponseEntity.ok().body(list);
	}
	

}
