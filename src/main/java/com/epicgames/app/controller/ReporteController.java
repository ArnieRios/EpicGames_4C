package com.epicgames.app.controller;

import java.io.OutputStream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ReporteController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/reportes")
    public void reportes(HttpServletResponse response) {

        response.setHeader("Content-Disposition", "inline; filename=grafico_categoria.pdf");
        response.setContentType("application/pdf");

        try {

            String ru = resourceLoader
                    .getResource("classpath:grafico_categoria.jasper")
                    .getURI()
                    .getPath();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    ru,
                    null,
                    dataSource.getConnection()
            );

            OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

        } catch (Exception e) {
            e.printStackTrace();}
        }
        
        @GetMapping("/reporte_juego")
        public void reporte_juego(HttpServletResponse response) {

            response.setHeader("Content-Disposition", "inline; filename=Grafico_juego-proveedor.pdf");
            response.setContentType("application/pdf");

            try {

                String ru = resourceLoader
                        .getResource("classpath:Grafico_juego-proveedor.jasper")
                        .getURI()
                        .getPath();

                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        ru,
                        null,
                        dataSource.getConnection()
                );

                OutputStream outStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    
}