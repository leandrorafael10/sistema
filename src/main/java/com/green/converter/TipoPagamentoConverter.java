/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.modelo.Tipopagamento;
import com.green.rn.TipoPagamentoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("tipoPagamentoConverter")
public class TipoPagamentoConverter implements Converter{
    
    TipoPagamentoRN tipoPagamentoRN;

    public TipoPagamentoConverter() {
        this.tipoPagamentoRN = (TipoPagamentoRN)FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "tipoPagamentoRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         if (value != null && !value.equals("")) {
            return this.tipoPagamentoRN.carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if (value != null && !value.equals("")) {
            if (((Tipopagamento) value).getIDTipoPagamento() != null) {
                return ((Tipopagamento) value).getIDTipoPagamento().toString();
            }
        }
        return null;
    }
    
}
