/**
 *
 */
package com.shalahuddin.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shalahuddin.web.form.DataBean;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

@Controller
@RequestMapping(value="example")
public class ExampleReportController {
	private static final Logger logger = LoggerFactory.getLogger(ExampleReportController.class);

	@Autowired
	DataSource dataSource;

	@GetMapping("reportPdf")
	public String report(HttpServletResponse response){
		JasperPrint jasperPrint = fillReportUsingCollection("example");
		try{
			// printing
			//			JasperPrintManager.printReport(jrPrint, true);
			// export to pdf
			//			net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfFile(jasperPrint, "/tmp/example.pdf");
			// export to html
			//			net.sf.jasperreports.engine.JasperExportManager.exportReportToHtmlFile(jasperPrint, "/tmp/example.html");
			// export to xls
			//			JRXlsExporter exporter = new JRXlsExporter();
			//            exporter.exportReport();

			// open pdf in browser
			generateReportPDF(response, jasperPrint, "example.pdf");
		}catch(JRException jrex){
			logger.error("failed print report", jrex);
		}
		return null;
	}

	@GetMapping("reportXls")
	public String reportXls(HttpServletResponse response){
		JasperPrint jasperPrint = fillReportUsingCollection("example");
		try{
			// printing
			//			JasperPrintManager.printReport(jrPrint, true);
			// export to pdf
			//			net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfFile(jasperPrint, "/tmp/example.pdf");
			// export to html
			//			net.sf.jasperreports.engine.JasperExportManager.exportReportToHtmlFile(jasperPrint, "/tmp/example.html");
			// export to xls
			//			JRXlsExporter exporter = new JRXlsExporter();
			//            exporter.exportReport();

			// open pdf in browser
			generateReportXLS(response, jasperPrint, "example.pdf");
		}catch(JRException jrex){
			logger.error("failed print report", jrex);
		}
		return null;
	}

	private void generateReportPDF(HttpServletResponse response,
			JasperPrint jasperPrint, String fileName) throws JRException {
		byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

		response.reset();
		// uncomment this line to make browser download the file
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment;filename="+fileName);
		try {
			response.getOutputStream().write(pdfBytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			response.flushBuffer();
		} catch (IOException ioe) {
			logger.error("failed export to output stream", ioe);
		}
	}

	private void generateReportXLS(HttpServletResponse response,
			JasperPrint jasperPrint, String fileName) throws JRException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String dateNow = dateFormat.format(new Date());
		String nameFileFix = fileName+"_"+dateNow+".xls";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		JRXlsExporter exporterXLS = new JRXlsExporter();
		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		// exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporterXLS.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
		exporterXLS.exportReport();
		byte[] xlsByte = outputStream.toByteArray();
		response.setHeader("Content-Disposition","attachment;filename="+nameFileFix+" ");
		response.setContentType("application/vnd.ms-excel");
		response.setContentLength(xlsByte.length);
		if (xlsByte.length>0){
			try {
				ServletOutputStream outputStr = response.getOutputStream();
				outputStr.write(xlsByte,0,xlsByte.length);
				outputStr.flush();
				outputStr.close();
			} catch (IOException ioe) {
				logger.error("failed export to output stream", ioe);
			}
		}

	}

	private JasperPrint fillReportUsingConnection(String reportName, Map<String, Object> reportParams) throws Exception {
		JasperPrint jasperPrint = null;
		InputStream inputStream = null;
		Connection conn = null;
		try {
			logger.info("report from local database");
			conn = dataSource.getConnection();
			logger.info("conn" + conn);
			logger.info("reportParams >> " + reportParams);
			inputStream = this.getClass().getClassLoader().getResourceAsStream(reportName + ".jasper");

			jasperPrint = JasperFillManager.fillReport(inputStream, reportParams, conn);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("cannot close input stream", e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("cannot close connection", e);
				}
			}
		}
		return jasperPrint;
	}

	private JasperPrint fillReportUsingCollection(String reportName) {
		InputStream inputStream = null;
		JasperPrint jasperPrint = null;
		ArrayList<DataBean> dataList = getDataBeanList();

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
		Map parameters = new HashMap();
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream(reportName +".jasper");
			jasperPrint = JasperFillManager.fillReport(inputStream, parameters, beanColDataSource);

			logger.debug("jasper print={}", jasperPrint);
		} catch (JRException e) {
			logger.error("failed fill report", e);
		} catch (Exception e){
			logger.error("failed fill report", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("cannot close input stream", e);
				}
			}
		}
		return jasperPrint;
	}

	private ArrayList<DataBean> getDataBeanList() {
		ArrayList<DataBean> dataBeanList = new ArrayList<>();

		dataBeanList.add(new DataBean("Manisha", "India"));
		dataBeanList.add(new DataBean("Dennis Ritchie", "USA"));
		dataBeanList.add(new DataBean("V.Anand", "India"));
		dataBeanList.add(new DataBean("Shrinath", "California"));

		return dataBeanList;
	}
}
