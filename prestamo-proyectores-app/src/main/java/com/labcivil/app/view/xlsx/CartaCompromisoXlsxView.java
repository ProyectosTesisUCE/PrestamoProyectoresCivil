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

import com.labcivil.app.models.entity.Registro;

@Component("registro/listar.xlsx")
public class CartaCompromisoXlsxView extends AbstractXlsxView {
	

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"LISTADO_CARTAS_COMPROMISO.xlsx\"");		

		@SuppressWarnings("unchecked")
		List<Registro> registros =  (List<Registro>) model.get("registros");		
		
		Sheet sheet = workbook.createSheet("Listado");
		
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
		row.createCell(3).setCellValue("UNIVERSIDAD CENTRAL DEL ECUADOR"); 
		row.getCell(3).setCellStyle(headerStyle);
				
		
		row = sheet.createRow(1);
		row.createCell(0).setCellValue("");
		row.createCell(1).setCellValue("");
		row.createCell(2).setCellValue("");
		row.createCell(3).setCellValue("FACULTAD DE INGENIERÍA, CIENCIAS FÍSICAS Y MATEMÁTICA");
		row.getCell(3).setCellStyle(headerStyle1);
		sheet.autoSizeColumn(3);
		
		row = sheet.createRow(2);
		row.createCell(0).setCellValue("");
		row.createCell(1).setCellValue("");
		row.createCell(2).setCellValue("");
		row.createCell(3).setCellValue("LABORATORIO DE INGENIERÍA CIVIL");
		row.getCell(3).setCellStyle(headerStyle1);
		
		row = sheet.createRow(3);
		row.createCell(0).setCellValue("");
		row.createCell(1).setCellValue("");
		row.createCell(2).setCellValue("");
		row.createCell(3).setCellValue("LISTADO CARTAS DE COMPROMISO");
		row.getCell(3).setCellStyle(headerStyle);
		
		
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
				
		
		Row header = sheet.createRow(4);
		header.createCell(0).setCellValue("N°");
		header.createCell(1).setCellValue("FECHA CC.");
		header.createCell(2).setCellValue("ESTUDIANTE");		
		header.createCell(3).setCellValue("DOCENTE");
		header.createCell(4).setCellValue("MATERIA");
		header.createCell(5).setCellValue("HORARIO");
		header.createCell(6).setCellValue("CELULAR EST.");
		header.createCell(7).setCellValue("ESTADO");
	
		
		header.getCell(0).setCellStyle(theaderStyle);
		header.getCell(1).setCellStyle(theaderStyle);
		header.getCell(2).setCellStyle(theaderStyle);
		header.getCell(3).setCellStyle(theaderStyle);
		header.getCell(4).setCellStyle(theaderStyle);
		header.getCell(5).setCellStyle(theaderStyle);
		header.getCell(6).setCellStyle(theaderStyle);
		header.getCell(7).setCellStyle(theaderStyle);

		
		int rownum = 5;
		for( Registro registro : registros ) {
			
			Row fila = sheet.createRow(rownum ++);
			
			cell = fila.createCell(0);
			cell.setCellValue(registro.getId().toString());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(1);
			cell.setCellValue(registro.getCreateRe().toString());
			sheet.autoSizeColumn(1);
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(2);
			cell.setCellValue(registro.getEstudiante().getApellido()+" "+registro.getEstudiante().getNombre());
			sheet.autoSizeColumn(2);
			cell.setCellStyle(tbodyStyle);
			
			
			cell = fila.createCell(3);
			cell.setCellValue(registro.getProfesor().getApellido()+" "+registro.getProfesor().getNombre());
			cell.setCellStyle(tbodyStyle);
						
			cell = fila.createCell(4);
			cell.setCellValue(registro.getMateria().getNombre());
			sheet.autoSizeColumn(4);
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(5);
			cell.setCellValue(registro.getHorario());
			sheet.autoSizeColumn(5);
			cell.setCellStyle(tbodyStyle);			
			
			cell = fila.createCell(6);
			cell.setCellValue(registro.getEstudiante().getTelefono());
			sheet.autoSizeColumn(6);
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(7);
			cell.setCellValue(registro.getEstado());
			sheet.autoSizeColumn(7);
			cell.setCellStyle(tbodyStyle);								
			
		}	
	}
}
