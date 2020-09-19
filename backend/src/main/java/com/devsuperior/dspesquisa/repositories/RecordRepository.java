package com.devsuperior.dspesquisa.repositories;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dspesquisa.entities.Record;

@Repository //anotation para usar a interface de repository do spring boot
public interface RecordRepository extends JpaRepository<Record, Long>{

	@Query("SELECT obj FROM Record obj WHERE " //anotation para a geração de consulta em JPQL
			+ "(coalesce(:min, null) IS NULL OR obj.moment >= :min) AND"
			+ "(coalesce(:max, null) IS NULL OR obj.moment <= :max)") 
	Page<Record> findByMoments(Instant min, Instant max, Pageable pageable); //Repository não reconhece DTO e sim Entities 
	//método Page serve para já vir paginado ao invés de usar List
	//pageable ao invés de PageRequest - entender o por que?
}

