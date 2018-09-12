package com.notinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.notinha.model.Notinha;
import com.notinha.model.Status;

public interface NotinhaRepository extends JpaRepository<Notinha, Integer> {

	@Query("select n from Notinha n where n.status = :status order by n.id desc")
	List<Notinha> listarNotinhaAberta(@Param("status") Status aberta);
	
	@Query("select n from Notinha n where n.status = :status")
	List<Notinha> buscaNotinhaCriacao(@Param("status") Status aberta);
	
	@Query("select n from Notinha n where n.id = :id")
	Notinha buscaPorId(@Param("id") Integer id);

	
}
