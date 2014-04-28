/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.view;

import com.green.modelo.Despesa;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author leandro.silva
 */
public class DespesaDataModelo extends ListDataModel<Despesa> implements SelectableDataModel<Despesa> {

    public DespesaDataModelo(List<Despesa> list) {
        super(list);
    }

    public DespesaDataModelo() {
    }

    @Override
    public Object getRowKey(Despesa t) {
        return t.getIDDespesa().toString();
    }

    @Override
    public Despesa getRowData(String string) {
        List<Despesa> despesa = (List<Despesa>) getWrappedData();
        for (Despesa d : despesa) {
            if (d.getIDDespesa().toString().equals(string)) {
                return d;
            }

        }
        return null;
    }
}
