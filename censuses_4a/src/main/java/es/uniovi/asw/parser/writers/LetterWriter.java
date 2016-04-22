package es.uniovi.asw.parser.writers;

import java.util.Map;

import es.uniovi.asw.util.ReadCensusException;

public interface LetterWriter {

	public void write(Map<String, Object> voter) throws ReadCensusException;
	
}
