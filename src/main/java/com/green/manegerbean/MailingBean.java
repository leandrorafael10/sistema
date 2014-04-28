/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Funcionario;
import com.green.modelo.Mailing;
import com.green.modelo.Obsmailing;
import com.green.rn.MailingRN;
import com.green.rn.ObsMailingRN;
import com.green.util.ContextoUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
@ManagedBean(name = "mailingBean")
@ViewScoped
public class MailingBean implements Serializable {

    private Mailing mailing;
    private Mailing mailingAtualizar;
    @ManagedProperty("#{mailingRN}")
    private MailingRN mailingRN;
    @ManagedProperty("#{obsMailingRN}")
    private ObsMailingRN obsMailingRN;
    private static final int LIVRE = 1;
    private static final int PROSPEQUITANDO = 2;
    private static final int NEGOCIACAO = 3;
    private static final int FINALIZADO = 4;
    private Obsmailing obsmailing;
    private Date dataObs;
    private Funcionario funcionario  = new Funcionario();
    private List<Mailing> mailings = new ArrayList<>();

    public MailingBean() {
        mailing = new Mailing();
        mailingAtualizar = new Mailing();
        obsmailing = new Obsmailing();
        mailing.setIDobsMailing(new ArrayList<Obsmailing>());
        Calendar d2 = Calendar.getInstance();
        d2.add(Calendar.DAY_OF_MONTH,10);
        this.dataObs = d2.getTime();
    }

    public void salvar() {
        getMailing().setStatus(LIVRE);
        getMailingRN().salvar(getMailing());
        this.mailing = new Mailing();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", "Salvo com sucesso!"));
    }

    public void addObs(ActionEvent event) {
        getObsmailing().setIDFuncionario(ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario());
        getObsmailing().setDTObs(new Date());
        getObsmailing().setIDMailing(getMailingAtualizar());
        getObsMailingRN().salvar(getObsmailing());
        getMailingAtualizar().getIDobsMailing().add(getObsmailing());
        setObsmailing(new Obsmailing());
    }

    public void separarMailing(Mailing m) {
        RequestContext context = RequestContext.getCurrentInstance();
        if (m.getStatus() == 1) {
            setMailingAtualizar(m);
            context.update("formConfirSeparar");
            context.execute("dialogMailingSep.show()");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Cliente não liberado para prospequicão!", "Cliente não liberado para prospequicão!"));
            context.update("forCadastroMailing:msgMailing");

        }

    }

    public void atualizar(int opcao) {
        switch (opcao) {
            case LIVRE:
                getMailingAtualizar().setStatus(LIVRE);
                getMailingAtualizar().setDTvalidade(null);
                getMailingAtualizar().setIDFuncionario(null);
                getMailingRN().atualizar(getMailingAtualizar());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Liberado com sucesso!", "Liberado com sucesso!"));
                break;
            case PROSPEQUITANDO:
                //Date d = new Date();
                Calendar d2 = Calendar.getInstance();
                d2.add(Calendar.DAY_OF_MONTH, 30);
                getMailingAtualizar().setStatus(PROSPEQUITANDO);
                getMailingAtualizar().setDTvalidade(d2.getTime());
                getMailingAtualizar().setIDFuncionario(ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario());
                getMailingRN().atualizar(getMailingAtualizar());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", "Salvo com sucesso!"));
                break;
            case NEGOCIACAO:
                getMailingAtualizar().setStatus(NEGOCIACAO);
                getMailingRN().atualizar(getMailingAtualizar());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", "Salvo com sucesso!"));
                break;
            
        }
    }
    
    public String levarProContrato()  
    {  
        //Pega o id do objeto enviado no click do link "Editar"  
        Integer id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));  
        //Insere o id no escopo de sessão  
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("meuObjetoId", id);  
        //Retorna o endereço da pagina de edicao para realizar a navegação...  
        return "/principal/cadastro_cliente.xhtml";  
    }  

    public List<Mailing> listaAtivVend() {
       return  getMailingRN().listaAtivVend(getFuncionario());
    }

    public Mailing getMailing() {
        return mailing;
    }

    public void setMailing(Mailing mailing) {
        this.mailing = mailing;
    }

    public MailingRN getMailingRN() {
        return mailingRN;
    }

    public void setMailingRN(MailingRN mailingRN) {
        this.mailingRN = mailingRN;
    }

    public Mailing getMailingAtualizar() {
        return mailingAtualizar;
    }

    public void setMailingAtualizar(Mailing mailingAtualizar) {
        this.mailingAtualizar = mailingAtualizar;
    }

    public Obsmailing getObsmailing() {
        return obsmailing;
    }

    public void setObsmailing(Obsmailing obsmailing) {
        this.obsmailing = obsmailing;
    }

    public ObsMailingRN getObsMailingRN() {
        return obsMailingRN;
    }

    public void setObsMailingRN(ObsMailingRN obsMailingRN) {
        this.obsMailingRN = obsMailingRN;
    }

    public String primeiraPalavra(String frase) {
        String palavra = "";
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) != ' ') {
                palavra = palavra + frase.charAt(i);
            } else {
                break;
            }
        }
        return palavra;
    }

    public Date getDataObs() {
        return dataObs;
    }

    public void setDataObs(Date dataObs) {
        this.dataObs = dataObs;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Mailing> getMailings() {
        return mailings;
    }

    public void setMailings(List<Mailing> mailings) {
        this.mailings = mailings;
    }
    
    
}
