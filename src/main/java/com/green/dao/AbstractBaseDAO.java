package com.green.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;

public abstract class AbstractBaseDAO <ObjetoEntidade,PK>{
	@Inject
	private SessionFactory sf;

	protected SessionFactory getSf() {
		return sf;
	}

	public abstract ObjetoEntidade buscar(PK pk);
	public abstract ObjetoEntidade salvar(ObjetoEntidade entidade);
	
	public abstract ObjetoEntidade atualizar(ObjetoEntidade entidade);
	
	public abstract List<ObjetoEntidade> listar();
	
	public abstract void excluir(ObjetoEntidade entidade);
}
