/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.view;

import com.green.modelo.Receita;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author leandro.silva
 */
public class ReceitaDataModelo extends ListDataModel<Receita> implements SelectableDataModel<Receita> {

    public ReceitaDataModelo() {
    }

    public ReceitaDataModelo(List<Receita> list) {
        super(list);
    }

    
    
    @Override
    public Object getRowKey(Receita t) {
        return t.getIDReceita().toString();
    }

    @Override
    public Receita getRowData(String string) {
        List<Receita> despesa = (List<Receita>) getWrappedData();
        for (Receita d : despesa) {
            if (d.getIDReceita().toString().equals(string)) {
                return d;
            }

        }
        return null;
    }
    
}
