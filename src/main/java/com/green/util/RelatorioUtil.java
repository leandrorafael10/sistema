/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author leandro.silva
 */
public class RelatorioUtil {

    public static final int RELATORIO_PDF = 1;
    public static final int RELATORIO_EXCEL = 2;
    public static final int RELATORIO_HTML = 3;
    public static final int RELATORIO_RELATORIO_PLANILHA_OPEN_OFFICE = 4;

    public RelatorioUtil() {
    }

    public static File geraRelatorio(HashMap parametrosRelatorio, String nomeRelatorioJasper, String nomeRelatorioSaida, int tipoRelatorio) throws JRException {
        // StreamedContent arquivoRetorno = null;
        File arquivoGerado = null;
        java.sql.Connection conexao = null;
        try {
            Context initContext = new InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) initContext.lookup("GreenJndi");
            conexao = (java.sql.Connection) ds.getConnection();
            FacesContext context = FacesContext.getCurrentInstance();
            String caminhoRelatorio = context.getExternalContext().getRealPath("relatorio");
            String caminhoArquivoJasper = caminhoRelatorio + File.separator + nomeRelatorioJasper + ".jasper";
            String caminhoArquivoRelatorio = null;
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametrosRelatorio, conexao);
            conexao.close();
            JRExporter tipoArquivoExportado = null;
            String extencaoArquivoExportado = "";

