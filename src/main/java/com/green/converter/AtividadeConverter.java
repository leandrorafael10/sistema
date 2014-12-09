/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.modelo.Atividade;
import com.green.rn.AtividadeRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("atividadeConverter")
public class AtividadeConverter implements Converter {

    private AtividadeRN atividadeRN;

    public AtividadeConverter() {
        this.atividadeRN = (AtividadeRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "atividadeRN");
    }

    public AtividadeRN getAtividadeRN() {
        return atividadeRN;
    }

    public void setAtividadeRN(AtividadeRN atividadeRN) {
        this.atividadeRN = atividadeRN;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return getAtividadeRN().carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            return ((Atividade) value).getIDAtividade().toString();
        }
        return null;
    }
}
