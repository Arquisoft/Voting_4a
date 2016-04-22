package es.uniovi.asw.parser.writers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import es.uniovi.asw.util.ReadCensusException;

/**
 * @author Ricardo Suárez Rodríguez
 * @author Iván Modroño Álvarez
 */
public class PDFWriter implements LetterWriter {

	@Override
	public void write(Map<String, Object> voter) throws ReadCensusException {
		
		Document doc = null;
		FileOutputStream file = null;

		try {
			
			file = new FileOutputStream("Letters/" + voter.get("nif") + ".pdf");

			doc = new Document();
			PdfWriter.getInstance(doc, file);

			doc.open();
			doc.add(new Paragraph(
					"Don/Doña " + voter.get("name") + " ha sido añadido/a al censo." + 
					"\n\nDatos de acceso:" +
					"\nUsuario: " + voter.get("email") + 
					"\nPassword: " + voter.get("password") + 
					"\n\nSu colegio electoral es el " + voter.get("code")));

		} 
		
		catch (FileNotFoundException e) {
			throw new ReadCensusException("[ERROR] [PDFWriter] No se puede crear el archivo que contiene la carta PDF del usuario " + voter.get("nif"));
		}
		
		catch (DocumentException e) {
			throw new ReadCensusException("[ERROR] [PDFWriter] Error inesperado al crear la carta PDF para " + voter.get("nif") + ": " + e.getMessage());
		}
		
		finally {
			try { if (doc != null) doc.close(); if (file != null) file.close(); } 
			catch (Exception e) { throw new ReadCensusException("[ERROR] [PDFWriter] I/O error (" + voter.get("nif") + "): " + e.getMessage()); }
		}
	}

}
