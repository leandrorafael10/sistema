package com.green.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the painel_contrato database table.
 * 
 */
@Entity
@Table(name="painel_contrato")
public class PainelContrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="IDPainelContrato")
	private Integer iDPainelContrato;
	
	@JoinColumn(name="IDContratoMidia" ,referencedColumnName="idcontrato_midia")
	@OneToOne
	private ContratoMidia iDContratoMidia;


	@JoinColumn(name="IDPainel",referencedColumnName="IDPainel")
	@ManyToOne
	private Painel iDPainel;

	public PainelContrato() {
	}
	
	

	public PainelContrato(ContratoMidia iDContratoMidia, Painel iDPainel) {
		super();
		this.iDContratoMidia = iDContratoMidia;
		this.iDPainel = iDPainel;
	}



	public Integer getiDPainelContrato() {
		return iDPainelContrato;
	}


	public void setiDPainelContrato(Integer iDPainelContrato) {
		this.iDPainelContrato = iDPainelContrato;
	}


	public ContratoMidia getiDContratoMidia() {
		return iDContratoMidia;
	}


	public void setiDContratoMidia(ContratoMidia iDContratoMidia) {
		this.iDContratoMidia = iDContratoMidia;
	}


	public Painel getIDPainel() {
		return iDPainel;
	}


	public void setIDPainel(Painel iDPainel) {
		this.iDPainel = iDPainel;
	}


	


	@Override
	public String toString() {
		return "PainelContrato [iDPainelContrato=" + iDPainelContrato + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((iDPainelContrato == null) ? 0 : iDPainelContrato.hashCode());
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
		PainelContrato other = (PainelContrato) obj;
		if (iDPainelContrato == null) {
			if (other.iDPainelContrato != null)
				return false;
		} else if (!iDPainelContrato.equals(other.iDPainelContrato))
			return false;
		return true;
	}

	
	

}