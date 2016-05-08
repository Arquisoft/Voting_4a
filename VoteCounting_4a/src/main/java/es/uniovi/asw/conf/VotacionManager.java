package es.uniovi.asw.conf;

import java.util.List;

import es.uniovi.asw.model.*;

public class VotacionManager {
	
	private static VotacionManager vm = null;
	
	private Election votacion;
	private List<Candidature> opciones;
	
	private VotacionManager() { }
	
	public static VotacionManager getVM() {
		if (vm == null) {
			vm = new VotacionManager();
		}
		return vm;
	}

	public void setVotacion(Election votacion) {
		this.votacion = votacion;
	}

	public void setOpciones(List<Candidature> opciones) {
		this.opciones = opciones;
	}

	public List<Candidature> getOpciones() {
		return opciones;
	}

	public Election getVotacion() {
		return votacion;
	}
}
