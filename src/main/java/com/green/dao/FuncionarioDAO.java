/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Funcao;
import com.green.modelo.Funcionario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
@Repository("funcionarioDAO")
@SuppressWarnings("unchecked")
public class FuncionarioDAO extends AbstractDao<Funcionario, Integer> {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Funcionario carregar(Integer k) {
        Criterion filtro = Restrictions.eq("iDFuncionario", k);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Funcionario.class);
        criteria.add(filtro);
        return (Funcionario) criteria.uniqueResult();
    }

    @Override
    public Integer salvar(Funcionario funcionario) {
        Criteria criteria = sf.getCurrentSession().createCriteria(Funcionario.class);
        criteria.setProjection(Projections.max("iDFuncionario"));
        Integer codigo = (Integer) criteria.uniqueResult();
        sf.getCurrentSession().save(funcionario);
        if (codigo == null) {
            return 1;
        }
        return codigo + 1;
    }

    
	@Override
    public List<Funcionario> listar() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Funcionario f where f.iDFuncionario>1 order by f.iDPessoa.razao");
        return (List<Funcionario>) query.list();
    }

    @Override
    public void excluir(Funcionario funcionario) {
        getSf().getCurrentSession().delete(funcionario);
        getSf().getCurrentSession().flush();
    }

    public List<Funcionario> listarPorFuncao(Funcao funcao) {
        //Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Funcionario f where f.iDFuncao :d ")
        //     .setParameter("d", funcao);
        Criterion criterion = Restrictions.eq("iDFuncao", funcao);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Funcionario.class);
        criteria.add(criterion);

        return criteria.list();
    }

    public List<Funcionario> listarGerente() {
        List<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(2);
        list.add(11);
        list.add(19);
        list.add(28);
        list.add(22);
        list.add(29);
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Funcionario f where f.iDFuncao.iDFuncao in (:ids) and f.ativo = :ativo").setParameterList("ids", list).setBoolean("ativo", true);
        return (List<Funcionario>) query.list();
    }
    public List<Funcionario> listarGerenteVendedor() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(7);
        list.add(11);
        list.add(19);
        list.add(28);
        list.add(20);
        list.add(29);
        list.add(22);
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Funcionario f where f.iDFuncao.iDFuncao in (:ids) and f.ativo = :ativo" ).setParameterList("ids", list).setBoolean("ativo", true);
        return (List<Funcionario>) query.list();
    }

    public List<Funcionario> listarPorFuncaoAtivo(Funcao funcao) {
        //Query query = getSf().getCurrentSession().createQuery("From com.green.modelo.Funcionario f where f.iDFuncao :d ")
        //     .setParameter("d", funcao);
        Criterion criterion = Restrictions.eq("iDFuncao", funcao);
        Criterion filtro = Restrictions.eq("ativo", true);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Funcionario.class);
        criteria.add(criterion).add(filtro);
        return criteria.list();
    }

    public void atualizar(Funcionario funcionario) {
        getSf().getCurrentSession().merge(funcionario);
    }

    public List<Funcionario> listarPorEquipe(Funcionario funcionario) {
        Criterion criterion = Restrictions.eq("iDFuncionario_gerente", funcionario);
        Criterion filtro = Restrictions.eq("ativo", true);
        Criteria criteria = getSf().getCurrentSession().createCriteria(Funcionario.class);
        criteria.add(criterion).add(filtro);

        return criteria.list();

    }

    public List<Funcionario> listarPorFuncaes(List<Integer> idFuncoes) {
        Query query = getSf().getCurrentSession().createQuery
        ("from com.green.modelo.Funcionario f where f.iDFuncao.iDFuncao in(:funcoes)").setParameterList("funcoes", idFuncoes);
        return (List<Funcionario>)query.list();
    }
}
