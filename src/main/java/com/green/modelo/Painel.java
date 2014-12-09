package com.green.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the midia_statica database table.
 * 
 */
@Entity
@Table(name = "painel")
public class Painel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "IDPainel")
	private Integer iDPainel;

	private boolean ativo;

	@Column(name="descrição")
	private String descricao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date DTAlt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date DTInc;

	@JoinColumn(name = "IDPraca", referencedColumnName = "idpraca")
	@ManyToOne
	private Praca iDPraca;

	@JoinColumn(name = "IDUsuario", referencedColumnName = "iDUsuario")
	@ManyToOne
	private Usuario iDUsuario;

	@JoinColumn(name = "IDUsuarioAlt", referencedColumnName = "iDUsuario")
	@ManyToOne
	private Usuario iDUsuarioAlt;

	@Column(name="codigo")
	private String codigo;

	public Painel() {
	}

	public Integer getIDPainel() {
		return this.iDPainel;
	}

	public void setIDPainel(Integer iDPainel) {
		this.iDPainel = iDPainel;
	}

	public boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDTAlt() {
		return this.DTAlt;
	}

	public void setDTAlt(Date DTAlt) {
		this.DTAlt = DTAlt;
	}

	public Date getDTInc() {
		return this.DTInc;
	}

	public void setDTInc(Date DTInc) {
		this.DTInc = DTInc;
	}

	public Praca getIDPraca() {
		return this.iDPraca;
	}

	public void setIDPraca(Praca iDPraca) {
		this.iDPraca = iDPraca;
	}

	public Usuario getIDUsuario() {
		return this.iDUsuario;
	}

	public void setIDUsuario(Usuario iDUsuario) {
		this.iDUsuario = iDUsuario;
	}

	public Usuario getIDUsuarioAlt() {
		return this.iDUsuarioAlt;
	}

	public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
		this.iDUsuarioAlt = iDUsuarioAlt;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}