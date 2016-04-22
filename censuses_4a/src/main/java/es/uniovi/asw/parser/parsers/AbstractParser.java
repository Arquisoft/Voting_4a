package es.uniovi.asw.parser.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.util.ReadCensusException;

/**
 * @author ivan
 */
public abstract class AbstractParser implements CensusParser {
	
	protected String filePath;
	
	List<Map<String, Object>> voters;
	Map<String, Object> voter = null;
	
	public AbstractParser(String filePath) {
		voters = new ArrayList<Map<String, Object>>();
		this.filePath = filePath;
	}
	
	@Override
	public abstract List<Map<String, Object>> parse() throws ReadCensusException;
	
}