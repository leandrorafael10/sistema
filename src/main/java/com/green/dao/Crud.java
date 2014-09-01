/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.dao;

import java.util.List;

/**
 *
 * @author leandro.silva
 * @param <Objeto>
 */
public interface Crud <Objeto>{
    
    public void salvar(Objeto o);
    public  void excluir(Objeto o);
    public void atualizar(Objeto o);
    public List<Objeto> listar();
}
