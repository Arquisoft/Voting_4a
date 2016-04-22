package es.uniovi.asw.parser.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.util.PasswordGenerator;
import es.uniovi.asw.util.ReadCensusException;

public class XLSXParser extends AbstractParser {

	public XLSXParser(String filePath) {
		super(filePath);
	}

	@Override
	public List<Map<String, Object>> parse() throws ReadCensusException {
		XSSFWorkbook listaVotantes = null;
		String fileName = Paths.get(filePath).getFileName().toString();

		try {

			FileInputStream file = new FileInputStream(new File(filePath));
			listaVotantes = new XSSFWorkbook(file);

			XSSFSheet hoja = listaVotantes.getSheetAt(0);
			Iterator<Row> rowIterator = hoja.iterator();
			Row row;

			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				Iterator<Cell> columns = row.cellIterator();
				
				if (!columns.hasNext())
					continue;
				
				try {
					voter = new HashMap<String, Object>();
					voter.put("name", columns.next().getStringCellValue());
					voter.put("email", columns.next().getStringCellValue());
					voter.put("nif", columns.next().getStringCellValue());
					voter.put("code", String.valueOf((int)columns.next().getNumericCellValue()));
					voter.put("password", PasswordGenerator.generate(8));
					voter.put("file", fileName);
					voter.put("line", row.getRowNum());
				}
				
				catch(Exception e) {
					throw new ReadCensusException("[ERROR] [" + fileName + ":" + row.getRowNum() + "] El usuario no tiene el formato correcto");
				}
				
				voters.add(voter);
			}
			
		}
		
		catch (FileNotFoundException e) { 
			throw new ReadCensusException("[ERROR] [" + fileName + "] El fichero no existe");
		}
		
		catch (Exception e) {
			throw new ReadCensusException("[ERROR] [" + fileName + "] Fallo inesperado al leer el fichero: " + e.getMessage());
		}
		
		finally {
			try { if (listaVotantes != null) listaVotantes.close(); } 
			catch (Exception e) { throw new ReadCensusException("[ERROR] [" + fileName + "] I/O Error: " + e.getMessage()); }
		}
		
		if (voters.isEmpty()) {
			throw new ReadCensusException("[AVISO] [" + fileName + "] El censo está vacío");
		}
		
		return voters;
	}

}
