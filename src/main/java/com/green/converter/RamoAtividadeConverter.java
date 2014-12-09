/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.modelo.Ramoatividade;
import com.green.rn.RamoAtividadeRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("ramoAtividadeConverter")
public class RamoAtividadeConverter implements Converter{
    
    private RamoAtividadeRN ramoAtividadeRN = (RamoAtividadeRN)FacesContext.getCurrentInstance().getELContext().getELResolver().
            getValue(FacesContext.getCurrentInstance().getELContext(), null, "ramoAtividadeRN");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         if (value != null && !value.equals("")) {
             return getRamoAtividadeRN().buscar(Integer.parseInt(value));
         }
         return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            return ((Ramoatividade)value).getIDRamoAtividade().toString();
        }
        return null;
    }

    public RamoAtividadeRN getRamoAtividadeRN() {
        return ramoAtividadeRN;
    }

    public void setRamoAtividadeRN(RamoAtividadeRN ramoAtividadeRN) {
        this.ramoAtividadeRN = ramoAtividadeRN;
    }
    
    
}
