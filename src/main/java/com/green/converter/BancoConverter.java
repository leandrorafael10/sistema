/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

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
public class BancoConverter implements Converter {

    private BancoRN bancoRN;

    public BancoConverter() {
        this.bancoRN = (BancoRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "bancoRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return (Banco) this.bancoRN.carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            if (((Banco) value).getIDBanco() != null) {
                return ((Banco) value).getIDBanco().toString();
            }
        }
        return null;

    }
}
