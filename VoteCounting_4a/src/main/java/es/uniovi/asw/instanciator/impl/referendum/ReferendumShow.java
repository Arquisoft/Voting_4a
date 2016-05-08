package es.uniovi.asw.instanciator.impl.referendum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import es.uniovi.asw.model.*;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import es.uniovi.asw.conf.VotacionManager;
import es.uniovi.asw.instanciator.VotesShow;

public class ReferendumShow extends VotesShow {

	public ReferendumShow() {
		super();
		setUpSectChart();
		setUpLineChart();
	}

	/**
	 * Metodo de instanciacion de la propiedad heredada lineChartModel (grafica
	 * de puntos).
	 */
	private void setUpLineChart() {
		lineChartModel = new LineChartModel();
	}

	/**
	 * Metodo de instanciacion de la propiedad heredada lineChartModel (grafica
	 * de sectores).
	 */
	private void setUpSectChart() {
		pieChartModel.setFill(false);
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setTitle("Referendum - SECTORES");
		pieChartModel.setLegendPosition("ne");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.uniovi.asw.instanciator.VotesShow#setResults(java.util.List)
	 */
	@Override
	public void setResults(List<Vote> results) {
		setResultados(results);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.uniovi.asw.instanciator.VotesShow#updateChartLive()
	 */
	@Override
	protected void updateChartLive() {
		for (Candidature o : VotacionManager.getVM().getOpciones()) {
			pieChartModel.getData().put(((ReferendumOption) o).getOption(), getVotosOpcion((ReferendumOption) o).size());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.uniovi.asw.instanciator.VotesShow#updateChartLine()
	 */
	@Override
	protected void updateChartLine() {
		lineChartModel = new LineChartModel();
		LineChartSeries series;
		for (Candidature o : VotacionManager.getVM().getOpciones()) {
			series = new LineChartSeries();
			series.setLabel(((ReferendumOption) o).getOption());

			Map<Date, List<Vote>> sorted = new TreeMap<Date, List<Vote>>(
					getVotosOpcion((ReferendumOption) o).stream().collect(Collectors.groupingBy(Vote::getVoteDate)));

			for (Map.Entry<Date, List<Vote>> entrada : sorted.entrySet()) {
				series.set(new SimpleDateFormat("HH:mm").format(entrada.getKey()), entrada.getValue().size());
			}
			lineChartModel.addSeries(series);
		}

		lineChartModel.setTitle("Zoom para más detalles");
		lineChartModel.setZoom(true);
		lineChartModel.getAxis(AxisType.Y).setLabel("Cantidad de votos");
		DateAxis axis = new DateAxis("Horas");
		axis.setTickAngle(-50);
		axis.setMax("23:59");
		axis.setTickFormat("%H:%#M");

		lineChartModel.getAxes().put(AxisType.X, axis);
	}

	/**
	 * Metodo que filtra mediante la programacion funcional los votos de una
	 * opcion pasada por parametros.
	 * 
	 * @param o
	 *            ReferendumOption
	 * @return Lista de votos de la candidatura pasada por parametros
	 */
	private List<Vote> getVotosOpcion(ReferendumOption o) {
		return resultados.stream().filter(v -> v.getCandidature().getId() == o.getId()).collect(Collectors.toList());
	}
}
