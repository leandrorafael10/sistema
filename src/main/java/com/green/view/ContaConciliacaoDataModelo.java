/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.view;

import com.green.util.ContaConciliacao;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author leandro.silva
 */
public class ContaConciliacaoDataModelo extends ListDataModel<ContaConciliacao> implements SelectableDataModel<ContaConciliacao> {

    public ContaConciliacaoDataModelo(List<ContaConciliacao> list) {
        super(list);
    }

    public ContaConciliacaoDataModelo() {
    }

    @Override
    public Object getRowKey(ContaConciliacao t) {
        return t.getConta().getIDConta();
    }

    @SuppressWarnings("unchecked")
	@Override
    public ContaConciliacao getRowData(String string) {
        List<ContaConciliacao> contaConciliacao = (List<ContaConciliacao>) getWrappedData();
        for (ContaConciliacao c : contaConciliacao) {
            if (c.getConta().getIDConta().toString().equals(string)) {
                return c;
            }

        }
        return null;
    }
}
