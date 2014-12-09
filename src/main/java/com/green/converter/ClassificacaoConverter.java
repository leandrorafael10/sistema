/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.modelo.Classificacao;
import com.green.rn.ClassificacaoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("classificacaoConverter")
public class ClassificacaoConverter implements Converter {

    private ClassificacaoRN classificacaoRN;

    public ClassificacaoConverter() {
        this.classificacaoRN = (ClassificacaoRN) FacesContext.getCurrentInstance().getELContext().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "classificacaoRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return getClassificacaoRN().carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            return ((Classificacao) value).getIDClassificacao().toString();
        }
        return null;
    }

    public ClassificacaoRN getClassificacaoRN() {
        return classificacaoRN;
    }

    public void setClassificacaoRN(ClassificacaoRN classificacaoRN) {
        this.classificacaoRN = classificacaoRN;
    }
}
