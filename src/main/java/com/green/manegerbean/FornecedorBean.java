/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Contato;
import com.green.modelo.Fornecedor;
import com.green.modelo.Pessoa;
import com.green.rn.ContatoRN;
import com.green.rn.FornecedorRN;
import com.green.rn.PessoaRN;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "fornecedorBean")
@ViewScoped
public class FornecedorBean implements Serializable {
    
    @ManagedProperty("#{fornecedorRN}")
    private FornecedorRN fornecedorRN;
    @ManagedProperty("#{contatoRN}")
    private ContatoRN contatoRN;
    @ManagedProperty("#{pessoaRN}")
    private PessoaRN pessoaRN;
    private List<Fornecedor> fornecedores;
    private List<Fornecedor> fornecedoresFiltro;
    private Fornecedor fornecedorNovo = new Fornecedor();
    private Fornecedor fornecedorEditado = new Fornecedor();
    private Contato contato;
    private String telefoneF = new String();
    private String telefoneC = new String();
    private String telefoneCel = new String();
    private String telefoneR = new String();
    private String serieCTPS = new String();
    
    @PostConstruct
    public void unit() {
        setContato(new Contato());
        setFornecedores(getFornecedorRN().listar());
        getFornecedorNovo().setIDPessoa(new Pessoa());
        getFornecedorEditado().setIDPessoa(new Pessoa());
    }
    
    public void confirmaSalvar(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formFornecedorDG");
        context.execute("PF('dialogCadFornecedor').show()");
        
        
    }
    
