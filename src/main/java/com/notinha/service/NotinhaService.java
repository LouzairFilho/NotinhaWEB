package com.notinha.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notinha.model.Cliente;
import com.notinha.model.Notinha;
import com.notinha.model.Status;
import com.notinha.repository.NotinhaRepository;

@Service
public class NotinhaService {

	@Autowired
	private NotinhaRepository notinhaRepository;
	
	
	public Notinha salvar(Notinha notinha, Status s) {
		
		notinha.setStatus(s);
		
		return notinhaRepository.save(notinha);
	}


	public List<Notinha> listarNotinhaStatus(Status status) {
		
		return notinhaRepository.listarNotinhaAberta(status);
	}


	public Notinha buscaNotinhaCriacao() {
		
		List<Notinha> lstNotinha = new ArrayList<>();
		
		lstNotinha =notinhaRepository.buscaNotinhaCriacao(Status.CRIACAO);
		
		if ( lstNotinha.size()>0){
			return lstNotinha.get(0);
		}else{
			
			Notinha notinha = new Notinha();
			notinha .setDataNotinha(new Date());
			notinha.setCliente(new Cliente());
			notinha.setItemNotinha(new ArrayList<>());
			notinha.setValorNotinha(0.00);
			return notinha;
		}
		
	}


	public Notinha getOne(Integer id) {
		Notinha notinha = new Notinha();
		notinha = notinhaRepository.buscaPorId(id);
		
		return notinha;
	}
	
	public Notinha salvarTemp(Notinha notinha) {
		return notinhaRepository.save(notinha);
	}


	public void excluir(Integer id) {
		
		if(notinhaRepository.exists(id)){
			notinhaRepository.delete(id);
		}
		
	}
	
	
}
