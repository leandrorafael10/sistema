/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import com.green.modelo.Cliente;
import com.green.modelo.Pessoa;
import java.util.List;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyObject;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leandro.silva
 */
@Repository("clienteDAO")
public class ClienteDAO extends AbstractDao<Cliente, Integer> {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Cliente carregar(Integer k) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Cliente.class);
        Criterion filtro = Restrictions.eq("iDCliente", k);
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Cliente c  where c.iDCliente = :k").setParameter("k", k);
        Cliente c =(Cliente) getUnproxyModel((Cliente)criteria.add(filtro).uniqueResult());
        return c;
    }

    public Cliente buscaPorPessoa(Pessoa pessoa) {

        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Cliente c where c.iDPessoa = :p").setParameter("p", pessoa);
        return (Cliente) query.uniqueResult();
    }

    public Cliente carregar2(String id) {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Cliente c where c.iDPessoa.razao = :k").setString("k", id);
        Cliente c = (Cliente) query.uniqueResult();

        return (Cliente) query.uniqueResult();
    }

    @Override
    public Integer salvar(Cliente obj) {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Cliente.class);
        criteria.setProjection(Projections.max("iDCliente"));
        Integer codigo = (Integer) criteria.uniqueResult();
        getSf().getCurrentSession().save(obj);
        if (codigo == null) {
            return 1;
        }
        return codigo + 1;
    }

    @Override
    public List<Cliente> listar() {
        Criteria criteria = getSf().getCurrentSession().createCriteria(Cliente.class);
        return criteria.list();
    }
    //lista somente clientes parceiros;

    public List<Cliente> listarParceiro() {
        Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.Cliente c where c.iDTipocliente.iDTipocliente = :id").setInteger("id", 2);
        return (List<Cliente>) query.list();
    }

    @Override
    public void excluir(Cliente obj) {
        getSf().getCurrentSession().delete(obj);
    }

    public Object getUnproxyModel(Object model) {
        if (HibernateProxy.class.isAssignableFrom(model.getClass())) {
            return ((HibernateProxy) model).getHibernateLazyInitializer().getImplementation();
        }

        return model;
    }
}
