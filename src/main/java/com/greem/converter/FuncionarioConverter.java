/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greem.converter;

import com.green.modelo.Funcionario;
import com.green.rn.FuncionarioRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("funcionarioConverter")
public class FuncionarioConverter implements Converter {

    private FuncionarioRN funcionarioRN;

    public FuncionarioConverter() {
        this.funcionarioRN = (FuncionarioRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "funcionarioRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")&&!value.equals("todos")) {
            return this.funcionarioRN.carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            if (((Funcionario) value).getIDFuncionario() != null) {
                return ((Funcionario) value).getIDFuncionario().toString();
            }
        }
        return null;

    }
}
