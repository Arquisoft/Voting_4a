package es.uniovi.asw.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ivan
 */
public class Console {

	/**
	 * @param args
	 */
	public static void start(String... args) {
		
		List<String> fileNames = new ArrayList<String>();
		List<String> formats = new ArrayList<String>();
		List<String> errors = new ArrayList<String>();
		
		boolean printHelp = false;
		
		for (int i = 0; i < args.length; i++) {
			
			if (args[i].equals("-h") || args[i].equals("--help")) {
				printHelp = true;
				break;
			}
			
			else if (args[i].contains(".xlsx") || args[i].contains(".csv") || args[i].contains(".txt")) {
				if (!fileNames.contains(args[i])) fileNames.add(args[i]);
			} 
			
			else if (args[i].equals("-p") || args[i].equals("--pdf")) {
				if (!formats.contains("pdf")) formats.add("pdf");
			}
			
			else if (args[i].equals("-w") || args[i].equals("--word")) {
				if (!formats.contains("word")) formats.add("word");
			}
			
			else if (args[i].equals("-t") || args[i].equals("--txt")) {
				if (!formats.contains("txt")) formats.add("txt");
			}
			
			else {
				errors.add(args[i]);
			}
			
		}
		
		if (printHelp) {
			printHelp();
			return;
		}
		
		if (!errors.isEmpty()) {
			System.out.print("Unrecognized parameters: ");
			for (int i = 0; i < errors.size(); i++) {
				System.out.print(errors.get(i) + " ");
			}
			System.out.println("\n");
			printHelp();
			return;
		}
		
		if (fileNames.isEmpty()) {
			System.out.println("Error: no files specified\n");
			printHelp();
			return;
		}
		
		if (formats.isEmpty())
			formats.add("txt");
		
		try {
			ServicesFactory.getParserService(fileNames, formats).read();
		}
		
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void printHelp() {
		System.out.println("Usage: censusreader FILE1 FILE2... PARAM1 PARAM2...");
		System.out.println("\nRead censuses files (xlsx|csv|txt) and generate notification letters in PDF, MS Word and Plain Text formats.");
		System.out.println("\nParameters:\n");
		System.out.println("    -p, --pdf            Generate letters in PDF format");
		System.out.println("    -w, --word           Generate letters in Word format");
		System.out.println("    -t, --txt            Generate letters in Plain Text format");
		System.out.println("    -h, --help           Show this help");
		System.out.println("\nIf no format is specified the letters will be generated in Plan Text.");
	}

}
