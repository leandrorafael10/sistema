/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.view;

import com.green.modelo.Debito;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author leandro.silva
 */
public class DebitoDataModelo extends ListDataModel<Debito> implements SelectableDataModel<Debito> {

    public DebitoDataModelo(List<Debito> list) {
        super(list);
    }

    public DebitoDataModelo() {
    }

    @Override
    public Object getRowKey(Debito t) {
        return  t.getIDDebito().toString();
    }

    @Override
    public Debito getRowData(String string) {
        List<Debito> dedito= (List<Debito>)getWrappedData();
        for(Debito d : dedito){
            if(d.getIDDebito().toString().equals(string)){
                return d;
            }
            
        }
        return null;
    }

    
   
}
