/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greem.converter;

import com.green.modelo.Equipevenda;
import com.green.rn.EquipevendaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("equipevendaConverter")
public class EquipevendaConverter implements Converter{
    
    private EquipevendaRN equipevendaRN;

    public EquipevendaConverter() {
        this.equipevendaRN = (EquipevendaRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "equipevendaRN");
    }
    
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if (value != null && !value.equals("")) {
            return this.equipevendaRN.carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         if (value != null && !value.equals("")) {
            if (((Equipevenda) value).getIdequipevenda() != null) {
                return ((Equipevenda) value).getIdequipevenda().toString();
            }
        }
        return null;
    }
    
}