            switch (tipoRelatorio) {
                case RelatorioUtil.RELATORIO_PDF:
                    tipoArquivoExportado = new JRPdfExporter();
                    extencaoArquivoExportado = "pdf";
                    break;
                case RelatorioUtil.RELATORIO_HTML:
                    tipoArquivoExportado = new JRHtmlExporter();
                    extencaoArquivoExportado = "html";
                    break;
                case RelatorioUtil.RELATORIO_EXCEL:
                    tipoArquivoExportado = new JRXlsExporter();
                    extencaoArquivoExportado = "xls";
                    break;
                case RelatorioUtil.RELATORIO_RELATORIO_PLANILHA_OPEN_OFFICE:
                    tipoArquivoExportado = new JROdtExporter();
                    extencaoArquivoExportado = "ods";
                    break;
                default:
                    tipoArquivoExportado = new JRPdfExporter();
                    extencaoArquivoExportado = "pdf";
                    break;
            }
            caminhoArquivoRelatorio = caminhoRelatorio + File.separator + nomeRelatorioSaida + "." + extencaoArquivoExportado;
            arquivoGerado = new java.io.File(caminhoArquivoRelatorio);
            tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
            tipoArquivoExportado.exportReport();
            arquivoGerado.deleteOnExit();
            return arquivoGerado;
        } catch (SQLException ex) {
            System.out.println("Erro na conexao com jndi" + ex.getMessage());
            return arquivoGerado;
        } catch (NamingException ex) {
            System.out.println("Erro na conexao com SQL" + ex.getMessage());
            return arquivoGerado;
        }
        // InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/relatorio/plano_conta.pdf"); 
    }

    /**
     * **
     * transforma o qualquer arquivo em bytes para enviar para o brawser
     *
     */
    public static byte[] fileToByte(File imagem) throws Exception {
        FileInputStream fis = new FileInputStream(imagem);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int bytesRead = 0;
        while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }

    /**
     * **
     *
     * @param nomeJasper
     * @param nomeRelatorioSaida
     * @param parametros
     * @return StreamedContent para baixar arquivos pelo file Dowload do prime
     * faces
     */
    public static StreamedContent getFile(String nomeJasper, String nomeRelatorioSaida, HashMap parametros, int tipoRelatorio) {
        StreamedContent file;
        FacesContext fc = FacesContext.getCurrentInstance();
        Date dateNomeRelatorio = new Date();
        String nomeRelatorioJasper = nomeJasper;
        nomeRelatorioSaida = String.valueOf(dateNomeRelatorio.getTime()).concat(nomeRelatorioSaida);
        RelatorioUtil relatorioUtil = new RelatorioUtil();
        File arquivo = null;
        try {
            arquivo = relatorioUtil.geraRelatorio(parametros, nomeRelatorioJasper, nomeRelatorioSaida, tipoRelatorio);
        } catch (Exception e) {
            fc.addMessage(null, new FacesMessage(e.getMessage()));
        }
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/relatorio/" + nomeRelatorioSaida + ".pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", nomeRelatorioSaida + ".pdf");
        return file;
    }

    /**
     * **
     *
     * @param nomeRelatorioJasper
     * @param nomeDeSaida
     * @param parametros
     * @throws Exception recebe os parametroas no nome do relatorio jasper e o
     * nome do relatorio de saida e depois de concluido gera uma nova aba no
     * navegador com o pdf do relatorio
     */
    public static void geraPdf(String nomeRelatorioJasper, String nomeDeSaida, HashMap parametros, int tipoRelatorio) throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        Date dateNomeRelatorio = new Date();
        String nomeRelatorioSaida = dateNomeRelatorio.getTime() + nomeDeSaida;
        File arquivo = null;
        byte[] conteudoPdf;

        try {
            arquivo = geraRelatorio(parametros, nomeRelatorioJasper, nomeRelatorioSaida, tipoRelatorio);

        } catch (Exception e) {
            fc.addMessage(null, new FacesMessage(e.getMessage()));
        }
        conteudoPdf = fileToByte(arquivo);
        // Obtem o HttpServletResponse, objeto responsável pela resposta do
        // servidor ao browser
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        // Limpa o buffer do response
        // response.reset();
        // Seta o tipo de conteudo no cabecalho da resposta. No caso, indica que o
        // conteudo sera um documento pdf.
        // Seta o tamanho do conteudo no cabecalho da resposta. No caso, o tamanho
        // em bytes do pdf
        // response.setContentLength(conteudoPdf.length);

        response.setHeader("Content-disposition",
                "inline; filename=" + nomeRelatorioSaida + ".pdf");
        try {
            if (conteudoPdf != null && conteudoPdf.length > 0) {
                response.setContentType("application/pdf");
                // Envia o conteudo do arquivo PDF para o response
                ServletOutputStream servletOutputStream = response.getOutputStream();
                servletOutputStream.write(conteudoPdf, 0, conteudoPdf.length);
                // Descarrega o conteudo do stream, forçando a escrita de qualquer byte
                // ainda em buffer
                servletOutputStream.flush();
                // Fecha o stream, liberando seus recursos
                servletOutputStream.close();
                fc.renderResponse();
            }
            // Sinaliza ao JSF que a resposta HTTP para este pedido já foi gerada
            fc.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    private static void gerarRelatorioWeb(JRDataSource jrds, Map<String, Object> parametros, String arquivo) {
        ServletOutputStream servletOutputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        InputStream inputStream = null;
        
        try {
            servletOutputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), response.getOutputStream(), parametros, jrds);
            response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void geraRelatorioBean(String nomeRelatorioJasper,JRDataSource jrds ,Map<String, Object> parametros) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        String arquivo = context.getRealPath("relatorio/"+nomeRelatorioJasper+".jasper");
        gerarRelatorioWeb(jrds, parametros, arquivo);
    }
}
/*
 * private Connection getConexao() { java.sql.Connection conexao = null; try {
 * Context initContext = new InitialContext(); javax.sql.DataSource ds =
 * (javax.sql.DataSource) initContext.lookup("GreenJndi"); conexao =
 * (java.sql.Connection) ds.getConnection(); } catch (NamingException e) {
 * System.out.println("Erro na conexao com jndi" + e.getMessage()); } catch
 * (SQLException e) { System.out.println("Erro na conexao com SQL" +
 * e.getMessage()); } return conexao; }
 *
 *
 * public RelatorioUtil(String ReportName, Map<?, ?> params, int tipoRelatorio,
 * List<?> list) { this.reportName = ReportName; this.params = params;
 * this.tipoRelatorio = tipoRelatorio; this.list = list; }
 *
 * public RelatorioUtil(String ReportName,int tipoRelatorio) { this.reportName =
 * ReportName; this.tipoRelatorio = tipoRelatorio; }
 *
 */
