package es.uniovi.asw.parser.ports;

import es.uniovi.asw.parser.ReadCensus;
import es.uniovi.asw.parser.parsers.CSVParser;
import es.uniovi.asw.parser.parsers.CensusParser;
import es.uniovi.asw.parser.parsers.TXTParser;
import es.uniovi.asw.parser.parsers.XLSXParser;
import es.uniovi.asw.parser.writers.LetterWriter;
import es.uniovi.asw.parser.writers.LetterWriterFactory;
import es.uniovi.asw.util.ReadCensusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RCensus implements ReadCensus {

	List<Map<String, Object>> voters;
	List<String> fileNames, formats;

	InsertR insertR;

	public RCensus(List<String> fileNames, List<String> formats) {
		this.fileNames = fileNames;
		this.formats = formats;

		insertR = new InsertR();
	}

	@Override
	public void read() throws IOException {

		CensusParser parser;

		for (String fileName : fileNames)

			try {

				parser = create(fileName);
				voters = parser.parse();

				insertVoters();
				writeLetters();

			} catch (ReadCensusException e) {
				insertR.logMessage(e.getMessage());
			}

	}

	private void insertVoters() throws IOException {

		List<Map<String, Object>> validVoters = new ArrayList<>();

		for (Map<String, Object> voter : voters) {
			if (insertR.insertVoter(voter))
				validVoters.add(voter);
		}

		voters = validVoters;

	}

	private void writeLetters() throws ReadCensusException, IOException {

		for (String format : formats) {
			LetterWriter leterWritter = LetterWriterFactory.create(format);

			for (Map<String, Object> voter : voters)

				try {
					leterWritter.write(voter);
				}

				catch (ReadCensusException e) {
					insertR.logMessage(e.getMessage());
				}
		}

	}

	private CensusParser create(String fileName) throws ReadCensusException {

		CensusParser parser;
		
		if (fileName.endsWith(".xlsx"))
			parser = new XLSXParser(fileName);
		
		else if (fileName.endsWith(".csv"))
			parser = new CSVParser(fileName);
		
		else if (fileName.endsWith(".txt"))
			parser = new TXTParser(fileName);

		else
			throw new ReadCensusException("ERROR - El formato del archivo que contiene el censo (" + fileName + ") no está soportado");
		
		return parser;
	}
	
}
