/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "formatarBean")
@ViewScoped
public class FormatarBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StreamedContent imagem = new DefaultStreamedContent();

    public static String formatatel(String fixo, String celular, String residencial, String comercial) {
        return null;
    }

    

    public static String maiuscula(String silva) {
        String mais = "";
        String maiuscula2;
        String tudo;
        String posicao = "" + silva.charAt(0);//pega a primeira letra que sera maiuscula  
        String pos = posicao.toUpperCase();    //transforma em maiuscula  

        for (int i = 1; i < silva.length(); i++) {  //coloca o for de acordo com o tamanho  
            mais = mais + silva.charAt(i); //acrescenta as letras  

            if (silva.charAt(i) == ' ') {  //se houver um espaço a próxima vai ser transformada em maisucula.  
                String maiuscula = "" + silva.charAt(i + 1);//pega a próxima apos o espaço    
                maiuscula2 = maiuscula.toUpperCase();       //transforma em maiuscula.  
                mais = mais + maiuscula2;//acrescenta a maiuscula a palavra completa, que se chama mais  
                i = i + 1; //soma um, pois uma letra minuscula foi substituida e ja foi acrescentada                                   
            }
        }
        tudo = pos + mais;
        System.out.println("tudo:" + tudo);
        return tudo;
    }

    public String stringMes(int mes) {
        switch (mes) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";
            default:
                return "";
        }

    }

    public StreamedContent imagem(byte[] imagem) {
        if (imagem != null) {
            StreamedContent imagemConvertida = new DefaultStreamedContent(new ByteArrayInputStream(imagem));
            return imagemConvertida;
        }
        return null;

    }

    public StreamedContent getSelectedFoto(byte[] imagem) {
        try {
            if (imagem != null) {
                return new DefaultStreamedContent(new ByteArrayInputStream(imagem), "image/png");
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public StreamedContent getImagem() {
        return imagem;
    }

    public void setImagem(StreamedContent imagem) {
        this.imagem = imagem;
    }
}
