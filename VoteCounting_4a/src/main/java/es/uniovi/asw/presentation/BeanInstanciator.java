package es.uniovi.asw.presentation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.ReferendumOption;
import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.types.ElectionDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.conf.VotacionManager;
import es.uniovi.asw.instanciator.AbstractFactory;
import es.uniovi.asw.instanciator.ReferendumFactory;
import es.uniovi.asw.instanciator.VotesCalc;
import es.uniovi.asw.persistence.OpcionesService;
import es.uniovi.asw.persistence.VotacionesService;
import es.uniovi.asw.persistence.VotosService;
import es.uniovi.asw.presentation.util.VotesUtil;

@Component("instanciatorBean")
@Scope("application")
public class BeanInstanciator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private OpcionesService opcionesService;

	@Autowired
	private VotosService votosService;

	@Autowired
	private VotacionesService votacionesService;

	private String pageView;
	private VotacionManager VMaganer = VotacionManager.getVM();
	private static final long TIEMPO_MS = 15000;

	// Inyeccion de dependencia
	@ManagedProperty(value = "#{results}")
	private BeanResults beanResults;

	public BeanResults getBeanResults() {
		return beanResults;
	}

	public void setBeanResults(BeanResults beanResults) {
		this.beanResults = beanResults;
	}

	private VotesCalc votesCalc;

	/**
	 * Metodo que se inicia siempre en la construccion del bean, y que se
	 * encarga de establecer la configuracion e instanciar adecuadamente todo lo
	 * necesario.
	 */
	@PostConstruct
	public void init() {
		System.out.println("BeanInstanciator - INIT");

		List<Election> votaciones = votacionesService.getVoteInfo(true);

		//////////////////////////////////////////////////////////////////////////////////////
		// TODO: seleccionar la votación que escoja el usuario en la UI, no la primera activa
		// TODO: comprobar que hay votaciones activas y si no las hay evitar que reviente
		//////////////////////////////////////////////////////////////////////////////////////
		Election vot = votaciones.get(0);
		//////////////////////////////////////////////////////////////////////////////////////

		VotacionManager.getVM().setOpciones(opcionesService.getOpciones(vot));
		VotacionManager.getVM().setVotacion(vot);

		beanResults = (BeanResults) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(new String("beanResults"));

		if (beanResults == null) {
			System.out.println("results - No existia");
			beanResults = new BeanResults();
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(new String("beanResults"),
					beanResults);
		}
		cargarTipoVotacion();
	}

	/**
	 * Identifica la votación que está activa y en función de cual sea
	 * instanciará el sistema de una forma u otra.
	 */
	private void cargarTipoVotacion() {
		String candidatureType = VotacionManager.getVM().getOpciones().get(0).getCandidatureType();
		// Indicamos que pagina tiene que ir
		setPageView(candidatureType.toLowerCase() + ".xhtml");
		AbstractFactory absf;
		switch (candidatureType.toUpperCase()) {
		case "REFERENDUM": {
			absf = new ReferendumFactory();
			break;
		}
		default: {
			throw new RuntimeException("Tipo de votación desconocida");
		}
		}
		this.votesCalc = absf.crearCalc();
		beanResults.setVotesShow(absf.crearShow());
		calculoVotosPeriodicos();
	}

	/**
	 * Metodo que se ejecuta periodicamente cada 15 segundos y que actualiza el
	 * recuento de votos no leidos.
	 */
	private void calculoVotosPeriodicos() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				List<Vote> votoscalculados = votosService.getVotes(false);
				beanResults.getVotos().addAll(votesCalc.calcularResultados(votoscalculados));
				beanResults.getVotosAgrupados().putAll(VotesUtil.groupByOption(votoscalculados));

				for (Vote v : votoscalculados) {
					v.setRead(true);
					votosService.updateVotes(v);
				}
			}
		};
		timer.schedule(task, 0, TIEMPO_MS);
	}

	/**
	 * Metodo para la obtencion de la propiedad pageView.
	 * 
	 * @return String pageView
	 */
	public String getPageView() {
		return pageView;
	}

	/**
	 * Metodo para la modificacion de la propiedad pageView.
	 * 
	 * @param pageView
	 */
	public void setPageView(String pageView) {
		this.pageView = pageView;
	}

	/**
	 * Metodo para la obtencion de la propiedad votesCalc.
	 * 
	 * @return Objecto de tipo VotesCalc
	 */
	public VotesCalc getVotesCalc() {
		return votesCalc;
	}

	/**
	 * Metodo para la modificacion de la propiedad votesCalc.
	 * 
	 * @param votesCalc
	 */
	public void setVotesCalc(VotesCalc votesCalc) {
		this.votesCalc = votesCalc;
	}

	/**
	 * Metodo de acceso al atributo VManager.
	 * 
	 * @return Objeto de tipo VotacionManager
	 */
	public VotacionManager getVMaganer() {
		return VMaganer;
	}
}
