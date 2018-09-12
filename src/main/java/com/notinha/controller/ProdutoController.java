package com.notinha.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.notinha.model.ApontamentoProducao;
import com.notinha.model.Produto;
import com.notinha.model.Solicitante;
import com.notinha.model.StatusApontamento;
import com.notinha.model.TipoApontamento;
import com.notinha.service.ProdutoService;


@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static final String CADASTRO_VIEW = "CadastroProduto";
	private static final String PESQUISA_VIEW = "pesquisaProduto";
	
	
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping("/novo")
	public ModelAndView pgNovo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Produto());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Produto produto, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			System.out.println("deu erro");
			return CADASTRO_VIEW;
			
		}
		
			produtoService.salvar(produto);
			System.out.println("Salvou");
			attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
			return "redirect:/produto/novo";
	
	}
	
	@RequestMapping("/pesquisa")
	public ModelAndView pgPesquisar() {
		
		List<Produto> listProdutos = produtoService.listarTodos();
		ModelAndView mv = new ModelAndView(PESQUISA_VIEW);
		
		
		mv.addObject("listProdutos",listProdutos);
		return mv;
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView pgEdicao(@PathVariable("id") Produto produto) {

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(produto);
		return mv;

	}
	
	@RequestMapping("excluir/{id}")
	public String exclir(@PathVariable("id") Integer id,RedirectAttributes attributes) {

		produtoService.excluir(id);

		attributes.addFlashAttribute("mensagem", "Produto Excluido com Sucesso!");
		return "redirect:/produto/pesquisa";
	}
}
