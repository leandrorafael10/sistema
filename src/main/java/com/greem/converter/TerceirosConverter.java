/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greem.converter;

import com.green.modelo.Terceiros;
import com.green.rn.TerceirosRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("terceirosConverter")
public class TerceirosConverter implements Converter {

    private TerceirosRN terceirosRN;

    public TerceirosConverter() {
        terceirosRN = (TerceirosRN) FacesContext.getCurrentInstance().getELContext().getELResolver()
                .getValue(FacesContext.getCurrentInstance().getELContext(), null, "terceirosRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return this.terceirosRN.carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            return ((Terceiros) value).getIDTerceiros().toString();
        }
        return null;
    }
}
