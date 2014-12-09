/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.modelo.Ccusto;
import com.green.rn.CCustoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("ccustoConverter")
public class CCustoConverter implements Converter {

    private CCustoRN custoRN;

    public CCustoConverter() {
        this.custoRN = (CCustoRN) FacesContext.getCurrentInstance().getELContext().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "ccustoRN");

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return getCustoRN().carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            return ((Ccusto) value).getIDCCusto().toString();
        }
        return null;
    }

    public CCustoRN getCustoRN() {
        return custoRN;
    }

    public void setCustoRN(CCustoRN custoRN) {
        this.custoRN = custoRN;
    }
}
