package es.uniovi.asw.parser.parsers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.util.PasswordGenerator;
import es.uniovi.asw.util.ReadCensusException;

public class CSVParser extends AbstractParser {
	
	public CSVParser(String filePath) {
		super(filePath);
	}

	@Override
	public List<Map<String, Object>> parse() throws ReadCensusException {
		BufferedReader br = null;
		String fileName = Paths.get(filePath).getFileName().toString();
		
		String line = "";
		String splitBy = ",";
		int row = 0;

		try {

			br = new BufferedReader(new FileReader(filePath));
			
			while ((line = br.readLine()) != null) {
				String[] voterInfo = line.split(splitBy);
				row++;
				
				try {
					voter = new HashMap<String, Object>();
					voter.put("name", voterInfo[0]);
					voter.put("email", voterInfo[1]);
					voter.put("nif", voterInfo[2]);
					voter.put("code", voterInfo[3]);
					voter.put("password", PasswordGenerator.generate(8));
					voter.put("file", fileName);
					voter.put("line", row);
				}
				
				catch(Exception e) {
					if (br != null) br.close();
					throw new ReadCensusException("[ERROR] [" + fileName + ":" + row + "] El usuario no tiene el formato correcto");
				}
				
				voters.add(voter);
			}

		} 
		
		catch (FileNotFoundException e) {
			throw new ReadCensusException("[ERROR] [" + fileName + "] El fichero no existe");
		} 
		
		catch (Exception e) {
			throw new ReadCensusException("[ERROR] [" + fileName + ":" + row + "] Fallo inesperado al leer el fichero: " + e.getMessage());
		} 
		
		finally {
			try { if (br != null) br.close(); } 
			catch (Exception e) { throw new ReadCensusException("[ERROR] [" + fileName + ":" + row + "] I/O Error: " + e.getMessage()); }
		}
		
		if (voters.isEmpty()) {
			throw new ReadCensusException("[AVISO] [" + fileName + "] El censo está vacío");
		}
		
		return voters;
	}

}
