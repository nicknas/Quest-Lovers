package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.apache.log4j.Logger;


public class ReporteQueries {

	private static Logger log = Logger.getLogger(ReporteQueries.class);
	
	public static List<Reporte> findNoVistos(EntityManager entityManager) {
		try {
			List<Reporte> r = entityManager.createQuery("from Reporte t where t.visto = '0'", Reporte.class).getResultList();
			return r;
			
		} catch (Exception e) {
			log.info("No hay nuevos reportes",e);
			return null;
		}
	}
	
	public static List<Reporte> findVistos(EntityManager entityManager) {
		try {
			List<Reporte> r = entityManager.createQuery("from Reporte t where t.visto = '1'", Reporte.class).getResultList();
			return r;
			
		} catch (Exception e) {
			log.info("No hay reportes vistos",e);
			return null;
		}
	}
	
	
	public static Reporte findReporteById(EntityManager entityManager, int idReporte) {
		try {
			Reporte r = entityManager.createQuery("from Reporte t where t.id = :idReporte", Reporte.class).setParameter("idReporte", idReporte).getSingleResult();
			return r;
			
		} catch (Exception e) {
			log.info("No existe ese reporte",e);
			return null;
		}
	}
	
	
}