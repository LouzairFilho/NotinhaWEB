package com.notinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notinha.model.ApontamentoProducao;


public interface ApontamentoRepository extends JpaRepository<ApontamentoProducao, Long> {

}
