package com.notinha.util;

import com.notinha.model.Notinha;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Relatorios {

	public void imprimeRelatorio(List<Notinha> listaOS, String relatorio, HttpServletResponse facesContext) throws IOException {
//        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaOS);
//        HashMap parameters = new HashMap();
//        try {
//           // FacesContext facesContext = FacesContext.getCurrentInstance();
//           // facesContext.responseComplete();
//           // ServletContext scontext = (ServletContext)facesContext.getExternalContext().getContext();
//            JasperPrint jasperPrint = JasperFillManager.fillReport("c:\\relatorios\\" + relatorio, parameters, (JRDataSource)ds);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            JRPdfExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)jasperPrint);
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, (Object)baos);
//            exporter.exportReport();
//            byte[] bytes = baos.toByteArray();
//            if (bytes != null && bytes.length > 0) {
//                HttpServletResponse response = facesContext;
//                response.setContentType("application/pdf");
//                response.setHeader("Content-disposition", "attachment; filename=\"notinha.pdf\"");
//                response.setContentLength(bytes.length);
//                ServletOutputStream outputStream = response.getOutputStream();
//                outputStream.write(bytes, 0, bytes.length);
//                outputStream.flush();
//                outputStream.close();
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
