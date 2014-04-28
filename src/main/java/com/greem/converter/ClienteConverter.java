/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greem.converter;

import com.green.modelo.Cliente;
import com.green.rn.ClienteRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

    private ClienteRN clienteRN;

    public ClienteConverter() {
        this.clienteRN = (ClienteRN) FacesContext.getCurrentInstance().getELContext()
                .getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "clienteRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return (Cliente) this.clienteRN.carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            if (((Cliente) value).getIDCliente() != null) {
                return ((Cliente) value).getIDCliente().toString();
            }
        }
        return null;

    }
}