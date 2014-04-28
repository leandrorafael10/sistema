/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.List;

/**
 *
 * @author leandro.silva
 */
public abstract class AbstractDao<OBJ,K> {
    
    public abstract OBJ carregar(K k);
    public abstract K salvar(OBJ obj);
    public abstract List<OBJ> listar();
    public abstract void excluir(OBJ obj);
}
