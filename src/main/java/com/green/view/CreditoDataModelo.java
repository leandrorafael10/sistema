/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.view;

import com.green.modelo.Credito;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author leandro.silva
 */
public class CreditoDataModelo extends ListDataModel<Credito> implements SelectableDataModel<Credito> {

    public CreditoDataModelo(List<Credito> list) {
        super(list);
    }

    public CreditoDataModelo() {
    }

    
    @Override
    public Object getRowKey(Credito t) {
        return t.getIDCredito().toString();
    }

    @Override
    public Credito getRowData(String string) {
        List<Credito> credito= (List<Credito>)getWrappedData();
        for(Credito c : credito){
            if(c.getIDCredito().toString().equals(string)){
                return c;
            }
            
        }
        return null;
    }
    
}
