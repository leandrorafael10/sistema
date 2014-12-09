/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    @Lob
    @Column(name = "Imagem")
    private byte[] imagem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTAdmissao")
    @Temporal(TemporalType.DATE)
    private Date dTAdmissao;
    @Column(name = "DTDemissao")
    @Temporal(TemporalType.DATE)
    private Date dTDemissao;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name = "DtValidadeCNH")
    @Temporal(TemporalType.DATE)
    private Date dtValidadeCNH;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDFuncionario")
    private Integer iDFuncionario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "PisPasep")
    private String pisPasep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Ctrabalho")
    private String ctrabalho;
    @Size(max = 50)
    @Column(name = "FiliacaoP")
    private String filiacaoP;
    @Size(max = 50)
    @Column(name = "FiliacaoM")
    private String filiacaoM;
    @Lob
    @Size(max = 65535)
    @Column(name = "Obs")
    private String obs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ativo")
    private boolean ativo;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @Size(max = 120)
    @Column(name = "MotivoDemissao")
    private String motivoDemissao;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Sexo")
    private char sexo;
    @Size(max = 15)
    @Column(name = "Cnh")
    private String cnh;
    @Size(max = 2)
    @Column(name = "CnhCategoria")
    private String cnhCategoria;
    @Column(name = "operacional")
    private Integer operacional;
    @Column(name = "serieCtps")
    @Size(max = 10)
    private String serieCtps;
    @JoinColumn(name = "IDFilial", referencedColumnName = "IDFilial")
    @ManyToOne(optional = false)
    private Filial iDFilial;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDPessoa", referencedColumnName = "IDPessoa")
    @ManyToOne(optional = false)
    private Pessoa iDPessoa;
    @JoinColumn(name = "IDFuncao", referencedColumnName = "IDFuncao")
    @ManyToOne(optional = false)
    private Funcao iDFuncao;
    @JoinColumn(name = "idfornecedor", referencedColumnName = "IDFornecedor")
    @ManyToOne
    private Fornecedor idfornecedor;
    @OneToMany(mappedBy = "iDFuncionario")
    private List<HistoricoDemicao> iDHistoricoDemicao;

    public Funcionario() {
    }

    public Funcionario(Integer iDFuncionario) {
        this.iDFuncionario = iDFuncionario;
    }

    public Funcionario(Integer iDFuncionario, String pisPasep, String ctrabalho, Date dTAdmissao, boolean ativo, Date dTInc, Filial iDFilial, char sexo) {
        this.iDFuncionario = iDFuncionario;
        this.pisPasep = pisPasep;
        this.ctrabalho = ctrabalho;
        this.dTAdmissao = dTAdmissao;
        this.ativo = ativo;
        this.dTInc = dTInc;
        this.iDFilial = iDFilial;
        this.sexo = sexo;
    }

    public Integer getIDFuncionario() {
        return iDFuncionario;
    }

    public void setIDFuncionario(Integer iDFuncionario) {
        this.iDFuncionario = iDFuncionario;
    }

    public String getPisPasep() {
        return pisPasep;
    }

    public void setPisPasep(String pisPasep) {
        this.pisPasep = pisPasep;
    }

    public String getCtrabalho() {
        return ctrabalho;
    }

    public void setCtrabalho(String ctrabalho) {
        this.ctrabalho = ctrabalho;
    }

    public String getFiliacaoP() {
        return filiacaoP;
    }

    public void setFiliacaoP(String filiacaoP) {
        this.filiacaoP = filiacaoP;
    }

    public String getFiliacaoM() {
        return filiacaoM;
    }

    public void setFiliacaoM(String filiacaoM) {
        this.filiacaoM = filiacaoM;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Integer iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public String getMotivoDemissao() {
        return motivoDemissao;
    }

    public void setMotivoDemissao(String motivoDemissao) {
        this.motivoDemissao = motivoDemissao;
    }

    public Filial getIDFilial() {
        return iDFilial;
    }

    public void setIDFilial(Filial iDFilial) {
        this.iDFilial = iDFilial;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCnhCategoria() {
        return cnhCategoria;
    }

    public void setCnhCategoria(String cnhCategoria) {
        this.cnhCategoria = cnhCategoria;
    }

    public Integer getOperacional() {
        return operacional;
    }

    public void setOperacional(Integer operacional) {
        this.operacional = operacional;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Pessoa getIDPessoa() {
        return iDPessoa;
    }

    public void setIDPessoa(Pessoa iDPessoa) {
        this.iDPessoa = iDPessoa;
    }

    public Funcao getIDFuncao() {
        return iDFuncao;
    }

    public void setIDFuncao(Funcao iDFuncao) {
        this.iDFuncao = iDFuncao;
    }

    public Fornecedor getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(Fornecedor idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDFuncionario != null ? iDFuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.iDFuncionario == null && other.iDFuncionario != null) || (this.iDFuncionario != null && !this.iDFuncionario.equals(other.iDFuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Funcionario[ iDFuncionario=" + iDFuncionario + " ]";
    }

    public Date getDTAdmissao() {
        return dTAdmissao;
    }

    public void setDTAdmissao(Date dTAdmissao) {
        this.dTAdmissao = dTAdmissao;
    }

    public Date getDTDemissao() {
        return dTDemissao;
    }

    public void setDTDemissao(Date dTDemissao) {
        this.dTDemissao = dTDemissao;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Date getDtValidadeCNH() {
        return dtValidadeCNH;
    }

    public void setDtValidadeCNH(Date dtValidadeCNH) {
        this.dtValidadeCNH = dtValidadeCNH;
    }

    public String getSerieCtps() {
        return serieCtps;
    }

    public void setSerieCtps(String serieCtps) {
        this.serieCtps = serieCtps;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public List<HistoricoDemicao> getIDHistoricoDemicao() {
        return iDHistoricoDemicao;
    }

    public void setIDHistoricoDemicao(List<HistoricoDemicao> iDHistoricoDemicao) {
        this.iDHistoricoDemicao = iDHistoricoDemicao;
    }

}
