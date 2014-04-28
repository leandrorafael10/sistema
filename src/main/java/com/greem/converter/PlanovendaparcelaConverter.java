/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greem.converter;

import com.green.modelo.Planovendaparcela;
import com.green.rn.PlanovendaparcelaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("planovendaparcelaConverter")
public class PlanovendaparcelaConverter implements Converter{

   private PlanovendaparcelaRN planovendaparcelaRN;

    public PlanovendaparcelaConverter() {
        this.planovendaparcelaRN = (PlanovendaparcelaRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "planovendaparcelaRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")&&!value.equals("todos")) {
            return this.planovendaparcelaRN.buscar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            if (((Planovendaparcela) value).getIDPlanoVendaParcela() != null) {
                return ((Planovendaparcela) value).getIDPlanoVendaParcela().toString();
            }
        }
        return null;

    }
    
}
