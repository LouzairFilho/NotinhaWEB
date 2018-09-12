package com.notinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.notinha.model.Cliente;
import com.notinha.model.Produto;
import com.notinha.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	private static final String CADASTRO_VIEW = "CadastroCliente";

	private static final String PESQUISA_VIEW = "pesquisaCliente";
	
	@Autowired
	private ClienteService clienteSevice;
	

	@RequestMapping("/novo")
	public ModelAndView pgNovo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Cliente());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cliente cliente, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			System.out.println("deu erro");
			return CADASTRO_VIEW;
			
		}
			
			clienteSevice.salvar(cliente);
			System.out.println("Salvou");
			attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
			return "redirect:/cliente/novo";
	
	}
	
	@RequestMapping("/pesquisa")
	public ModelAndView pgPesquisar() {
		
		List<Cliente> listClintes = clienteSevice.listarTodos();
		ModelAndView mv = new ModelAndView(PESQUISA_VIEW);
		
		
		mv.addObject("listClintes",listClintes);
		return mv;
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView pgEdicao(@PathVariable("id") Cliente cliente) {

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(cliente);
		return mv;

	}
	
	@RequestMapping("excluir/{id}")
	public String exclir(@PathVariable("id") Integer id,RedirectAttributes attributes) {

		clienteSevice.excluir(id);

		attributes.addFlashAttribute("mensagem", "Produto Excluido com Sucesso!");
		return "redirect:/cliente/pesquisa";
	}
}
