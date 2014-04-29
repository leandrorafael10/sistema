/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.util;

/**
 *
 * @author leandro.silva
 */
public class MainTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CepWebService cepWebService=new CepWebService("32115110");
        System.out.println(cepWebService.getEstado());
        System.out.println(cepWebService.getCidade());
        System.out.println(cepWebService.getBairro());
        System.out.println(cepWebService.getLogradouro());
        System.out.println(cepWebService.getTipo_logradouro());
    }
    
}
