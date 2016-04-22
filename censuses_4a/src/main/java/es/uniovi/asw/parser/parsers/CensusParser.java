package es.uniovi.asw.parser.parsers;

import java.util.List;
import java.util.Map;

import es.uniovi.asw.util.ReadCensusException;

public interface CensusParser {
	
	List<Map<String, Object>> parse() throws ReadCensusException;

}