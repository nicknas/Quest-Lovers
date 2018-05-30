package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

import es.ucm.fdi.iw.model.User;


public class RespuestasQuestQueries {

	private static Logger log = Logger.getLogger(RespuestasQuestQueries.class);

	
	public static List<Quest> findAllQuests(EntityManager entityManager) {
		try {
			List<Quest> r = entityManager.createQuery("from Quest", Quest.class).getResultList();
			return r;
			
		} catch (Exception e) {
			log.info("No hay Quests",e);
			return null;
		}
	}
	public static List<RespuestasQuest> findQuestsByRespuesta(EntityManager entityManager, String respuesta) {//id_quest
		try {
			List<RespuestasQuest> u = entityManager.createQuery("from RespuestasQuest t where t.resultado = :resultado", RespuestasQuest.class).setParameter("resultado", respuesta).getResultList();
			return u;
			
		} catch (Exception e) {
			log.info("Error",e);
			return null;
		}
	}
	
	
}