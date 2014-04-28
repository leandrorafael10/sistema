/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greem.converter;

import com.green.modelo.Banco;
import com.green.rn.BancoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("bancoConverter")
public class BancoConverter implements Converter{
    
    private BancoRN bancoRN;

    public BancoConverter() {
        this.bancoRN = (BancoRN)FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "bancoRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
             return (Banco)this.bancoRN.carregar(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
           return ((Banco)value).getIDBanco().toString();
    }
    
}
