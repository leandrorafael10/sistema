package com.green.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="calendarioUtil")
@ViewScoped
public class CalendarioUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Mes> listaMes ;
	private List<Integer> ano;

	@PostConstruct
	public void init() {
		this.listaMes = new ArrayList<Mes>();
		this.ano = new ArrayList<Integer>();
		
		
		this.listaMes.add(new Mes("janeiro", 1));
		this.listaMes.add(new Mes("fevereiro", 2));
		this.listaMes.add(new Mes("março", 3));
		this.listaMes.add(new Mes("abril", 4));
		this.listaMes.add(new Mes("mail", 5));
		this.listaMes.add(new Mes("junho", 6));
		this.listaMes.add(new Mes("julho", 7));
		this.listaMes.add(new Mes("agosto", 8));
		this.listaMes.add(new Mes("setembro", 9));
		this.listaMes.add(new Mes("outubro", 10));
		this.listaMes.add(new Mes("novembro", 11));
		this.listaMes.add(new Mes("dezembro", 12));

		
		this.ano.add(2014);
		this.ano.add(2015);
		this.ano.add(2016);
		this.ano.add(2017);
		this.ano.add(2018);
		this.ano.add(2019);
		this.ano.add(2020);
	}

	public List<Mes> getListaMes() {
		return listaMes;
	}

	public void setListaMes(List<Mes> listaMes) {
		this.listaMes = listaMes;
	}

	public List<Integer> getAno() {
		return ano;
	}

	public void setAno(List<Integer> ano) {
		this.ano = ano;
	}

	public class Mes {
		
		private String mes;
		
		private int numeroMes;

		public Mes(String mes, int numeroMes) {
			this.mes = mes;
			this.numeroMes = numeroMes;
		}

		public String getMes() {
			return mes;
		}

		public void setMes(String mes) {
			this.mes = mes;
		}

		public int getNumeroMes() {
			return numeroMes;
		}

		public void setNumeroMes(int numeroMes) {
			this.numeroMes = numeroMes;
		}

		

	}

}
