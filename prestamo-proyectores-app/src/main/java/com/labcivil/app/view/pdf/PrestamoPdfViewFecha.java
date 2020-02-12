package com.labcivil.app.view.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.labcivil.app.models.entity.Prestamo;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

@Component("listadoFecha")
public class PrestamoPdfViewFecha extends AbstractPdfView1 {
	
	Font ffont = new Font(Font.UNDEFINED, 10f, Font.BOLD);
	Font ffonta = new Font(Font.UNDEFINED, 8f, Font.BOLD);
	Font ffonta1 = new Font(Font.UNDEFINED, 7f, Font.BOLD);
	Font ffont1 = new Font(Font.UNDEFINED, 10f);
	Font ffont2 = new Font(Font.UNDEFINED, 8f);
	
	class MyHeader extends PdfPageEventHelper{
		
		public void onEndPage(PdfWriter writer, Document document) {
			
            PdfContentByte cb = writer.getDirectContent();
            
            Phrase header = new Phrase("ACTA #5 ENTREGA RECEPCIÓN DE PRESTAMO DE INFOCUS EN EXCESO", ffont);
            Phrase header1 = new Phrase("UNIVERSIDAD CENTRAL DEL ECUADOR", ffont);
            Phrase header2 = new Phrase("FACULTAD DE INGENIERÍA, CIENCIAS FÍSICAS Y MATEMÁTICA", ffont2);
            Phrase header3 = new Phrase("LABORATORIO DE INGENIERÍA CIVIL", ffont2);
            Phrase header4 = new Phrase("REGISTRO PRESTAMO PROYECTORES", ffont);
//            Phrase footer = new Phrase("this is a footer", ffont);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    header,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.top() + 50, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    header1,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.top() + 40, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    header2,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.top() + 30, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    header3,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.top() + 20, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    header4,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.top() + 10, 0);
            
//            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
//                    footer,
//                    (document.right() - document.left()) / 2 + document.leftMargin(),
//                    document.bottom() - 10, 0);
        }
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		MyHeader event = new MyHeader();
		writer.setPageEvent(event);
		
		response.setHeader("Content-Disposition", "attachment; filename=\"REGISTRO_PRESTAMO_PROYECTORES.pdf\"");
		 
		document.addTitle("REGISTRO PRESTAMO PROYECTORES");
		  		
		PdfPCell cell = null;
		
		@SuppressWarnings("unchecked")
		List<Prestamo> prestamos =  (List<Prestamo>) model.get("prestamos");
		
		PdfPTable tabla2 = new PdfPTable(12);
		tabla2.setWidths(new float [] {0.4f,1.5f, 0.8f, 1.2f, 3f, 1.2f, 0.6f, 0.6f, 0.6f, 1f, 0.9f,1.8f});
		tabla2.setWidthPercentage(100);
		tabla2.setHeaderRows(1);

		cell = new PdfPCell(new Phrase("N°", ffonta));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase("FIRMA DIRECTOR/A\nCARRERA DE CIVIL", ffonta1));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase("ALTA P.", ffonta));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase("FECHA", ffonta));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase("PROFESOR / AYUDANTE", ffonta));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase("MATERIA", ffonta));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase("SEM.", ffonta));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase("PAR.", ffonta));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase("AULA", ffonta));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase("HORA I.", ffonta));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase("HORA F.", ffonta));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);
		cell = new PdfPCell(new Phrase("OBSERVACIONES", ffonta));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla2.addCell(cell);

		
		for( Prestamo prestamo : prestamos ) {
			cell = new PdfPCell(new Phrase(prestamo.getId().toString(),ffont1));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla2.addCell(cell);
			tabla2.addCell("");
			cell = new PdfPCell(new Phrase(prestamo.getProyector().getAlta(),ffont1));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla2.addCell(cell);
			cell = new PdfPCell(new Phrase(prestamo.getCreatePr().toString(),ffont1));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla2.addCell(cell);
			cell = new PdfPCell(new Phrase(prestamo.getRegistro().getProfesor().getApellido()+" "+prestamo.getRegistro().getProfesor().getNombre()+" / \n "+prestamo.getRegistro().getEstudiante().getApellido()+" "+prestamo.getRegistro().getEstudiante().getNombre(),ffont1));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla2.addCell(cell);
			cell = new PdfPCell(new Phrase(prestamo.getRegistro().getMateria().getNombre(),ffont1));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla2.addCell(cell);
			cell = new PdfPCell(new Phrase(Integer.toString(prestamo.getRegistro().getMateria().getSemestre()),ffont1));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla2.addCell(cell);
			cell = new PdfPCell(new Phrase(Integer.toString(prestamo.getRegistro().getMateria().getParalelo()),ffont1));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla2.addCell(cell);
			cell = new PdfPCell(new Phrase(prestamo.getAula(),ffont1));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla2.addCell(cell);
			cell = new PdfPCell(new Phrase(prestamo.getHoraIn().toString(),ffont1));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla2.addCell(cell);
			
			if(prestamo.getHoraFn() == null) {
				tabla2.addCell(" ");
			}else {
				cell = new PdfPCell(new Phrase(prestamo.getHoraFn().toString(),ffont1));
				cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				tabla2.addCell(cell);
			}
			

			cell = new PdfPCell(new Phrase(prestamo.getObservacion(),ffont1));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			tabla2.addCell(cell);
		}

		document.add(tabla2);
		
	}

}
