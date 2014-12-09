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

@Entity
@Table(name = "check_ponto")
public class CheckPonto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "IDCheckPonto")
	private Integer iDCheckPonto;
	@JoinColumn(name = "IDFuncionario", referencedColumnName = "IDFuncionario")
	@ManyToOne
	private Funcionario iDFuncionario;
	@Column(name = "DTInc")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dTInc;
	@JoinColumn(name = "IDPontodevenda", referencedColumnName = "IDPontodeVenda")
	@ManyToOne
	private Pontodevenda iDPontodevenda;
	
	

	
	
	
	public CheckPonto() {
		
	}

	public CheckPonto(Funcionario iDFuncionario, Date dTInc,
			Pontodevenda iDPontodevenda) {
		
		this.iDFuncionario = iDFuncionario;
		this.dTInc = dTInc;
		this.iDPontodevenda = iDPontodevenda;
	}

	public Integer getIDCheckPonto() {
		return iDCheckPonto;
	}

	public void setIDCheckPonto(Integer iDCheckPonto) {
		this.iDCheckPonto = iDCheckPonto;
	}

	public Funcionario getIDFuncionario() {
		return iDFuncionario;
	}

	public void setIDFuncionario(Funcionario iDFuncionario) {
		this.iDFuncionario = iDFuncionario;
	}

	public Date getDTInc() {
		return dTInc;
	}

	public void setDTInc(Date dTInc) {
		this.dTInc = dTInc;
	}

	public Pontodevenda getIDPontodevenda() {
		return iDPontodevenda;
	}

	public void setIDPontodevenda(Pontodevenda pontodevenda) {
		this.iDPontodevenda = pontodevenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((iDCheckPonto == null) ? 0 : iDCheckPonto.hashCode());
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
		CheckPonto other = (CheckPonto) obj;
		if (iDCheckPonto == null) {
			if (other.iDCheckPonto != null)
				return false;
		} else if (!iDCheckPonto.equals(other.iDCheckPonto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CheckPonto [iDCheckPonto=" + iDCheckPonto + "]";
	}

}
