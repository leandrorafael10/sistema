/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.view;

import com.green.modelo.Capalotejornal;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author leandro.silva
 */
public class CapaloteDataModel extends ListDataModel<Capalotejornal> implements SelectableDataModel<Capalotejornal>{

    public CapaloteDataModel() {
    }

    public CapaloteDataModel(List<Capalotejornal> list) {
        super(list);
    }

   
    
    @Override
    public Object getRowKey(Capalotejornal t) {
        return t.getIDCapalotejornal();
    }

    @Override
    public Capalotejornal getRowData(String id) {
         List<Capalotejornal> capalote = (List<Capalotejornal>) getWrappedData();
         
        for(Capalotejornal car : capalote) {
            if(car.getIDCapalotejornal().toString().equals(id))
                return car;
        }
         
        return null;
    }
    
}
