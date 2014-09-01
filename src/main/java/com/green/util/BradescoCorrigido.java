/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.bancos.AbstractBanco;
import br.com.caelum.stella.boleto.exception.CriacaoBoletoException;
import static br.com.caelum.stella.boleto.utils.StellaStringUtils.leftPadWithZeros;

/**
 *
 * @author leandro.silva
 */
public class BradescoCorrigido extends AbstractBanco implements Banco {

    private static final long serialVersionUID = 1L;

    private static final String NUMERO_BRADESCO = "237";

    private static final String DIGITO_NUMERO_BRADESCO = "2";

    @Override
    public String geraCodigoDeBarrasPara(Boleto boleto) {
        Emissor emissor = boleto.getEmissor();
        StringBuilder campoLivre = new StringBuilder();
        campoLivre.append(emissor.getAgenciaFormatado());
        campoLivre.append(getCarteiraDoEmissorFormatado(emissor));
        campoLivre.append(getNossoNumeroDoEmissorFormatado(emissor));
        campoLivre.append(getContaCorrenteDoEmissorFormatado(emissor));
        campoLivre.append("0");
        return new CodigoDeBarrasBuilder(boleto).comCampoLivre(campoLivre);

    }

    @Override
    public String getNumeroFormatadoComDigito() {
        StringBuilder builder = new StringBuilder();
        builder.append(getNumeroFormatado()).append("-");
        return builder.append(DIGITO_NUMERO_BRADESCO).toString();
    }

    @Override
    public String getNumeroFormatado() {
        return NUMERO_BRADESCO;
    }

    @Override
    public java.net.URL getImage() {
        String arquivo = "/br/com/caelum/stella/boleto/img/%s.png";
        String imagem = String.format(arquivo, getNumeroFormatado());
        return getClass().getResource(imagem);
    }

    public String getNumeroConvenioDoEmissorFormatado(Emissor emissor) {
        return leftPadWithZeros(emissor.getNumeroConvenio(), 7);
    }

    @Override
    public String getContaCorrenteDoEmissorFormatado(Emissor emissor) {
        return leftPadWithZeros(emissor.getContaCorrente(), 7);
    }

    @Override
    public String getCarteiraDoEmissorFormatado(Emissor emissor) {
        return leftPadWithZeros(emissor.getCarteira(), 2);
    }

    @Override
    public String getNossoNumeroDoEmissorFormatado(Emissor emissor) {
        return leftPadWithZeros(emissor.getNossoNumero(), 11);
    }

    public String getDigitoNossoNumeroDoEmissorFormatado(Emissor emissor) {
        return emissor.getDigitoNossoNumero();
    }

    @Override
    public String getNossoNumeroECodDocumento(Boleto boleto) {
        Emissor emissor = boleto.getEmissor();
        StringBuilder builder = new StringBuilder().append(leftPadWithZeros(emissor.getCarteira(), 2));
        builder.append("/").append(getNossoNumeroDoEmissorFormatado(emissor));
        return builder.append(getDigitoNossoNumero(emissor)).toString();
    }

    private String getDigitoNossoNumero(Emissor emissor) {
        return emissor.getDigitoNossoNumero() != null
                && !emissor.getDigitoNossoNumero().isEmpty()
                ? "-" + emissor.getDigitoNossoNumero() : "";
    }

}

class CodigoDeBarrasBuilder {

    private StringBuilder codigoDeBarras;
    private Banco banco;

    /**
     * Cria um CodigoDeBarrasBuilder com os primeiros 18 digitos preenchidos de
     * acordo com as normas da carta circular 2926 do banco central do Brasil. O
     * documento estÃ¡ disponÃ­vel para consulta em
     * br.com.caelum.stella.boleto.doc.
     *
     * @param boleto para o qual serÃ¡ gerado o cÃ³digo de barras.
     */
    CodigoDeBarrasBuilder(Boleto boleto) {
        this.banco = boleto.getBanco();
        this.codigoDeBarras = new StringBuilder(44);
        this.codigoDeBarras.append(banco.getNumeroFormatado());
        int codigoEspecieMoeda = boleto.getCodigoEspecieMoeda();
        this.codigoDeBarras.append(String.valueOf(codigoEspecieMoeda));
        this.codigoDeBarras.append(boleto.getFatorVencimento());
        this.codigoDeBarras.append(boleto.getValorFormatado());
    }

    /**
     * @param campoLivre preparado pelo banco de acordo com suas regras
     * @return o cÃ³digo de barras jÃ¡ com o digito verificador geral.
     * @throws CriacaoBoletoException se cÃ³digo tenha mais de 44 digitos.
     */
    public String comCampoLivre(StringBuilder campoLivre) {
        this.codigoDeBarras.append(campoLivre);
        String trecho = this.codigoDeBarras.toString();
        int digito = banco.getGeradorDeDigito().geraDigitoMod11(trecho);
        this.codigoDeBarras.insert(4, digito);
        validaTamahoDoCodigoDeBarrasCompletoGerado();
        return this.codigoDeBarras.toString();
    }

    /**
     * JÃ¡ valida neste ponto para nÃ£o ter que repetir esse cÃ³digo sempre
     */
    private void validaTamahoDoCodigoDeBarrasCompletoGerado() {
        if (this.codigoDeBarras.toString().length() != 44) {
            throw new CriacaoBoletoException("Erro na geraÃ§Ã£o do cÃ³digo "
                    + "de barras. NÃºmero de digitos diferente de 44. Verifique "
                    + "se todos os dados foram preenchidos corretamente.");
        }
    }
}
