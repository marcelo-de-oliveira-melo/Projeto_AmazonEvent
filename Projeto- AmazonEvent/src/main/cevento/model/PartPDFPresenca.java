package com.spring.cevento.model;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPage;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static java.awt.Color.BLACK;

public class PartPDFPresenca {

    private Iterable<Participante> listparte;
    private Iterable<Evento> eventos;

    public PartPDFPresenca(Iterable<Participante> listparte) {
        this.listparte = listparte;
    }


    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BLACK);

        cell.setPhrase(new Phrase("Nome", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Assinatura", font));
        table.addCell(cell);
    }
    private void writeTableData(PdfPTable table){

        for(Participante participante: listparte){

            table.addCell(participante.getNomeParticipante());
            table.addCell("");

        }

    }
    public  void presencas(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA, 14,BLACK);

        
        Paragraph paragraph = new Paragraph( "Lista de presença", font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);


        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        writeTableHeader(table);
        writeTableData(table);

        document.add(table);


        document.close();

    }


}
