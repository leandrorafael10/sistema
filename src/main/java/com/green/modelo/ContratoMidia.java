/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "contrato_midia")
@XmlRootElement
public class ContratoMidia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idcontrato_midia")
    private Integer idcontratoMidia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_inicio_contrato")
    @Temporal(TemporalType.DATE)
    private Date dataInicioContrato;
    @Column(name = "data_fim_contrato")
    @Temporal(TemporalType.DATE)
    private Date dataFimContrato;
    @Column(name = "data_cancelamento")
    @Temporal(TemporalType.DATE)
    private Date dataCancelamento;
    @Column(name = "DTVend")
    @Temporal(TemporalType.DATE)
    private Date dTVend;
    @Column(name = "DTinc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTinc;
    @Column(name = "DTalt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTalt;
    @Size(max = 15)
    @Column(name = "n_contrato")
    private String nContrato;
    @Column(name = "bruto_liq")
    private Boolean brutoLiq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_vencimento")
    private int diaVencimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "numero_parcelas")
    private String numeroParcelas;
    @Column(name = "porc_agencia")
    @Max(value = 100)
    private BigDecimal porcAgencia;
    @ManyToMany (cascade={ CascadeType.PERSIST,CascadeType.MERGE}) 
    @JoinTable(name = "contrato_pracas",
    joinColumns = @JoinColumn(name = "idcontrato_midia",referencedColumnName = "IDcontrato_midia"),   
    inverseJoinColumns = @JoinColumn(name = "IDpraca",referencedColumnName = "idpraca")) 
    private List<Praca> pracas;
    @Size(max = 200)
    @Column(name = "obs")
    private String obs;
    @JoinColumn(name = "IDtipo_pagamento", referencedColumnName = "IDTipoPagamento")
    @ManyToOne
    private Tipopagamento iDtipopagamento;
    @JoinColumn(name = "IDvendedor", referencedColumnName = "IDFuncionario")
    @ManyToOne
    private Funcionario iDvendedor;
    @JoinColumn(name = "IDusuarioalt", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDusuarioalt;
    @JoinColumn(name = "IDusuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDusuario;
    @JoinColumn(name = "IDgerente_vendas", referencedColumnName = "IDFuncionario")
    @ManyToOne
    private Funcionario iDgerentevendas;
    @JoinColumn(name = "IDcliente", referencedColumnName = "IDCliente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente iDcliente;
    @JoinColumn(name = "IDassistente_vendas", referencedColumnName = "IDFuncionario")
    @ManyToOne
    private Funcionario iDassistentevendas;
    @JoinColumn(name = "IDagencia", referencedColumnName = "IDFornecedor")
    @ManyToOne
    private Fornecedor iDagencia;
    
    @OneToMany(mappedBy = "iDContratoMidia", fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    private List<ObsContrato> iDobsContrato;

    @OneToMany(mappedBy = "iDContratoMidia",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    private List<ProducaoMidia> producaoList;
    
    @OneToOne(mappedBy = "IDContratoMidia",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    private Origem origen;
    @Lob
    @Size(max = 65535)
    @Column(name = "obs_cancelamento")
    private String obsCancelamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    @Max(value = 99999999)
    private BigDecimal valor;
    @Column(name = "ativo")
    private Integer ativo;
    @JoinColumn(name = "IDproduto", referencedColumnName = "idproduto_midia")
    @ManyToOne(optional = false)
    private ProdutoMidia iDproduto;
    @Column(name = "cliente_parceiro")
    private String clienteParceiro;

    public ContratoMidia() {
    }

    public ContratoMidia(Integer idcontratoMidia) {
        this.idcontratoMidia = idcontratoMidia;
    }

    public ContratoMidia(Integer idcontratoMidia, Date dataInicioContrato) {
        this.idcontratoMidia = idcontratoMidia;
        this.dataInicioContrato = dataInicioContrato;
    }

    public Integer getIdcontratoMidia() {
        return idcontratoMidia;
    }

    public void setIdcontratoMidia(Integer idcontratoMidia) {
        this.idcontratoMidia = idcontratoMidia;
    }

    public Fornecedor getIDagencia() {
        return iDagencia;
    }

    public void setIDagencia(Fornecedor iDagencia) {
        this.iDagencia = iDagencia;
    }

    public Date getDataInicioContrato() {
        return dataInicioContrato;
    }

    public void setDataInicioContrato(Date dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }

    public Date getDataFimContrato() {
        return dataFimContrato;
    }

    public void setDataFimContrato(Date dataFimContrato) {
        this.dataFimContrato = dataFimContrato;
    }

    public String getObsCancelamento() {
        return obsCancelamento;
    }

    public void setObsCancelamento(String obsCancelamento) {
        this.obsCancelamento = obsCancelamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public ProdutoMidia getIDproduto() {
        return iDproduto;
    }

    public void setIDproduto(ProdutoMidia iDproduto) {
        this.iDproduto = iDproduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontratoMidia != null ? idcontratoMidia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoMidia)) {
            return false;
        }
        ContratoMidia other = (ContratoMidia) object;
        if ((this.idcontratoMidia == null && other.idcontratoMidia != null) || (this.idcontratoMidia != null && !this.idcontratoMidia.equals(other.idcontratoMidia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.ContratoMidia[ idcontratoMidia=" + idcontratoMidia + " ]";
    }

    public Funcionario getIDvendedor() {
        return iDvendedor;
    }

    public void setIDvendedor(Funcionario iDvendedor) {
        this.iDvendedor = iDvendedor;
    }

    public Usuario getIDusuarioalt() {
        return iDusuarioalt;
    }

    public void setIDusuarioalt(Usuario iDusuarioalt) {
        this.iDusuarioalt = iDusuarioalt;
    }

    public Usuario getIDusuario() {
        return iDusuario;
    }

    public void setIDusuario(Usuario iDusuario) {
        this.iDusuario = iDusuario;
    }

    public Funcionario getIDgerentevendas() {
        return iDgerentevendas;
    }

    public void setIDgerentevendas(Funcionario iDgerentevendas) {
        this.iDgerentevendas = iDgerentevendas;
    }

    public Cliente getIDcliente() {
        return iDcliente;
    }

    public void setIDcliente(Cliente iDcliente) {
        this.iDcliente = iDcliente;
    }

    public Funcionario getIDassistentevendas() {
        return iDassistentevendas;
    }

    public void setIDassistentevendas(Funcionario iDassistentevendas) {
        this.iDassistentevendas = iDassistentevendas;
    }

    public Tipopagamento getIDtipopagamento() {
        return iDtipopagamento;
    }

    public void setIDtipopagamento(Tipopagamento iDtipopagamento) {
        this.iDtipopagamento = iDtipopagamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }


    public Date getDTinc() {
        return dTinc;
    }

    public void setDTinc(Date dTinc) {
        this.dTinc = dTinc;
    }

    public Date getDTalt() {
        return dTalt;
    }

    public void setDTalt(Date dTalt) {
        this.dTalt = dTalt;
    }

    public String getNContrato() {
        return nContrato;
    }

    public void setNContrato(String nContrato) {
        this.nContrato = nContrato;
    }

    public Boolean getBrutoLiq() {
        return brutoLiq;
    }

    public void setBrutoLiq(Boolean brutoLiq) {
        this.brutoLiq = brutoLiq;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(int diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public String getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(String numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public BigDecimal getPorcAgencia() {
        return porcAgencia;
    }

    public void setPorcAgencia(BigDecimal porcAgencia) {
        this.porcAgencia = porcAgencia;
    }

    public List<ObsContrato> getIDobsContrato() {
        return iDobsContrato;
    }

    public void setIDobsContrato(List<ObsContrato> iDobsContrato) {
        this.iDobsContrato = iDobsContrato;
    }

    public String getClienteParceiro() {
        return clienteParceiro;
    }

    public void setClienteParceiro(String clienteParceiro) {
        this.clienteParceiro = clienteParceiro;
    }

    public Date getDTVend() {
        return dTVend;
    }

    public void setDTVend(Date dTVend) {
        this.dTVend = dTVend;
    }

    public List<Praca> getPracas() {
        return pracas;
    }

    public void setPracas(List<Praca> pracas) {
        this.pracas = pracas;
    }

    public List<ProducaoMidia> getProducaoList() {
        return producaoList;
    }

    public void setProducaoList(List<ProducaoMidia> producaoList) {
        this.producaoList = producaoList;
    }

    public Origem getOrigen() {
        return origen;
    }

    public void setOrigen(Origem origen) {
        this.origen = origen;
    }

    

       
    
}
