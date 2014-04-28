/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Pessoa;
import com.green.modelo.Teste;
import com.green.modelo.Usuario;
import com.green.rn.TesteRN;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="testeBean")
@ViewScoped
public class TesteBean implements Serializable{
    
    @ManagedProperty("#{testeRN}")
    private TesteRN testeRN;
    
    private Teste teste = new Teste();
     private Pessoa user = new Pessoa();  
      
    private boolean skip;  
      
    private static Logger logger = Logger.getLogger(Pessoa.class.getName());  
  
    public Pessoa getUser() {  
        return user;  
    }  
  
    public void setUser(Pessoa user) {  
        this.user = user;  
    }  
      
    public void save(ActionEvent actionEvent) {  
        //Persist user  
          
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getRazao());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public boolean isSkip() {  
        return skip;  
    }  
  
    public void setSkip(boolean skip) {  
        this.skip = skip;  
    }  
      
    public String onFlowProcess(FlowEvent event) {  
        logger.info("Current wizard step:" + event.getOldStep());  
        logger.info("Next step:" + event.getNewStep());  
          
        if(skip) {  
            skip = false;   //reset in case user goes back  
            return "confirm";  
        }  
        else {  
            return event.getNewStep();  
        }  
    }  

    

    public TesteRN getTesteRN() {
        return testeRN;
    }

    public void setTesteRN(TesteRN testeRN) {
        this.testeRN = testeRN;
    }
    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }
    public void salvar (){
       getTesteRN().salvar(this.getTeste());
    }
    
}
