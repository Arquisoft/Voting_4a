package es.uniovi.asw.reportwriter;

import java.io.IOException;

public interface WriteReport {

	void log(String msg) throws IOException;

	void close() throws IOException;

}
