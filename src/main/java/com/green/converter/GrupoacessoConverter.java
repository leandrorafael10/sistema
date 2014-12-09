/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.modelo.Grupoacesso;
import com.green.rn.GrupoAcessoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("grupoAcessoConverter")
public class GrupoacessoConverter implements Converter {

    private GrupoAcessoRN acessoRN;

    public GrupoacessoConverter() {
        this.acessoRN = (GrupoAcessoRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "grupoAcessoRN");
    }
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
            return this.acessoRN.carregar(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
            return ((Grupoacesso) value).getIDGrupoAcesso().toString();
    }
}
