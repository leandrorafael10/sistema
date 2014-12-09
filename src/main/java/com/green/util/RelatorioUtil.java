/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.view.JasperViewer;

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

    @Deprecated
    public static File geraRelatorio(HashMap<String, Object> parametrosRelatorio, String nomeRelatorioJasper, String nomeRelatorioSaida, int tipoRelatorio) throws JRException {
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

    private static void gerarRelatorioWeb(JRDataSource jrds, Map<String, Object> parametros, String arquivo) {
        ServletOutputStream servletOutputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

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

    public static void geraRelatorioBean(String nomeRelatorioJasper, JRDataSource jrds, Map<String, Object> parametros) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        String arquivo = context.getRealPath("relatorio/" + nomeRelatorioJasper + ".jasper");
        gerarRelatorioWeb(jrds, parametros, arquivo);
    }

    public static void geraRelatorioBeanLocal(String nomeRelatorioJasper, JRDataSource jrds, Map<String, Object> parametros) throws JRException, IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext contextFaces = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) contextFaces.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        ServletContext context = (ServletContext) externalContext.getContext();
        String arquivo = context.getRealPath("relatorio/" + nomeRelatorioJasper + ".jrxml");
        //response.setContentType("application / pdf");
       // response.setHeader("Content-Disposition", "attachment; filename=\"relatorio.pdf\"");
        JasperReport pathReport = JasperCompileManager.compileReport(arquivo);
//relatorio gerado
        JasperPrint preencher = JasperFillManager.fillReport(pathReport, null, jrds);
        JasperExportManager.exportReportToPdfStream(preencher, responseStream);
        responseStream.flush();
        responseStream.close();
        contextFaces.renderResponse();
        contextFaces.responseComplete();
        JasperViewer.viewReport(preencher, false);
    }

   

}
