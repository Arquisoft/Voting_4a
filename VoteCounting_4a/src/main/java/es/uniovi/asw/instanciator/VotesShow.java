package es.uniovi.asw.instanciator;

import java.util.List;

import es.uniovi.asw.model.Vote;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

public abstract class VotesShow {
	
	protected VotesShow() {
		this.pieChartModel = new PieChartModel();
		this.lineChartModel = new LineChartModel();
	}

	// Datos
	protected List<Vote> resultados;

	// Graficas
	protected PieChartModel pieChartModel;
	protected LineChartModel lineChartModel;

	/**
	 * Metodo abstracto que actuliza la grafica de sectores con los nuevos valores, si es que los hay.
	 */
	protected abstract void updateChartLive();
	
	/**
	 * Metodo abstracto que actuliza la grafica de puntos con los nuevos valores, si es que los hay.
	 */
	protected abstract void updateChartLine();

	/**
	 * Metodo para la actualizacion y acceso a la grafica de sectores.
	 * @return El objeto grafica correspondiente
	 */
	public PieChartModel getPieChartModel() {
		updateChartLive();
		return this.pieChartModel;
	}
	
	/**
	 * Metodo para la actualizacion y acceso a la grafica de puntos.
	 * @return El objeto grafica correspondiente
	 */
	public LineChartModel getLineChartModel() {
		updateChartLine();
		return this.lineChartModel;
	}

	/**
	 * Metodo abstracto mara la modificacion de la propiedad results.
	 * @param results
	 */
	public abstract void setResults(List<Vote> results);
	
	/**
	 * Metodo de acceso a la propiedad resultados.
	 * @return
	 */
	public List<Vote> getResultados() {
		return resultados;
	}

	/**
	 * Metodo abstracto mara la modificacion de la propiedad resultados.
	 * @param resultados
	 */
	public void setResultados(List<Vote> resultados) {
		this.resultados = resultados;
	}
}
