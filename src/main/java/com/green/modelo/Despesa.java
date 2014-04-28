/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "despesa")
public class Despesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDDespesa")
    private Integer iDDespesa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTVencimento")
    @Temporal(TemporalType.DATE)
    private Date dTVencimento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTEmissao")
    @Temporal(TemporalType.DATE)
    private Date dTEmissao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorLiquido")
    private BigDecimal valorLiquido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorMulta")
    private BigDecimal valorMulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorJuros")
    private BigDecimal valorJuros;
    @Column(name = "DTMulta")
    @Temporal(TemporalType.DATE)
    private Date dTMulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorDesconto")
    private BigDecimal valorDesconto;
    @Column(name = "DTJuros")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTJuros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsentoMulta")
    private boolean isentoMulta;
    @Column(name="del")
    private boolean del;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsentoJuros")
    private boolean isentoJuros;
    @Column(name = "DTDesconto")
    @Temporal(TemporalType.DATE)
    private Date dTDesconto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorNominal")
    private BigDecimal valorNominal;
    @Lob
    @Size(max = 65535)
    @Column(name = "Obs")
    private String obs;
    @Column(name = "DTCancelado")
    @Temporal(TemporalType.DATE)
    private Date dTCancelado;
    @Lob
    @Size(max = 65535)
    @Column(name = "ObsCancelado")
    private String obsCancelado;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @JoinColumn(name = "IDUsuarioAlt",referencedColumnName="IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioAlt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Numero")
    private String numero;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Usuario iDUsuario;
    @JoinColumn(name="IDOrigem", referencedColumnName="idorigem")
    @ManyToOne(fetch = FetchType.LAZY)
    private Origem idorigem ;
    @JoinColumn(name = "IDFuncionario", referencedColumnName = "IDFuncionario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funcionario iDFuncionario;
    @JoinColumn(name = "IDFornecedor", referencedColumnName = "IDFornecedor")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fornecedor iDFornecedor;
    @JoinColumn(name = "IDDocumento", referencedColumnName = "IDDocumento")
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Documento iDDocumento;
    @JoinColumn(name = "IDCliente", referencedColumnName = "IDCliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente iDCliente;
    @JoinColumn(name = "IDClassificacao", referencedColumnName = "IDClassificacao")
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Classificacao iDClassificacao;
    @JoinColumn(name = "IDCCusto", referencedColumnName = "IDCCusto")
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Ccusto iDCCusto;
    @JoinColumn(name = "IDAtividade", referencedColumnName = "IDAtividade")
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Atividade iDAtividade;
    @JoinColumn(name = "IDConta",referencedColumnName = "IDConta")
    @ManyToOne(fetch = FetchType.EAGER)
    private Conta IDConta;
    @Column(name = "tipo_juros")
    private Boolean tipoJuros;
    @Column(name ="pago")
    private boolean pago;
    @Column(name = "atz_pg")
    private Integer atzPg;
    @Column(name = "dt_atz")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtz;

    public Despesa() {
    }

    public Despesa(Integer iDDespesa) {
        this.iDDespesa = iDDespesa;
    }

    public Despesa(Integer iDDespesa, Date dTInc, Date dTVencimento, Date dTEmissao, BigDecimal valorLiquido, BigDecimal valorMulta, BigDecimal valorJuros, BigDecimal valorDesconto, boolean isentoMulta, boolean isentoJuros, BigDecimal valorNominal, String numero) {
        this.iDDespesa = iDDespesa;
        this.dTInc = dTInc;
        this.dTVencimento = dTVencimento;
        this.dTEmissao = dTEmissao;
        this.valorLiquido = valorLiquido;
        this.valorMulta = valorMulta;
        this.valorJuros = valorJuros;
        this.valorDesconto = valorDesconto;
        this.isentoMulta = isentoMulta;
        this.isentoJuros = isentoJuros;
        this.valorNominal = valorNominal;
        this.numero = numero;
       
    }

    public boolean getDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public Integer getIDDespesa() {
        return iDDespesa;
    }

    public void setIDDespesa(Integer iDDespesa) {
        this.iDDespesa = iDDespesa;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Date getDTVencimento() {
        return dTVencimento;
    }

    public void setDTVencimento(Date dTVencimento) {
        this.dTVencimento = dTVencimento;
    }

    public Date getDTEmissao() {
        return dTEmissao;
    }

    public void setDTEmissao(Date dTEmissao) {
        this.dTEmissao = dTEmissao;
    }

    public BigDecimal getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }

    public Date getDTMulta() {
        return dTMulta;
    }

    public void setDTMulta(Date dTMulta) {
        this.dTMulta = dTMulta;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Date getDTJuros() {
        return dTJuros;
    }

    public void setDTJuros(Date dTJuros) {
        this.dTJuros = dTJuros;
    }

    public boolean getIsentoMulta() {
        return isentoMulta;
    }

    public void setIsentoMulta(boolean isentoMulta) {
        this.isentoMulta = isentoMulta;
    }

    public boolean getIsentoJuros() {
        return isentoJuros;
    }

    public void setIsentoJuros(boolean isentoJuros) {
        this.isentoJuros = isentoJuros;
    }

    public Date getDTDesconto() {
        return dTDesconto;
    }

    public void setDTDesconto(Date dTDesconto) {
        this.dTDesconto = dTDesconto;
    }

    public BigDecimal getValorNominal() {
        return valorNominal;
    }

    public void setValorNominal(BigDecimal valorNominal) {
        this.valorNominal = valorNominal;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getDTCancelado() {
        return dTCancelado;
    }

    public void setDTCancelado(Date dTCancelado) {
        this.dTCancelado = dTCancelado;
    }

    public String getObsCancelado() {
        return obsCancelado;
    }

    public void setObsCancelado(String obsCancelado) {
        this.obsCancelado = obsCancelado;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    public Usuario getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuário) {
        this.iDUsuario = iDUsuário;
    }

    public Origem getIdorigem() {
        return idorigem;
    }

    public void setIdorigem(Origem idorigem) {
        this.idorigem = idorigem;
    }

    public Funcionario getIDFuncionario() {
        return iDFuncionario;
    }

    public void setIDFuncionario(Funcionario iDFuncionario) {
        this.iDFuncionario = iDFuncionario;
    }

    public Fornecedor getIDFornecedor() {
        return iDFornecedor;
    }

    public void setIDFornecedor(Fornecedor iDFornecedor) {
        this.iDFornecedor = iDFornecedor;
    }

    public Documento getIDDocumento() {
        return iDDocumento;
    }
    

    public void setIDDocumento(Documento iDDocumento) {
        this.iDDocumento = iDDocumento;
    }

    public Conta getIDConta() {
        return IDConta;
    }

    public void setIDConta(Conta IDConta) {
        this.IDConta = IDConta;
    }
    

    public Cliente getIDCliente() {
        return iDCliente;
    }

    public void setIDCliente(Cliente iDCliente) {
        this.iDCliente = iDCliente;
    }

    public Classificacao getIDClassificacao() {
        return iDClassificacao;
    }

    public void setIDClassificacao(Classificacao iDClassificacao) {
        this.iDClassificacao = iDClassificacao;
    }

    public Ccusto getIDCCusto() {
        return iDCCusto;
    }

    public void setIDCCusto(Ccusto iDCCusto) {
        this.iDCCusto = iDCCusto;
    }

    public Atividade getIDAtividade() {
        return iDAtividade;
    }

    public void setIDAtividade(Atividade iDAtividade) {
        this.iDAtividade = iDAtividade;
    }
    

    public Boolean getTipoJuros() {
        return tipoJuros;
    }

    public void setTipoJuros(Boolean tipoJuros) {
        this.tipoJuros = tipoJuros;
    }

    public boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Integer getAtzPg() {
        return atzPg;
    }

    public void setAtzPg(Integer atzPg) {
        this.atzPg = atzPg;
    }

    public Date getDtAtz() {
        return dtAtz;
    }

    public void setDtAtz(Date dtAtz) {
        this.dtAtz = dtAtz;
    }
    

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDDespesa != null ? iDDespesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despesa)) {
            return false;
        }
        Despesa other = (Despesa) object;
        if ((this.iDDespesa == null && other.iDDespesa != null) || (this.iDDespesa != null && !this.iDDespesa.equals(other.iDDespesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Despesa[ iDDespesa=" + iDDespesa + " ]";
    }
    
}
