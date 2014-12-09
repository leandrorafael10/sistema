/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.modelo.Funcao;
import com.green.rn.FuncaoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("funcaoConverter")
public class FuncaoConverter implements Converter {

    private FuncaoRN funcaoRN;

    public FuncaoConverter() {
        this.funcaoRN = (FuncaoRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "funcaoRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return funcaoRN.carregar(Integer.parseInt(value));
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            if (((Funcao) value).getIDFuncao() != null) {
                return ((Funcao) value).getIDFuncao().toString();
            }
        }
        return null;

    }
}
