package com.spring.cevento.model;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.jfree.chart.title.Title;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PartPDFExport {

    private Iterable<Participante> listpart;

    public PartPDFExport(Iterable<Participante> listpart) {
        this.listpart = listpart;
    }
    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Nome", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cpf", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
    }
    private void writeTableData(PdfPTable table){

        for(Participante participante: listpart){
            table.addCell(participante.getNomeParticipante());
            table.addCell(String.valueOf( participante.getCpf()));
            table.addCell(participante.getEmail());
        }

    }
    public  void export(HttpServletResponse response) throws DocumentException,IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();


        document.add(new Paragraph("Lista de Participantes"));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
