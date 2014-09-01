/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.Sacado;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.bancos.Bradesco;
import br.com.caelum.stella.boleto.bancos.Caixa;
import br.com.caelum.stella.boleto.bancos.Itau;
import br.com.caelum.stella.boleto.bancos.Santander;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoletoHTML;
import static br.com.caelum.stella.boleto.utils.StellaStringUtils.leftPadWithZeros;
import com.green.modelo.Receita;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author leandro.silva
 */
public class BoletoCaelum {

    public static void boletoModeloNovo(Receita r) {
        Banco banco = null;
        Calendar vencimento = Calendar.getInstance();
        vencimento.setTime(r.getDTVencimento());
        Calendar emissao = Calendar.getInstance();
        emissao.setTime(r.getDTEmissao());
        Datas datas = Datas.novasDatas()
                .comDocumento(emissao)
                .comProcessamento(Calendar.getInstance())
                .comVencimento(vencimento);
        //nosso banco

        Emissor emissor = Emissor.novoEmissor()
                .comCedente(r.getIDConta().getTitular())
                .comAgencia(r.getIDConta().getAgencia())
                .comDigitoAgencia(r.getIDConta().getAgenciaDigito())
                .comContaCorrente(r.getIDConta().getNumero())
                .comDigitoContaCorrente(r.getIDConta().getNumeroDigito())
                .comNossoNumero(leftPadWithZeros(r.getIDReceita().toString(), 11))//gerado pela empresa nao se repetir para o mesmo cliente
                .comDigitoNossoNumero("1")
                .comCarteira("9");

        switch (r.getIDConta().getIDBanco().getCodigo()) {
            //Banco do Brasil
            case "001":
                banco = new BancoDoBrasil();
                break;
            //Caixa
            case "104":
                banco = new Caixa();
                break;
            //Itau
            case "341":
                banco = new Itau();
                break;
            //Santander
            case "033":
                banco = new Santander();
                break;
            //Bradesco
            case "237":
                banco = new BradescoCorrigido();
                break;
        }
        Sacado sacado = Sacado.novoSacado().comNome(r.getIDCliente().getIDPessoa().getRazao()).
                comBairro(r.getIDCliente().getIDPessoa().getBairro())
                .comCep(r.getIDCliente().getIDPessoa().getCep())
                .comCidade(r.getIDCliente().getIDPessoa().getCidade())
                .comCpf(r.getIDCliente().getIDPessoa().getCnpjCpf())
                .comUf(r.getIDCliente().getIDPessoa().getEstado())
                .comEndereco(r.getIDCliente().getIDPessoa().getLogradouro() + " Nº:" + r.getIDCliente().getIDPessoa().getNumero() + " , " + r.getIDCliente().getIDPessoa().getComplemento());
        Boleto boleto = Boleto.novoBoleto()
                .comBanco(banco)
                .comDatas(datas)
                .comEmissor(emissor)
                .comEspecieDocumento("DM")
                .comValorBoleto(r.getValorLiquido().toString())
                .comSacado(sacado)
                .comNumeroDoDocumento(r.getIdorigem().getIDContratoMidia().getNContrato() + " -- " + r.getNumero() + "/" + r.getIdorigem().getIDContratoMidia().getNumeroParcelas())
                .comInstrucoes("** VALORES EXPRESSOS EM REAIS", "MORA DIA / COM.PERMANENC..........0,90", "APÓS " + r.getDTVencimento().getDate() + "/" + (r.getDTVencimento().getMonth() + 1) + "/" + (r.getDTVencimento().getYear() + 1900) + "MULTA ........54,00")
                .comLocaisDePagamento("Todos os bancos até o vencimento ,após vencimento somente " + r.getIDConta().getIDBanco().getDescricao());
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        try {
            JasperReport subrelatorioJasper = (JasperReport)JRLoader.loadObject(
                    context.getRealPath("relatorio/boleto-default_instrucoes.jasper"));
        parametros.put("SUB_INSTRUCOES", subrelatorioJasper);
        JRDataSource jrds = new JRBeanCollectionDataSource(Arrays.asList(boleto));
        RelatorioUtil.geraRelatorioBean("boleto-default", jrds, parametros);
        } catch (JRException ex) {
            Logger.getLogger(BoletoCaelum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void boletoModelo(Receita r) {
        Banco banco = null;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        ServletOutputStream servletOutputStream = null;
        Calendar vencimento = Calendar.getInstance();
        vencimento.setTime(r.getDTVencimento());
        Calendar emissao = Calendar.getInstance();
        emissao.setTime(r.getDTEmissao());
        Datas datas = Datas.novasDatas()
                .comDocumento(emissao)
                .comProcessamento(Calendar.getInstance())
                .comVencimento(vencimento);
        //nosso banco

        Emissor emissor = Emissor.novoEmissor()
                .comCedente(r.getIDConta().getTitular())
                .comAgencia(r.getIDConta().getAgencia())
                .comDigitoAgencia(r.getIDConta().getAgenciaDigito())
                .comContaCorrente(r.getIDConta().getNumero())
                .comDigitoContaCorrente(r.getIDConta().getNumeroDigito())
                .comNossoNumero(leftPadWithZeros(r.getIDReceita().toString(), 11))//gerado pela empresa nao se repetir para o mesmo cliente
                .comDigitoNossoNumero("1")
                .comCarteira("9");

        switch (r.getIDConta().getIDBanco().getCodigo()) {
            //Banco do Brasil
            case "001":
                banco = new BancoDoBrasil();
                break;
            //Caixa
            case "104":
                banco = new Caixa();
                break;
            //Itau
            case "341":
                banco = new Itau();
                break;
            //Santander
            case "033":
                banco = new Santander();
                break;
            //Bradesco
            case "237":
                banco = new BradescoCorrigido();
                break;
        }
        Sacado sacado = Sacado.novoSacado().comNome(r.getIDCliente().getIDPessoa().getRazao()).
                comBairro(r.getIDCliente().getIDPessoa().getBairro())
                .comCep(r.getIDCliente().getIDPessoa().getCep())
                .comCidade(r.getIDCliente().getIDPessoa().getCidade())
                .comCpf(r.getIDCliente().getIDPessoa().getCnpjCpf())
                .comUf(r.getIDCliente().getIDPessoa().getEstado())
                .comEndereco(r.getIDCliente().getIDPessoa().getLogradouro() + " Nº:" + r.getIDCliente().getIDPessoa().getNumero() + " , " + r.getIDCliente().getIDPessoa().getComplemento());
        Boleto boleto = Boleto.novoBoleto()
                .comBanco(banco)
                .comDatas(datas)
                .comEmissor(emissor)
                .comEspecieDocumento("DM")
                .comValorBoleto(r.getValorLiquido().toString())
                .comSacado(sacado)
                .comNumeroDoDocumento(r.getIdorigem().getIDContratoMidia().getNContrato() + " -- " + r.getNumero() + "/" + r.getIdorigem().getIDContratoMidia().getNumeroParcelas())
                .comInstrucoes("** VALORES EXPRESSOS EM REAIS", "MORA DIA / COM.PERMANENC..........0,90", "APÓS " + r.getDTVencimento().getDate() + "/" + (r.getDTVencimento().getMonth() + 1) + "/" + (r.getDTVencimento().getYear() + 1900) + "MULTA ........54,00")
                .comLocaisDePagamento("Todos os bancos até o vencimento ,após vencimento somente " + r.getIDConta().getIDBanco().getDescricao());
        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
        try {
            servletOutputStream = response.getOutputStream();
            gerador.geraPDF(servletOutputStream);
            response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();
        } catch (IOException ex) {
            Logger.getLogger(BoletoCaelum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
