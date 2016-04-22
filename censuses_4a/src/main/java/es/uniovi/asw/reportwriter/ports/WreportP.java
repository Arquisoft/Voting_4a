package es.uniovi.asw.reportwriter.ports;

import es.uniovi.asw.reportwriter.WriteReport;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class WreportP implements WriteReport {

	private static final Logger logger = Logger.getLogger(WreportP.class.getName());
	private static WreportP instance;

	private FileWriter fw;
	private String logFile = "log.txt";

	private WreportP() throws IOException {
		fw = new FileWriter(logFile);
	}

	public static WreportP getInstance() throws IOException {

		if (instance == null)
			instance = new WreportP();

		return instance;
	}

	@Override
	public void log(String msg) throws IOException {
		String message = addDateTime(msg);

		logger.info(msg);

		fw.write(message);
		fw.flush();
	}

	@Override
	public void close() throws IOException {
		fw.close();
	}
	
	private String addDateTime(String msg) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("[")
			.append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()))
			.append("] ")
			.append(msg)
			.append("\n");
		
		return sb.toString();
	}

}
