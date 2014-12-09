/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.modelo.Filial;
import com.green.rn.FilialRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("filialConverter")
public class FilialConverter implements Converter{
    
    
    private FilialRN filialRN;

    public FilialConverter() {
        this.filialRN = (FilialRN) FacesContext.getCurrentInstance().getELContext()
                .getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "filialRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return (Filial) this.filialRN.buscar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            if (((Filial) value).getIDFilial() != null) {
                return ((Filial) value).getIDFilial().toString();
            }
        }
        return null;

    }
    
}
