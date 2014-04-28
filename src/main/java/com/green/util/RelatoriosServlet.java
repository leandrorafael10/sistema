/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author leandro.silva
 */
@WebServlet(name = "RelatoriosServlet", urlPatterns = {"/RelatoriosServlet"})
public class RelatoriosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RelatoriosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RelatoriosServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] vlParametro = request.getParameterValues("id");

        // obtém a conexão com o banco de dados  
        Connection conn = null;
        try {
            Context initContext = new InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) initContext.lookup("GreenJndi");
            conn = (java.sql.Connection) ds.getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            // mude para a senha deste usuário  
        } catch (Exception e) {
            System.out.println("Erro ao obter conexao via DriverManager: "
                    + e.getMessage());
        }

        // gera o relatório  
        ServletContext context = getServletContext();
        byte[] bytes = null;
        try {


            // carrega os arquivos jasper  
            JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(
                    context.getRealPath("/WEB-INF/relatorios/Classificacao.jasper"));

            // parâmetros, se houverem  
            Map parametros = new HashMap();
            parametros.put("codigo1", "" + "%");
            parametros.put("descricao1", "" + "%");

            // direciona a saída do relatório para um stream  
            bytes = JasperRunManager.runReportToPdf(relatorioJasper, parametros, conn);
        } catch (JRException e) {
            e.printStackTrace();
        }

            if (bytes != null && bytes.length > 0) {
                // envia o relatório em formato PDF para o browser  
                response.setContentType("application/pdf");

                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
            }
            processRequest(request, response);
        }
    

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
