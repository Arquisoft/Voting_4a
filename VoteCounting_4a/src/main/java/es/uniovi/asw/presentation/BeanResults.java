package es.uniovi.asw.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.Vote;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.instanciator.VotesShow;

@Component("results")
@Scope("application")
public class BeanResults implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Vote> votosCalculados = new ArrayList<Vote>();
	private Map<Candidature, List<Vote>> votosAgrupados = new HashMap<Candidature, List<Vote>>();
	private VotesShow votesShow;

	/**
	 * Metodo para la obtencion de la propiedad votosCalculados.
	 * 
	 * @return Lista con los votos que ya han sido recontados
	 */
	public List<Vote> getVotos() {
		return votosCalculados;
	}

	/**
	 * Metodo para la modificacion de la propiedad votosCalculados.
	 * 
	 * @param votos
	 */
	public void setVotos(List<Vote> votos) {
		this.votosCalculados = votos;
	}

	/**
	 * Metodo para la obtencion de la propiedad votesShow, desde la que se
	 * accedera a las graficas de representacion.
	 * 
	 * @return Objeto votesShow
	 */
	public VotesShow getVotesShow() {
		votesShow.setResults(votosCalculados);
		return votesShow;
	}

	/**
	 * Metodo para la modificacion de la propiedad votesShow.
	 * 
	 * @param votesShow
	 */
	public void setVotesShow(VotesShow votesShow) {
		this.votesShow = votesShow;
	}

	public Map<Candidature, List<Vote>> getVotosAgrupados() {
		return votosAgrupados;
	}

	public void setVotosAgrupados(Map<Candidature, List<Vote>> votosAgrupados) {
		this.votosAgrupados = votosAgrupados;
	}

}