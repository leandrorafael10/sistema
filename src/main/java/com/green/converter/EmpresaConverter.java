/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.dao.EmpresaDAO;
import com.green.modelo.Empresa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("empresaConverter")
public class EmpresaConverter implements Converter {

    private EmpresaDAO empresaDAO;

    public EmpresaConverter() {
        this.empresaDAO = (EmpresaDAO) FacesContext.getCurrentInstance().getELContext().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "empresaDAO");

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return getEmpresaDAO().carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            if (((Empresa) value).getIDEmpresa() != null) {
                return ((Empresa) value).getIDEmpresa().toString();
            }
        }
        return null;
    }

    public EmpresaDAO getEmpresaDAO() {
        return empresaDAO;
    }

    public void setEmpresaDAO(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }

}
