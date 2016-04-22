package es.uniovi.asw.dbupdate.ports;

import es.uniovi.asw.util.ServicesFactory;

import java.io.IOException;

public class WreportR {

	public void log(String msg) throws IOException {
		if (verify(msg)) {
			ServicesFactory.getWriteReportService().log(msg);
		}
	}
	
	private boolean verify(String text) {
		return text != null && !text.equals("");
	}
	
}
