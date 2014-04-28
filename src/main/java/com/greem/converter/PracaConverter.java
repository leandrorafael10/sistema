/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greem.converter;

import com.green.modelo.Praca;
import com.green.rn.PracaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("pracaConverter")
public class PracaConverter implements Converter{

    private PracaRN pracaRN;
    
    public PracaConverter(){
        this.pracaRN = (PracaRN)FacesContext.getCurrentInstance().getELContext().
                getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "pracaRN");
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.pracaRN.carregar(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Praca)value).getIdpraca().toString();
    }
    
}