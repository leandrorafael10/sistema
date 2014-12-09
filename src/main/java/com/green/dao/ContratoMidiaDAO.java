/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.green.modelo.Cliente;
import com.green.modelo.ContratoMidia;
import com.green.modelo.Funcionario;
import com.green.modelo.Praca;
import com.green.modelo.Tipopagamento;
import com.green.modelo.Usuario;
import com.green.util.ReceitaPraca;

/**
 * 
 * @author leandro.silva
 */
@Component
@Repository("contratoMidiaDAO")
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class ContratoMidiaDAO extends AbstractDao<ContratoMidia, Integer> {

	@Inject
	private SessionFactory sf;

	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public ContratoMidia carregar(Integer k) {
		Criteria criteria = getSf().getCurrentSession().createCriteria(
				ContratoMidia.class);
		criteria.add(Restrictions.eq("idcontratoMidia", k));
		return (ContratoMidia) criteria.uniqueResult();
	}

	@Override
	public Integer salvar(ContratoMidia obj) {
		Criteria criteria = getSf().getCurrentSession().createCriteria(
				ContratoMidia.class);
		criteria.setProjection(Projections.max("idcontratoMidia"));
		Integer codigo = (Integer) criteria.uniqueResult();
		getSf().getCurrentSession().merge(obj);
		if (codigo == null) {
			return 1;
		}
		return codigo + 1;
	}

	public void salvarContrato(ContratoMidia cm) {
		getSf().getCurrentSession().save(cm);
	}

	@Override
	public List<ContratoMidia> listar() {
		Query query = getSf().getCurrentSession().createQuery(
				"from com.green.modelo.ContratoMidia ");

		return (List<ContratoMidia>) query.list();
	}

	@Override
	public void excluir(ContratoMidia obj) {
		getSf().getCurrentSession().delete(obj);
	}

	public List listaVend() {

		Query query = getSf()
				.getCurrentSession()
				.createQuery(
						"Select c.iDvendedor ,sum(c.valor),c.dTinc from com.green.modelo.ContratoMidia c group by c.iDvendedor,year(c.dTinc),month(c.dTinc)");
		// midias = query.list();
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List obj = query.list();
		return obj;

	}

	// listagem faltando 1 mes para o vencimento

	public List<ContratoMidia> listaVencidos() {
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.DAY_OF_MONTH, +30);
		Query query = getSf()
				.getCurrentSession()
				.createQuery(
						"From com.green.modelo.ContratoMidia c where c.dataFimContrato<= :d and ativo != 2")
				.setDate("d", c1.getTime());

		return (List<ContratoMidia>) query.list();
	}

	public List<ContratoMidia> listarPorPraca(Integer id) {
		Query query = getSf()
				.getCurrentSession()
				.createQuery(
						"select c.iDcontratomidia from com.green.modelo.ContratoPracas c where c.iDpraca.idpraca = :id and c.iDcontratomidia.dataFimContrato>= :hoje and c.iDcontratomidia.dataInicioContrato<= :hoje and c.iDcontratomidia.ativo = 1")
				.setInteger("id", id).setDate("hoje", new Date());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		List obj = query.list();
		List<ContratoMidia> list = new ArrayList<>();
		for (Object o : obj) {
			Map row = (Map) o;
			list.add((ContratoMidia) row.get("0"));
		}
		return list;

	}

	/* RECEITAS DE CONTRATO ATIVO POR PRAÇA */
	public List<ReceitaPraca> receitaPorPracaValorMensal(Praca praca, Calendar periodo) {
		String sql = "select pes.Razao as nome, pes.CnpjCpf as cnpj,cm.n_contrato as contrato, cm.data_inicio_contrato as data_inicio,cm.data_fim_contrato as data_fim " +
				" ,cm.valor as valor_total, TIMESTAMPDIFF(month,cm.data_inicio_contrato,cm.data_fim_contrato) as qtd_mes, "
				+ "TIMESTAMPDIFF(month,cm.data_inicio_contrato,'"
				+ periodo.get(Calendar.YEAR)
				+ "-"
				+ (periodo.get(Calendar.MONTH)+1)
				+ "-"
				+ periodo.get(Calendar.DAY_OF_MONTH)
				+ "')  as mes_parcela, "
				+ "Round(cm.valor/TIMESTAMPDIFF(month,cm.data_inicio_contrato,cm.data_fim_contrato),2) as valor_mes "
				+ "from  contrato_pracas cp join contrato_midia cm on cp.IDcontrato_midia = cm.idcontrato_midia join praca p on p.idpraca =cp.IDpraca "
				+ "join cliente c on c.IDCliente = cm.IDcliente join pessoa pes on pes.IDPessoa = c.IDPessoa "
				+ "where month(cm.data_inicio_contrato)<= "
				+ (periodo.get(Calendar.MONDAY) + 1)
				+ " and month(cm.data_fim_contrato)>= "
				+ (periodo.get(Calendar.MONDAY) + 1)
				+ " and year(cm.data_inicio_contrato)= "
				+ periodo.get(Calendar.YEAR)
				+ " and year(cm.data_fim_contrato)= "
				+ periodo.get(Calendar.YEAR)
				+ " "
				+ "and cm.ativo = 1 and p.idpraca = "
				+ praca.getIdpraca()
				+ " group by cm.idcontrato_midia ;";
		Query query = getSf().getCurrentSession().createSQLQuery(sql).addScalar("nome").addScalar("cnpj").addScalar("contrato").addScalar("data_inicio").addScalar("data_fim").addScalar("valor_total").addScalar("qtd_mes").addScalar("mes_parcela").addScalar("valor_mes");
		List<ReceitaPraca> lista = query.setResultTransformer(Transformers.aliasToBean(ReceitaPraca.class)).list();
		

		return lista;
	}
	/* RECEITAS DE CONTRATO ATIVO POR PRAÇA */
	public List<ReceitaPraca> TESTEreceitaPorPracaValorMensal(Praca praca, Calendar periodo) {
		String sql = "select pes.Razao as nome, pes.CnpjCpf as cnpj,cm.n_contrato as contrato, cm.data_inicio_contrato as data_inicio,cm.data_fim_contrato as data_fim " +
				" ,cm.valor as valor_total, (TIMESTAMPDIFF(month,cm.data_inicio_contrato,cm.data_fim_contrato)+1) as qtd_mes, "
				+ "(TIMESTAMPDIFF(month,cm.data_inicio_contrato, :periodo ) +1)  as mes_parcela, "
				+ "Round(cm.valor/(TIMESTAMPDIFF(month,cm.data_inicio_contrato,cm.data_fim_contrato) +1) ,2) as valor_mes "
				+ "from  contrato_pracas cp join contrato_midia cm on cp.IDcontrato_midia = cm.idcontrato_midia join praca p on p.idpraca =cp.IDpraca "
				+ "join cliente c on c.IDCliente = cm.IDcliente join pessoa pes on pes.IDPessoa = c.IDPessoa "
				+ "where month(cm.data_inicio_contrato)<= month( :periodo ) and month(cm.data_fim_contrato)>= month( :periodo )" +
				" and year(cm.data_inicio_contrato) >= year(:periodo) and year(cm.data_fim_contrato) <= year(:periodo) " +
				"and cm.ativo = 1 and p.idpraca = :idpraca group by cm.idcontrato_midia ;";
		Query query = getSf().getCurrentSession().createSQLQuery(sql).addScalar("nome").
				addScalar("cnpj").addScalar("contrato").addScalar("data_inicio").addScalar("data_fim").addScalar("valor_total").addScalar("qtd_mes")
				.addScalar("mes_parcela").addScalar("valor_mes")
				.setDate("periodo", periodo.getTime()).setInteger("idpraca", praca.getIdpraca());
		List<ReceitaPraca> lista = query.setResultTransformer(Transformers.aliasToBean(ReceitaPraca.class)).list();
		

		return lista;
	}

	public List<ContratoMidia> contratoUsuario(Usuario usuario) {
		Calendar calendar = Calendar.getInstance();
		Date dataDia = new Date(System.currentTimeMillis());
		switch (usuario.getIDFuncionario().getIDFuncao().getDescricao()) {
		case "Executivo de contas": {
			Date dataMesAntes = new Date(calendar.get(Calendar.YEAR) - 1900,
					calendar.get(Calendar.MONTH), 1);
			Criteria criteria = getSf().getCurrentSession().createCriteria(
					ContratoMidia.class);
			Criterion filtroData = Restrictions.between("dataInicioContrato",
					dataMesAntes, dataDia);
			Criterion filtro = Restrictions.eq("iDvendedor",
					usuario.getIDFuncionario());
			criteria.add(filtro).add(filtroData);
			return criteria.list();
		}
		case "Gerente de Vendas": {
			Date dataMesAntes = new Date(calendar.get(Calendar.YEAR) - 1900,
					calendar.get(Calendar.MONTH), 1);
			Criteria criteria = getSf().getCurrentSession().createCriteria(
					ContratoMidia.class);
			Criterion filtroData = Restrictions.between("dataInicioContrato",
					dataMesAntes, dataDia);
			Criterion filtro = Restrictions.eq("iDgerentevendas",
					usuario.getIDFuncionario());
			criteria.add(filtro).add(filtroData);
			return criteria.list();
		}
		case "Auxiliar Administrativo": {
			Date dataMesAntes = new Date(calendar.get(Calendar.YEAR) - 1900,
					calendar.get(Calendar.MONTH), 1);
			Criteria criteria = getSf().getCurrentSession().createCriteria(
					ContratoMidia.class);
			Criterion filtroData = Restrictions.between("dataInicioContrato",
					dataMesAntes, dataDia);
			criteria.add(filtroData);
			return criteria.list();
		}
		case "Adminstrativo": {
			Date dataMesAntes = new Date(calendar.get(Calendar.YEAR) - 1900,
					calendar.get(Calendar.MONTH), 1);
			Criteria criteria = getSf().getCurrentSession().createCriteria(
					ContratoMidia.class);
			Criterion filtroData = Restrictions.between("dataInicioContrato",
					dataMesAntes, dataDia);
			criteria.add(filtroData);
			return criteria.list();
		}
		default:
			return null;
		}

	}

	public void atualizar(ContratoMidia contratoMidia) {
		getSf().getCurrentSession().update(contratoMidia);
	}

	public List<ContratoMidia> buscaPorPeriodoTipo(Date inicio, Date fim,
			int tipo) {
		String opcao = "";
		switch (tipo) {
		case 0:
			opcao = "= 0 ";
			break;
		case 1:
			opcao = "= 1 ";
			break;
		case 2:
			opcao = "> 0 ";
			break;
		}
		Query query = getSf()
				.getCurrentSession()
				.createQuery(
						"from com.green.modelo.ContratoMidia c "
								+ "where c.dataFimContrato >= :inicio and c.dataFimContrato <= :fim and c.ativo "
								+ opcao
								+ " and c.iDcliente.iDTipocliente is null")
				.setDate("inicio", inicio).setDate("fim", fim);
		return query.list();
	}

	public List<ContratoMidia> buscaPorPeriodoTipo_parceiro(Date inicio,
			Date fim, int tipo, Cliente c) {
		String opcao = "";
		switch (tipo) {
		case 0:
			opcao = "= 0 ";
			break;
		case 1:
			opcao = "= 1 ";
			break;
		case 2:
			opcao = "> 0 ";
			break;
		}
		Query query = getSf()
				.getCurrentSession()
				.createQuery(
						"from com.green.modelo.ContratoMidia c "
								+ "where c.dataFimContrato >= :inicio and c.dataFimContrato <= :fim and c.ativo "
								+ opcao
								+ " and c.iDcliente.iDTipocliente is not null and c.iDcliente = :cliente")
				.setDate("inicio", inicio).setDate("fim", fim)
				.setParameter("cliente", c);
		return query.list();
	}

	public List<ContratoMidia> buscaPorVendedor(Funcionario funcionario,
			Date dtInicio, Date dtFim) {
		Query query;
		Map<String, Object> params = new HashMap<>();
		String sql = "From com.green.modelo.ContratoMidia c";
		Calendar c1 = Calendar.getInstance();
		c1.set(1900 + dtInicio.getYear(), dtInicio.getMonth(),
				dtInicio.getDate(), 0, 1);
		Calendar c2 = Calendar.getInstance();
		c2.set(1900 + dtFim.getYear(), dtFim.getMonth(), dtFim.getDate(), 23,
				59);

		params.put("dtini", dtInicio);
		params.put("dtfim", dtFim);
		if (funcionario == null) {
			sql = sql + " where dTVend >= :dtini and dTVend <= :dtfim ";
		} else {
			sql = sql
					+ " where iDvendedor = :vend and dTVend >= :dtini and dTVend <= :dtfim ";
			params.put("vend", funcionario);
		}
		query = getSf().getCurrentSession().createQuery(sql)
				.setProperties(params);
		return (List<ContratoMidia>) query.list();

	}

	public List<ContratoMidia> buscaPorFormaPag(Tipopagamento tipopagamento,
			Date dtInicio, Date dtFim) {
		Query query;
		Map<String, Object> params = new HashMap<>();
		String sql = "From com.green.modelo.ContratoMidia c";
		Calendar c1 = Calendar.getInstance();
		c1.set(1900 + dtInicio.getYear(), dtInicio.getMonth(),
				dtInicio.getDate(), 0, 1);
		Calendar c2 = Calendar.getInstance();
		c2.set(1900 + dtFim.getYear(), dtFim.getMonth(), dtFim.getDate(), 23,
				59);

		params.put("dtini", dtInicio);
		params.put("dtfim", dtFim);
		if (tipopagamento == null) {
			sql = sql + " where dTVend >= :dtini and dTVend <= :dtfim ";
		} else {
			sql = sql
					+ " where iDtipopagamento = :vend and dTVend >= :dtini and dTVend <= :dtfim ";
			params.put("vend", tipopagamento);
		}
		query = getSf().getCurrentSession().createQuery(sql)
				.setProperties(params);
		return (List<ContratoMidia>) query.list();

	}

	// nao esta sendo utilizado
	public List<ContratoMidia> buscarRazaoLike(String razao) {
		String sql = " from com.green.modelo.ContratoMidia c";
		Query query;
		if (!razao.equals("")) {
			sql = sql + " where upper(c.iDcliente.iDPessoa.razao) like '%"
					+ razao.toUpperCase() + "%'";
			query = getSf().getCurrentSession().createQuery(sql);
		} else {
			query = getSf().getCurrentSession().createQuery(sql);
		}

		return (List<ContratoMidia>) query.list();
	}

	public List<ContratoMidia> contratosDoCliente(Cliente c) {
		Query query = getSf()
				.getCurrentSession()
				.createQuery(
						"from com.green.modelo.ContratoMidia c where c.iDcliente = :c")
				.setParameter("c", c);
		return (List<ContratoMidia>) query.list();
	}
}
