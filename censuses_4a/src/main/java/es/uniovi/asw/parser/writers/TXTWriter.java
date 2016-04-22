package es.uniovi.asw.parser.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import es.uniovi.asw.util.ReadCensusException;

/**
 * @author Ricardo Suárez Rodríguez
 * @author Iván Modroño Álvarez
 */
public class TXTWriter implements LetterWriter {

	@Override
	public void write(Map<String, Object> voter) throws ReadCensusException {

		FileWriter writeLetter = null;
		
		try {
			
			File letter = new File("Letters/" + voter.get("nif") + ".txt");
			writeLetter = new FileWriter(letter, false);

			writeLetter.write(
					"Don/Doña " + voter.get("name") + " ha sido añadido/a al censo." + 
					"\n\nDatos de acceso:" + 
					"\nUsuario: " + voter.get("email") + 
					"\nPassword: " + voter.get("password") + 
					"\n\nSu colegio electoral es el " + voter.get("code")
				);
			
		} 
		
		catch (IOException e) {
			throw new ReadCensusException("[ERROR] [TXTWriter] No se puede crear el archivo que contiene la carta PDF del usuario " + voter.get("nif"));
		}
		
		catch (Exception e) {
			throw new ReadCensusException("[ERROR] [TXTWriter] Error inesperado al crear la carta TXT para " + voter.get("nif") + ": " + e.getMessage());
		}
		
		finally {
			try { if (writeLetter != null) writeLetter.close(); } 			
			catch (Exception e) { throw new ReadCensusException("[ERROR] [TXTWriter] I/O error (" + voter.get("nif") + "): " + e.getMessage()); }
		}
	}

}
