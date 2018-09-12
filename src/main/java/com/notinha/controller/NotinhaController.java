package com.notinha.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.notinha.model.ItemNotinha;
import com.notinha.model.Notinha;
import com.notinha.model.Status;
import com.notinha.service.ClienteService;
import com.notinha.service.NotinhaService;
import com.notinha.service.ProdutoService;
import com.notinha.util.Relatorios;

@Controller
@RequestMapping("/notinha")
public class NotinhaController {

	private static final String CADASTRO_VIEW = "novaNotinha";
	private static final String PESQUISA_VIEW = "pesquisaNotinha";
	private static final String IMPESSAO_VIEW = "impressaoNotinha";
	

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private NotinhaService notinhaService;

	@RequestMapping("/novo")
	public ModelAndView pgNovo() throws ParseException {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		Notinha notinha = new Notinha();
		notinha = notinhaService.buscaNotinhaCriacao(); 
		
		mv.addObject("itemNotinha", notinha.getItemNotinha());
		mv.addObject("notinha", notinha);
		return mv;
	}

	@RequestMapping("/pesquisa")
	public ModelAndView pgPesquisar() {
		
		List<Notinha> listNotinhas = notinhaService.listarNotinhaStatus(Status.ABERTA);
		
		ModelAndView mv = new ModelAndView(PESQUISA_VIEW);
		
		
		mv.addObject("listNotinhas",listNotinhas);
		return mv;
	}
	
	
	
	@PostMapping(params = {"salvar"})
	public String salvar(Notinha notinha){
		
		notinha = notinhaService.getOne(notinha.getId());
				
		
		notinhaService.salvar(notinha, Status.ABERTA);
		
		return "redirect:/notinha/gera-notinha/"+notinha.getId(); 
	}
	
	
	@RequestMapping("/editar/{id}")
	public ModelAndView pgEdicao(@PathVariable("id") Notinha notinha) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		
		mv.addObject("itemNotinha", notinha.getItemNotinha());
		mv.addObject("notinha", notinha);
		return mv;
	}
	
	@RequestMapping("/excluir/{id}")
	public String exclir(@PathVariable("id") Integer id,RedirectAttributes attributes) {

		notinhaService.excluir(id);

		attributes.addFlashAttribute("mensagem", "Produto Excluido com Sucesso!");
		return "redirect:notinha/pesquisa";
	}
	
	@RequestMapping(params = { "addCliente" },  method = RequestMethod.POST)
	public ModelAndView addCliente(Notinha notinha) {

		notinha.setCliente(clienteService.buscaById(notinha.getCliente()));

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		notinha.setDataNotinha(new Date());
		notinha.setItemNotinha(new ArrayList<>());
		mv.addObject("itemNotinha", notinha.getItemNotinha());
		mv.addObject("notinha", notinha);
		return mv;

	}

	@RequestMapping(params = { "buscaProduto" }, method = RequestMethod.POST)
	public ModelAndView buscaProduto(Notinha notinha) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		notinha.setDataNotinha(new Date());
		notinha.getItem().setProduto(produtoService.produtoByCodigo(notinha.getItem().getProduto()));
		
		
		ItemNotinha item = new ItemNotinha();

		item.setProduto(notinha.getItem().getProduto());
		item.setQuantidade(1);
		item.setValorItem(notinha.getItem().getProduto().getValor());

		if (notinha.getId() != null){
			notinha.setItemNotinha(notinhaService.getOne(notinha.getId()).getItemNotinha());
		}
		
		notinha.setItem(item);
		mv.addObject("itemNotinha", notinha.getItemNotinha());
		mv.addObject("notinha", notinha);
		return mv;
	}

	@RequestMapping(params = { "addProduto" })
	public ModelAndView addProduto(Notinha notinha) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		
		ItemNotinha itemTemp = notinha.getItem();
		
		if (notinha.getId() != null){
			notinha = notinhaService.getOne(notinha.getId());
		}
		notinha.setItem(itemTemp);
		if (notinha.getItemNotinha()==null){
			notinha.setItemNotinha(new ArrayList<>());
		}if (notinha.getDataNotinha() == null){
			notinha.setDataNotinha(new Date());
		}
		
		
		notinha.getItem().setValorTotal(notinha.getItem().getValorItem() * notinha.getItem().getQuantidade());
		boolean produtoLancado = false;
		for(int i = 1; notinha.getItemNotinha().size() >=i; i++ ){
			if(!notinha.getItemNotinha().isEmpty()){
				if (notinha.getItemNotinha().get(i-1).getProduto().getId() == notinha.getItem().getProduto().getId()){
					notinha.getItemNotinha().get(i-1).setQuantidade(notinha.getItem().getQuantidade());
					notinha.getItemNotinha().get(i-1).setValorItem(notinha.getItem().getValorItem());
					notinha.getItemNotinha().get(i-1).setValorTotal(notinha.getItem().getValorTotal());
					produtoLancado = true;
				}
			}
		}
		
		if(produtoLancado){
			notinha.setItem(new ItemNotinha());
			notinha = notinhaService.salvar(notinha, Status.CRIACAO);
		}else {
			notinha.getItemNotinha().add(notinha.getItem());
			notinha.setItem(new ItemNotinha());
			notinha = notinhaService.salvar(notinha, Status.CRIACAO);
		}
		
		
		mv.addObject("itemNotinha", notinha.getItemNotinha());
		mv.addObject("notinha", notinha);
		return mv;
	}

	@RequestMapping("/gera-notinha/{id}")
	public ModelAndView geraNotinha(@PathVariable("id") Integer id,RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(IMPESSAO_VIEW);
		Notinha	notinha = new Notinha();
		notinha = notinhaService.getOne(id);
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		
		
		notinha.setDataView(fmt.format(notinha.getDataNotinha()));
		
		
		


		mv.addObject("itemNotinha", notinha.getItemNotinha());
		mv.addObject("notinha", notinha);
		return mv;
	}
	
	
	
	
	
	@RequestMapping("/imprimir/{id}")
    public void download(HttpServletResponse response, @PathVariable("id") Integer id) throws IOException {
		Notinha	notinha = new Notinha();
		notinha = notinhaService.getOne(id);
		
		List<Notinha> listNotinha = new ArrayList<>();
		listNotinha.add(notinha);
		
		Relatorios rel = new Relatorios();
		
		rel.imprimeRelatorio(listNotinha, "notinha.jasper", response);
    }
	
}
