/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.view;

import com.green.modelo.Conta;
import com.green.modelo.Credito;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author leandro.silva
 */
public class ContaDataModelo extends ListDataModel<Conta> implements SelectableDataModel<Conta>  {

    public ContaDataModelo(List<Conta> list) {
        super(list);
    }

    public ContaDataModelo() {
    }

    
    @Override
    public Object getRowKey(Conta t) {
        return t.getIDConta().toString();
    }

    @Override
    public Conta getRowData(String string) {
        List<Conta> conta= (List<Conta>)getWrappedData();
        for(Conta c : conta){
            if(c.getIDConta().toString().equals(string)){
                return c;
            }
            
        }
        return null;
    }
    
}
