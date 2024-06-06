package com.example.tallerMecanico.App.Service;

import com.example.tallerMecanico.App.Entity.Facturacion;

import com.example.tallerMecanico.App.Entity.Repuesto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {
    public ByteArrayInputStream generateInvoicePdf(Facturacion factura){
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Add Invoice Title
            document.add(new Paragraph("Factura", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20, com.itextpdf.text.Font.BOLD)));

            // Add Invoice Details
            document.add(new Paragraph("ID Reparación: " + factura.getIdReparacion()));
            document.add(new Paragraph("Total: $" + factura.getTotal()));
            document.add(new Paragraph("Pagada: " + (factura.isPagada() ? "Sí" : "No")));

            // Add Repuestos Table
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            PdfPCell cell;

            cell = new PdfPCell(new Phrase("ID Repuesto"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Nombre"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Cantidad Utilizada"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Precio Unitario"));
            table.addCell(cell);

            for (Repuesto repuesto : factura.getRepuestos()) {
                table.addCell(repuesto.getId());
                table.addCell(repuesto.getNombre());
                table.addCell(String.valueOf(1)); // Asumiendo que la cantidad es siempre 1
                table.addCell("$" + repuesto.getPrecio());
            }

            document.add(table);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
