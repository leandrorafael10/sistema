/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.modelo.Conta;
import com.green.rn.ContaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("contaConverter")
public class ContaConverter implements Converter {

    private ContaRN contaRN;

    public ContaConverter() {
        this.contaRN = (ContaRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "contaRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return (Conta) this.contaRN.carregar(Integer.parseInt(value));
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null && !value.equals("")) {
            if (((Conta) value).getIDConta() != null) {
                return ((Conta) value).getIDConta().toString();
            }
        }
        return null;

    }
}
