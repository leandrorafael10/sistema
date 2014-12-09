/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Pessoa;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("pessoaDAO")
public class PessoaDao extends AbstractDao<Pessoa, Integer> {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public Pessoa buscaCpfCnpj(String cpfCnpj) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Pessoa.class);
        Criterion filtro = Restrictions.eq("cnpjCpf", cpfCnpj);
        return (Pessoa) criteria.add(filtro).uniqueResult();
    }

    @Override
    public Pessoa carregar(Integer k) {
        return (Pessoa) getSf().getCurrentSession().get(Pessoa.class, k);
    }

    @Override
    public Integer salvar(Pessoa obj) {

        Criteria c = sf.getCurrentSession().createCriteria(Pessoa.class);
        c.setProjection(Projections.max("iDPessoa"));
        Integer codigo = (Integer) c.uniqueResult();
        sf.getCurrentSession().save(obj);
        if (codigo == null) {
            return 1;
        } else {
            return codigo + 1;
        }
    }
    public void salvarPessoa(Pessoa p){
        sf.getCurrentSession().save(p);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Pessoa> listar() {
        Criteria c = getSf().getCurrentSession().createCriteria(Pessoa.class);
        return c.list();
    }

    @Override
    public void excluir(Pessoa obj) {
        getSf().getCurrentSession().delete(obj);
    }

    public void atualizar(Pessoa pessoa) {
        getSf().getCurrentSession().update(pessoa);
    }
}
