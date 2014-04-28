/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greem.converter;

import com.green.modelo.Fornecedor;
import com.green.rn.FornecedorRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter ("fornecedorConverter")
public class FornecedorConverter implements Converter {

    private FornecedorRN fornecedorRN;

    public FornecedorConverter() {
        this.fornecedorRN = (FornecedorRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "fornecedorRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return getFornecedorRN().buscar(Integer.parseInt(value));
        } 
            return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
             return ((Fornecedor)value).getIDFornecedor().toString();
        }
            return null;
        
       
    }

    public FornecedorRN getFornecedorRN() {
        return fornecedorRN;
    }

    public void setFornecedorRN(FornecedorRN fornecedorRN) {
        this.fornecedorRN = fornecedorRN;
    }
}
