package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * Entity implementation class for Entity: Vendaestorno
 *
 */
@Entity
@Table(name = "vendaestorno")
public class Vendaestorno implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="IDVendaEstorno")
	private Integer iDVendaestorno;
	@Column(name="DTEstorno")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dTEstorno;
	@Column(name="DTVenda")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dTVenda;
	@Column(name="valor")
	private BigDecimal valor;
	@Column(name="DTGestor")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dTGestor;
	@Column(name="DTInc")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dTInc;
	@JoinColumn(name="IDUsuario",referencedColumnName="IDUsuario")
	@ManyToOne
	private Usuario iDUsuario;
	@JoinColumn(name="IDCapalotejornal",referencedColumnName="IDCapalotejornal")
	@ManyToOne
	private Capalotejornal iDCapalotejornal;
	
	
	
	
	
	public Vendaestorno() {
	}
	public Vendaestorno(Capalotejornal iDCapalotejornal) {
		this.iDCapalotejornal = iDCapalotejornal;
	}
	public Vendaestorno(Integer iDVendaestorno, Date dTEstorno,
			Date dTVenda, BigDecimal valor, Date dTGestor,
			Date dTInc, Usuario iDUsuario, Capalotejornal iDCapalotejornal) {
		
		this.iDVendaestorno = iDVendaestorno;
		this.dTEstorno = dTEstorno;
		this.dTVenda = dTVenda;
		this.valor = valor;
		this.dTGestor = dTGestor;
		this.dTInc = dTInc;
		this.iDUsuario = iDUsuario;
		this.iDCapalotejornal = iDCapalotejornal;
	}
	public Integer getIDVendaestorno() {
		return iDVendaestorno;
	}
	public void setIDVendaestorno(Integer iDVendaestorno) {
		this.iDVendaestorno = iDVendaestorno;
	}
	public Date getDTEstorno() {
		return dTEstorno;
	}
	public void setDTEstorno(Date dTEstorno) {
		this.dTEstorno = dTEstorno;
	}
	public Date getDTVenda() {
		return dTVenda;
	}
	public void setDTVenda(Date dTVenda) {
		this.dTVenda = dTVenda;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Date getDTGestor() {
		return dTGestor;
	}
	public void setDTGestor(Date dTGestor) {
		this.dTGestor = dTGestor;
	}
	public Date getDTInc() {
		return dTInc;
	}
	public void setDTInc(Date dTInc) {
		this.dTInc = dTInc;
	}
	public Usuario getIDUsuario() {
		return iDUsuario;
	}
	public void setIDUsuario(Usuario iDUsuario) {
		this.iDUsuario = iDUsuario;
	}
	public Capalotejornal getIDCapalotejornal() {
		return iDCapalotejornal;
	}
	public void setIDCapalotejornal(Capalotejornal iDCapalotejornal) {
		this.iDCapalotejornal = iDCapalotejornal;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((iDVendaestorno == null) ? 0 : iDVendaestorno.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendaestorno other = (Vendaestorno) obj;
		if (iDVendaestorno == null) {
			if (other.iDVendaestorno != null)
				return false;
		} else if (!iDVendaestorno.equals(other.iDVendaestorno))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "com.green.modelo.Vendaestorno [iDCapalotejornal=" + iDCapalotejornal + "]";
	}
	
}
