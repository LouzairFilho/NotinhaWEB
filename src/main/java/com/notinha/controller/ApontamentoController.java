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
import com.notinha.model.Solicitante;
import com.notinha.model.StatusApontamento;
import com.notinha.model.TipoApontamento;
import com.notinha.service.ApontamentoService;
import com.notinha.service.SolicitanteService;


@Controller
@RequestMapping("/apontamento")
public class ApontamentoController {
	
	private static final String CADASTRO_VIEW = "CadastroApontamento";
	private static final String PESQUISA_VIEW = "pesquisaApontamento";
	
	@Autowired
	private SolicitanteService solicitanteService;
	
	@Autowired
	private ApontamentoService apontamentoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new ApontamentoProducao());
		return mv;
	}
	
	@RequestMapping("/pesquisa")
	public ModelAndView pesquisar() {
		
		List<ApontamentoProducao> apontamentos = apontamentoService.listarTodos(Sort.Direction.ASC, "dataEntrada");
		ModelAndView mv = new ModelAndView(PESQUISA_VIEW);
		
		
		mv.addObject("apontamentos",apontamentos);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated ApontamentoProducao apontamento, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			System.out.println("deu erro");
			return CADASTRO_VIEW;
			
		}
		try {
			
			apontamentoService.salvar(apontamento);
			System.out.println("Salvou");
			attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
			return "redirect:/apontamento/novo";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") ApontamentoProducao apontamentoProducao) {

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		apontamentoProducao.setIdSolicitante(apontamentoProducao.getSolicitante().getId());
		mv.addObject(apontamentoProducao);
		return mv;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@ModelAttribute("todosTipoApontamento")
	public List<TipoApontamento> todosTipoApontamento() {
		return Arrays.asList(TipoApontamento.values());
	}
	
	@ModelAttribute("todosSolicitantes")
	public List<Solicitante> todosSolicitantes() {
		
		List<Solicitante> solicitantes = solicitanteService.listarTodos(Sort.Direction.ASC, "nome");
		return solicitantes;
	}
	
	@ModelAttribute("todosStatusApontamento")
	public List<StatusApontamento> todosStatusApontamento() {
		return Arrays.asList(StatusApontamento.values());
	}
}
