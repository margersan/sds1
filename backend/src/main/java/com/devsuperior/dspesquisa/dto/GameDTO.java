package com.devsuperior.dspesquisa.dto;

import java.io.Serializable;

import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.enums.Platform;

//Trafegar os dados entre as requisições HTTP (Resource/Controller) com a camada de Servico e Entidades
public class GameDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private Platform platform;
	
	public GameDTO() {
	}

	public GameDTO(Game entity) { //Recebimento de entidade - forma pratica de instanciar o GameDTO à patir de um game;
		id = entity.getId();
		title = entity.getTitle();
		platform = entity.getPlatform();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
}