    public void salvar() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext rc = RequestContext.getCurrentInstance();
        if (!buscaCpfCnpj(getFornecedorNovo().getIDPessoa().getCnpjCpf())) {
            rc.execute("PF('dialogError').show()");
            
        } else {
            formatatel(getContato(), telefoneF, telefoneCel, telefoneR, telefoneC);
            boolean retorno = getFornecedorRN().salvar(getFornecedorNovo(), getContato());
            if (retorno) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", "Salvo com sucesso!"));
                setFornecedorNovo(new Fornecedor());
                getFornecedorNovo().setIDPessoa(new Pessoa());
                setContato(new Contato());
                rc.update("formCadFornecedor");
                rc.execute("PF('dialogCadFornecedor').hide()");
                
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha! Usuário nao autorizado!", "Falha! Usuário nao autorizado!"));
                rc.update("formCadFornecedor");
                rc.execute("PF('dialogCadFornecedor').hide()");
                
            }
        }
        
    }
    
    public void alterar(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext rc = RequestContext.getCurrentInstance();
        formatatel(getContato(), telefoneF, telefoneCel, telefoneR, telefoneC);
        boolean retorno = getFornecedorRN().alterar(getFornecedorEditado(), getContato());
        if (retorno) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!", "Alterado com sucesso!"));
            setFornecedores(getFornecedorRN().listar());
            setFornecedorNovo(new Fornecedor());
            setContato(new Contato());
            setTelefoneC("");
            setTelefoneCel("");
            setTelefoneF("");
            setTelefoneR("");
            rc.update("formListaFornecedor");
            rc.execute("PF('fornecedorDialogEditar').hide()");
            
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha! Usuário nao autorizado!", "Falha! Usuário nao autorizado!"));
            rc.update("formListaFornecedor");
            rc.execute("PF('fornecedorDialogEditar').hide()");
            
        }
    }
    
    public void formatatel(Contato c, String fixo, String celular, String residencial, String comercial) {
        if (!fixo.equals("")) {
            c.setDddf(fixo.substring(1, 3));
            c.setTelefoneF(fixo.substring(4, 8) + fixo.substring(9));
        } else {
            c.setDddf("");
            c.setTelefoneF("");
        }
        if (!celular.equals("")) {
            c.setDDDCel(celular.substring(1, 3));
            c.setTelefoneCel(celular.substring(4, 8) + celular.substring(9));
        } else {
            c.setDDDCel("");
            c.setTelefoneCel("");
        }
        if (!comercial.equals("")) {
            c.setDddc(comercial.substring(1, 3));
            c.setTelefoneC(comercial.substring(4, 8) + comercial.substring(9));
        } else {
            c.setDddc("");
            c.setTelefoneC("");
        }
    }
    
    public FornecedorRN getFornecedorRN() {
        return fornecedorRN;
    }
    
    public void setFornecedorRN(FornecedorRN fornecedorRN) {
        this.fornecedorRN = fornecedorRN;
    }
    
    public ContatoRN getContatoRN() {
        return contatoRN;
    }
    
    public void setContatoRN(ContatoRN contatoRN) {
        this.contatoRN = contatoRN;
    }
    
    public Fornecedor getFornecedorNovo() {
        return fornecedorNovo;
    }
    
    public void setFornecedorNovo(Fornecedor fornecedorNovo) {
        Contato c;
        setTelefoneC("");
        setTelefoneCel("");
        setTelefoneF("");
        setTelefoneR("");
        c = getContatoRN().buscaPorContato(fornecedorNovo.getIDPessoa());
        if (c != null) {
            setContato(c);
            setTelefoneC("(" + c.getDddc() + ")" + c.getTelefoneC());
            setTelefoneCel("(" + c.getDDDCel() + ")" + c.getTelefoneCel());
            setTelefoneF("(" + c.getDddf() + ")" + c.getTelefoneF());
            setTelefoneR("(" + c.getDddr() + ")" + c.getTelefoneR());
        }
        this.fornecedorNovo = fornecedorNovo;
    }
    
    public Fornecedor getFornecedorEditado() {
        return fornecedorEditado;
    }
    
    public boolean buscaCpfCnpj(String cpf) {
        Pessoa p;
        p = getPessoaRN().buscaCpfCnpj(cpf);
        if (p != null) {
            return false;
            
        } else {
            return true;
        }
    }
    
    public void setFornecedorEditado(Fornecedor fornecedorEditado) {
        Contato c;
        setTelefoneC("");
        setTelefoneCel("");
        setTelefoneF("");
        setTelefoneR("");
        c = getContatoRN().buscaPorContato(fornecedorEditado.getIDPessoa());
        if (c != null) {
            setContato(c);
            setTelefoneC(c.getDddc() + c.getTelefoneC());
            setTelefoneCel(c.getDDDCel() + c.getTelefoneCel());
            setTelefoneF(c.getDddf() + c.getTelefoneF());
            setTelefoneR(c.getDddr() + c.getTelefoneR());
        }
        this.fornecedorEditado = fornecedorEditado;
    }
    
    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }
    
    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
    
    public List<Fornecedor> getFornecedoresFiltro() {
        return fornecedoresFiltro;
    }
    
    public void setFornecedoresFiltro(List<Fornecedor> fornecedoresFiltro) {
        this.fornecedoresFiltro = fornecedoresFiltro;
    }
    
    public Contato getContato() {
        return contato;
    }
    
    public void setContato(Contato contato) {
        this.contato = contato;
    }
    
    public String getTelefoneF() {
        return telefoneF;
    }
    
    public void setTelefoneF(String telefoneF) {
        this.telefoneF = telefoneF;
    }
    
    public String getTelefoneC() {
        return telefoneC;
    }
    
    public void setTelefoneC(String telefoneC) {
        this.telefoneC = telefoneC;
    }
    
    public String getTelefoneCel() {
        return telefoneCel;
    }
    
    public void setTelefoneCel(String telefoneCel) {
        this.telefoneCel = telefoneCel;
    }
    
    public String getTelefoneR() {
        return telefoneR;
    }
    
    public void setTelefoneR(String telefoneR) {
        this.telefoneR = telefoneR;
    }
    
    public String getSerieCTPS() {
        return serieCTPS;
    }
    
    public void setSerieCTPS(String serieCTPS) {
        this.serieCTPS = serieCTPS;
    }
    
    public PessoaRN getPessoaRN() {
        return pessoaRN;
    }
    
    public void setPessoaRN(PessoaRN pessoaRN) {
        this.pessoaRN = pessoaRN;
    }
}
