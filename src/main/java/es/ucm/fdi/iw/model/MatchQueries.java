package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.apache.log4j.Logger;


public class MatchQueries {

	private static Logger log = Logger.getLogger(MatchQueries.class);

	
	public static List<Match> findMatchesUser(EntityManager entityManager, long id_user) {
		try {
			List<Match> r = entityManager.createQuery("from Match m where m.idUser1 = :id_user", Match.class).setParameter("id_user", id_user).getResultList();
			return r;
			
		} catch (Exception e) {
			log.info("No hay matches para este usuario",e);
			return null;
		}
	}
	
	
	
}