/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "capalotejornal")
public class Capalotejornal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDCapalotejornal")
    private Integer iDCapalotejornal;
    @Column(name = "unidade")
    private Integer unidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contrato")
    private String contrato;
    @Size(max = 45)
    @Column(name = "gerador")
    private String gerador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nome")
    private String nome;
    @Column(name = "modalidade")
    private Integer modalidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_brinde")
    private boolean statusBrinde;
    @Column(name = "altLote")
    private Boolean altLote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @Column(name = "motivoAlt")
    private String motivoAlt;
    @OneToMany(mappedBy="iDCapalotejornal")
    private List<Vendaestorno> vendaestornoList;
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST}, mappedBy = "iDCapalotejornal",fetch = FetchType.LAZY)
    private List<Brindecapalote> brindecapaloteList;
    @JoinColumn(name = "IDGerente", referencedColumnName = "IDFuncionario")
    @ManyToOne
    private Funcionario iDGerente;
    @JoinColumn(name = "IDFuncionarioPromotor", referencedColumnName = "iDFuncionario")
    @ManyToOne
    private Funcionario iDFuncionarioPromotor;
    @JoinColumn (name = "IDPontovenda", referencedColumnName = "IDPontodeVenda")
    @ManyToOne
    private Pontodevenda iDPontovenda;
    
    @JoinColumn(name = "IDTipopagamento",referencedColumnName = "IDTipopagamento")
    @ManyToOne
    private Tipopagamento iDTipopagamento;
    @Column(name = "DTVenda")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dTVenda;
    @JoinColumn(name = "IDUsuario",referencedColumnName = "IDUsuario")
    @ManyToOne
    @NotNull
    private Usuario iDUsuario;
    @JoinColumn(name = "IDUsuarioAlt",referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioAlt;
    @Column(name = "DTinc")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTinc;
    @Column(name="DTIncGerador")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTIncGerador;
    @JoinColumn(name = "IDUsuarioGerador",referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioGerador;
    @Column(name="web_super")
    private boolean web_super;
    @Column(name="web_otempo")
    private boolean web_otempo;
    @Column(name="fisico_otempo")
    private boolean fisico_otempo;
    @Column(name="DTCancel")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTCancel;
    @Column(name="ObsCancel")
    private String obsCancel;
    

    public Capalotejornal() {
    }

    public Capalotejornal(Integer iDCapalotejornal) {
        this.iDCapalotejornal = iDCapalotejornal;
    }

    public Capalotejornal(Integer iDCapalotejornal, String contrato, String nome, int status, boolean statusBrinde, BigDecimal valor) {
        this.iDCapalotejornal = iDCapalotejornal;
        this.contrato = contrato;
        this.nome = nome;
        this.status = status;
        this.statusBrinde = statusBrinde;
        this.valor = valor;
    }

    public Integer getIDCapalotejornal() {
        return iDCapalotejornal;
    }

    public void setIDCapalotejornal(Integer iDCapalotejornal) {
        this.iDCapalotejornal = iDCapalotejornal;
    }

    public Integer getUnidade() {
        return unidade;
    }

    public void setUnidade(Integer unidade) {
        this.unidade = unidade;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getGerador() {
        return gerador;
    }

    public void setGerador(String gerador) {
        this.gerador = gerador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getModalidade() {
        return modalidade;
    }

    public void setModalidade(Integer modalidade) {
        this.modalidade = modalidade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getStatusBrinde() {
        return statusBrinde;
    }

    public void setStatusBrinde(boolean statusBrinde) {
        this.statusBrinde = statusBrinde;
    }

    public Boolean getAltLote() {
        return altLote;
    }

    public void setAltLote(Boolean altLote) {
        this.altLote = altLote;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Funcionario getIDGerente() {
        return iDGerente;
    }

    public void setIDGerente(Funcionario iDGerente) {
        this.iDGerente = iDGerente;
    }

    public Funcionario getIDFuncionarioPromotor() {
        return iDFuncionarioPromotor;
    }

    public void setIDFuncionarioPromotor(Funcionario iDFuncionarioPromotor) {
        this.iDFuncionarioPromotor = iDFuncionarioPromotor;
    }

    public Pontodevenda getIDPontovenda() {
        return iDPontovenda;
    }

    public void setIDPontovenda(Pontodevenda iDPontovenda) {
        this.iDPontovenda = iDPontovenda;
    }

    public Date getDTVenda() {
        return dTVenda;
    }

    public void setDTVenda(Date dTVenda) {
        this.dTVenda = dTVenda;
    }

    public Tipopagamento getIDTipopagamento() {
        return iDTipopagamento;
    }

    public void setIDTipopagamento(Tipopagamento iDTipopagamento) {
        this.iDTipopagamento = iDTipopagamento;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }
    

    public Date getDTinc() {
        return dTinc;
    }   

    public void setDTinc(Date dTinc) {
        this.dTinc = dTinc;
    }

    public Usuario getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }


   
    public List<Brindecapalote> getBrindecapaloteList() {
        return brindecapaloteList;
    }

    public void setBrindecapaloteList(List<Brindecapalote> brindecapaloteList) {
        this.brindecapaloteList = brindecapaloteList;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    public String getMotivoAlt() {
        return motivoAlt;
    }

    public void setMotivoAlt(String motivoAlt) {
        this.motivoAlt = motivoAlt;
    } 
    
    public Date getDTIncGerador() {
		return dTIncGerador;
	}

	public void setDTIncGerador(Date dTIncGerador) {
		this.dTIncGerador = dTIncGerador;
	}
	
	

	public Usuario getIDUsuarioGerador() {
		return iDUsuarioGerador;
	}

	public void setIDUsuarioGerador(Usuario iDUsuarioGerador) {
		this.iDUsuarioGerador = iDUsuarioGerador;
	}
	
	

	public boolean getWeb_super() {
		return web_super;
	}

	public void setWeb_super(boolean web_super) {
		this.web_super = web_super;
	}

	public boolean getWeb_otempo() {
		return web_otempo;
	}

	public void setWeb_otempo(boolean web_otempo) {
		this.web_otempo = web_otempo;
	}

	public boolean getFisico_otempo() {
		return fisico_otempo;
	}

	public void setFisico_otempo(boolean fisico_otempo) {
		this.fisico_otempo = fisico_otempo;
	}
	
	

	public Date getDTCancel() {
		return dTCancel;
	}

	public void setDTCancel(Date dTCancel) {
		this.dTCancel = dTCancel;
	}

	public String getObsCancel() {
		return obsCancel;
	}

	public void setObsCancel(String obsCancel) {
		this.obsCancel = obsCancel;
	}
	
	public List<Vendaestorno> getVendaestornoList() {
		return vendaestornoList;
	}

	public void setVendaestornoList(List<Vendaestorno> vendaestornoList) {
		this.vendaestornoList = vendaestornoList;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCapalotejornal != null ? iDCapalotejornal.hashCode() : 0);
        return hash;
    }
	
	

    

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capalotejornal)) {
            return false;
        }
        Capalotejornal other = (Capalotejornal) object;
        if ((this.iDCapalotejornal == null && other.iDCapalotejornal != null) || (this.iDCapalotejornal != null && !this.iDCapalotejornal.equals(other.iDCapalotejornal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Capalotejornal[ iDCapalotejornal=" + iDCapalotejornal + " ]";
    }
}
