package com.labcivil.app.view.xlsx;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.labcivil.app.models.entity.Prestamo;

@Component("listar.xlsx")
public class PrestamoXlsxView extends AbstractXlsxView {
	

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"REGISTRO_PRESTAMO_PROYECTORES.xlsx\"");		

		@SuppressWarnings("unchecked")
		List<Prestamo> prestamos =  (List<Prestamo>) model.get("prestamos");		
		
		Sheet sheet = workbook.createSheet("Prestamos");
		
		Cell cell = null;
		Font font = workbook.createFont();
		
		CellStyle headerStyle = workbook.createCellStyle();
		font.setBold(true);
		headerStyle.setFont(font);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		
		CellStyle headerStyle1 = workbook.createCellStyle();
		headerStyle1.setAlignment(HorizontalAlignment.CENTER);
		
		Row  row = sheet.createRow(0);
		row.createCell(0).setCellValue("");
		row.createCell(1).setCellValue("");
		row.createCell(2).setCellValue("");
		row.createCell(3).setCellValue("");
		row.createCell(4).setCellValue("ACTA #5 ENTREGA RECEPCIÓN DE PRESTAMO DE INFOCUS EN EXCESO"); 
		row.getCell(4).setCellStyle(headerStyle);
		sheet.autoSizeColumn(4);		
		
		row = sheet.createRow(1);
		row.createCell(0).setCellValue("");
		row.createCell(1).setCellValue("");
		row.createCell(2).setCellValue("");
		row.createCell(3).setCellValue("");
		row.createCell(4).setCellValue("UNIVERSIDAD CENTRAL DEL ECUADOR");
		row.getCell(4).setCellStyle(headerStyle);
		
		row = sheet.createRow(2);
		row.createCell(0).setCellValue("");
		row.createCell(1).setCellValue("");
		row.createCell(2).setCellValue("");
		row.createCell(3).setCellValue("");
		row.createCell(4).setCellValue("FACULTAD DE INGENIERÍA, CIENCIAS FÍSICAS Y MATEMÁTICA");
		row.getCell(4).setCellStyle(headerStyle1);
		
		row = sheet.createRow(3);
		row.createCell(0).setCellValue("");
		row.createCell(1).setCellValue("");
		row.createCell(2).setCellValue("");
		row.createCell(3).setCellValue("");
		row.createCell(4).setCellValue("LABORATORIO DE INGENIERÍA CIVIL");
		row.getCell(4).setCellStyle(headerStyle1);
		
		row = sheet.createRow(4);
		row.createCell(0).setCellValue("");
		row.createCell(1).setCellValue("");
		row.createCell(2).setCellValue("");
		row.createCell(3).setCellValue("");
		row.createCell(4).setCellValue("REGISTRO PRESTAMO PROYECTORES");
		row.getCell(4).setCellStyle(headerStyle);

		
		CellStyle theaderStyle = workbook.createCellStyle();
		theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderStyle.setBorderTop(BorderStyle.MEDIUM);
		theaderStyle.setBorderRight(BorderStyle.MEDIUM);
		theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderStyle.setAlignment(HorizontalAlignment.CENTER);
		
		font = workbook.createFont();
		font.setBold(true);
		theaderStyle.setFont(font);
		theaderStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	
		CellStyle tbodyStyle = workbook.createCellStyle();
		tbodyStyle.setBorderBottom(BorderStyle.THIN);
		tbodyStyle.setBorderTop(BorderStyle.THIN);
		tbodyStyle.setBorderRight(BorderStyle.THIN);
		tbodyStyle.setBorderLeft(BorderStyle.THIN);
		tbodyStyle.setAlignment(HorizontalAlignment.CENTER);
		
		CellStyle tbodyStyle1 = workbook.createCellStyle();
		tbodyStyle1.setBorderBottom(BorderStyle.THIN);
		tbodyStyle1.setBorderTop(BorderStyle.THIN);
		tbodyStyle1.setBorderRight(BorderStyle.THIN);
		tbodyStyle1.setBorderLeft(BorderStyle.THIN);
		
		
		Row header = sheet.createRow(5);
		header.createCell(0).setCellValue("N°");
		header.createCell(1).setCellValue("FIRMA DIRECTOR/A");
		sheet.autoSizeColumn(1);
		header.createCell(2).setCellValue("ALTA P.");
		header.createCell(3).setCellValue("FECHA");
		header.createCell(4).setCellValue("PROFESOR / AYUDANTE");
		header.createCell(5).setCellValue("MATERIA");
		header.createCell(6).setCellValue("SEM.");
		header.createCell(7).setCellValue("PAR.");
		header.createCell(8).setCellValue("AULA");
		header.createCell(9).setCellValue("HORA I.");
		header.createCell(10).setCellValue("HORA F.");
		header.createCell(11).setCellValue("OBSERVACIONES");		
		
		header.getCell(0).setCellStyle(theaderStyle);
		header.getCell(1).setCellStyle(theaderStyle);
		header.getCell(2).setCellStyle(theaderStyle);
		header.getCell(3).setCellStyle(theaderStyle);
		header.getCell(4).setCellStyle(theaderStyle);
		header.getCell(5).setCellStyle(theaderStyle);
		header.getCell(6).setCellStyle(theaderStyle);
		header.getCell(7).setCellStyle(theaderStyle);
		header.getCell(8).setCellStyle(theaderStyle);
		header.getCell(9).setCellStyle(theaderStyle);
		header.getCell(10).setCellStyle(theaderStyle);
		header.getCell(11).setCellStyle(theaderStyle);
		
		int rownum = 6;
		for( Prestamo prestamo : prestamos ) {
			
			Row fila = sheet.createRow(rownum ++);
			
			cell = fila.createCell(0);
			cell.setCellValue(prestamo.getId().toString());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(1);
			cell.setCellValue("");
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(2);
			cell.setCellValue(prestamo.getProyector().getAlta());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(3);
			cell.setCellValue(prestamo.getCreatePr().toString());
			cell.setCellStyle(tbodyStyle);
			sheet.autoSizeColumn(3);
			
			cell = fila.createCell(4);
			cell.setCellValue(prestamo.getRegistro().getProfesor().getApellido()+" "+prestamo.getRegistro().getProfesor().getNombre()+" / \n "+prestamo.getRegistro().getEstudiante().getApellido()+" "+prestamo.getRegistro().getEstudiante().getNombre());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(5);
			cell.setCellValue(prestamo.getRegistro().getMateria().getNombre());
			cell.setCellStyle(tbodyStyle);
			sheet.autoSizeColumn(5);
			
			cell = fila.createCell(6);
			cell.setCellValue(Integer.toString(prestamo.getRegistro().getMateria().getSemestre()));
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(7);
			cell.setCellValue(Integer.toString(prestamo.getRegistro().getMateria().getParalelo()));
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(8);
			cell.setCellValue(prestamo.getAula());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(9);
			cell.setCellValue(prestamo.getHoraIn().toString());
			cell.setCellStyle(tbodyStyle);
			
			if(prestamo.getHoraFn() == null) {
				cell = fila.createCell(10);
				cell.setCellValue("");
				cell.setCellStyle(tbodyStyle);
			}else {
				cell = fila.createCell(10);
				cell.setCellValue(prestamo.getHoraFn().toString());
				cell.setCellStyle(tbodyStyle);
			}			
			
			cell = fila.createCell(11);
			cell.setCellValue(prestamo.getObservacion());
			cell.setCellStyle(tbodyStyle1);
			sheet.autoSizeColumn(11);
			
		}	
	}
}
