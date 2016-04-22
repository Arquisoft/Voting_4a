package es.uniovi.asw.parser.writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import es.uniovi.asw.util.ReadCensusException;

/**
 * @author Ricardo Suárez Rodríguez
 * @author Iván Modroño Álvarez
 */
public class WordWriter implements LetterWriter {

	@Override
	public void write(Map<String, Object> voter) throws ReadCensusException {
		
		XWPFDocument document = new XWPFDocument();
		FileOutputStream out = null;
		
		try {
			
			out = new FileOutputStream(new File("Letters/" + voter.get("nif") + ".docx"));
			
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			
			run.setFontFamily("Tahoma");
			run.setFontSize(12);
			
			run.setText("Don/Doña " + voter.get("name") + " ha sido añadido/a al censo.");
			run.addBreak();
			run.addBreak();
			run.setText("Datos de acceso:");
			run.addBreak();
			run.setText("Usuario: " + voter.get("email"));
			run.addBreak();
			run.setText("Password: " + voter.get("password"));
			run.addBreak();
			run.addBreak();
			run.setText("Su colegio electoral es el " + voter.get("code"));
			
			document.write(out);

		} 
		
		catch (FileNotFoundException e) {
			throw new ReadCensusException("[ERROR] [WordWriter] No se puede crear el archivo que contiene la carta Word del usuario " + voter.get("nif"));
		} 
		
		catch (Exception e) {
			throw new ReadCensusException("[ERROR] [WordWriter] Error inesperado al crear la carta Word para " + voter.get("nif") + ": " + e.getMessage());
		}
		
		finally {
			try { if (out != null) out.close(); if (document != null) document.close(); } 			
			catch (Exception e) { throw new ReadCensusException("[ERROR] [WordWriter] I/O error (" + voter.get("nif") + "): " + e.getMessage()); }
		}

	}

}
