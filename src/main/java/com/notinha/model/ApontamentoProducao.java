package com.notinha.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ApontamentoProducao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long Id;
	
	@NotNull(message="Data de Entrada é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	
	@NotNull(message="Hora de Entrada é obrigatório")
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)
	private Date horaEntrada;
	
	@NotEmpty(message= "Descrição do Serviço é obrigatório")
	@Size(max = 60, message = "Maximo 60 caracteres premitido")
	private String descricao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataExecucao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date prazoExecucao;
	
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)
	private Date inicioExecucao;
	
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)
	private Date fimExecucao;
	
	@Size(max = 60, message = "Maximo 60 caracteres premitido")
	private String insumo;
	
	@Enumerated(EnumType.STRING)
	private TipoApontamento tipo;

	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Solicitante solicitante;
	
	@Enumerated(EnumType.STRING)
	private StatusApontamento status;
	
	private String observacoes;
	
	@Transient
	private Long idSolicitante;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Date getPrazoExecucao() {
		return prazoExecucao;
	}

	public void setPrazoExecucao(Date prazoExecucao) {
		this.prazoExecucao = prazoExecucao;
	}

	public Date getInicioExecucao() {
		return inicioExecucao;
	}

	public void setInicioExecucao(Date inicioExecucao) {
		this.inicioExecucao = inicioExecucao;
	}

	public Date getFimExecucao() {
		return fimExecucao;
	}

	public void setFimExecucao(Date fimExecucao) {
		this.fimExecucao = fimExecucao;
	}

	public String getInsumo() {
		return insumo;
	}

	public void setInsumo(String insumo) {
		this.insumo = insumo;
	}

	public TipoApontamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoApontamento tipo) {
		this.tipo = tipo;
	}

	public Solicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

	public StatusApontamento getStatus() {
		return status;
	}

	public void setStatus(StatusApontamento status) {
		this.status = status;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Long getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(Long idSolicitante) {
		this.idSolicitante = idSolicitante;
	}
	
	
	
	
}
