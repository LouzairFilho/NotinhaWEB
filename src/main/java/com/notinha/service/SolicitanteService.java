package com.notinha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.notinha.model.Solicitante;
import com.notinha.repository.SolicitanteRepository;

@Service
public class SolicitanteService {
	
	@Autowired
	private SolicitanteRepository solicitanteRepository;

	public List<Solicitante> listarTodos(Sort.Direction direcao, String ordenacao){
		
		return solicitanteRepository.findAll(new Sort(direcao, ordenacao));
	}
	
	public Solicitante buscarById(Long id){
		
		if(id>0){
			return solicitanteRepository.findOne(id);
		}else {
			return new Solicitante();
		}
	}
}
