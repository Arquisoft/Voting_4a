package es.uniovi.asw.parser.writers;

import es.uniovi.asw.util.ReadCensusException;

import java.io.File;

public class LetterWriterFactory {

	public static LetterWriter create(String format) throws ReadCensusException {

		LetterWriter writer = null;
		createLettersFolder();
		
		if (format.equals("pdf"))
			writer = new PDFWriter();
		
		else if (format.equals("word"))
			writer = new WordWriter();
		
		else if (format.equals("txt"))
			writer = new TXTWriter();

		else
			throw new ReadCensusException("[ERROR] El formato para generar las cartas (" + format + ") no está soportado");

		return writer;
	}

	private static void createLettersFolder() {
		File file = new File("Letters");

		if (!file.exists())
			file.mkdir();
	}
	
}
