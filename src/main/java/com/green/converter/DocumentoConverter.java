/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.modelo.Documento;
import com.green.rn.DocumentoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("documentoConverter")
public class DocumentoConverter implements Converter{
    
    private DocumentoRN documentoRN;

    public DocumentoConverter() {
        this.documentoRN = (DocumentoRN)FacesContext.getCurrentInstance().getELContext()
                .getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "documentoRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return this.documentoRN.carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if (value != null && !value.equals("")) {
            return  ((Documento)value).getIDDocumento().toString();
        }
        return null;
    }

    
    
}
