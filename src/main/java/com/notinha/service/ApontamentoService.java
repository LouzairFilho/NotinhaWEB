package com.notinha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.notinha.model.ApontamentoProducao;
import com.notinha.repository.ApontamentoRepository;

@Service
public class ApontamentoService {
	
	@Autowired
	private ApontamentoRepository apontamentoRepository;
	
	@Autowired
	private SolicitanteService solicitanteService;
	
	public void salvar(ApontamentoProducao apontamento) {

		try {
			
			//apontamento.setSolicitante(solicitanteService.buscarById(apontamento.getIdSolicitante()));
			apontamentoRepository.save(apontamento);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data Incorreto");
		}

	}

	public List<ApontamentoProducao> listarTodos(Sort.Direction direcao, String ordenacao){
		
		return apontamentoRepository.findAll(new Sort(direcao, ordenacao));
	}

}
