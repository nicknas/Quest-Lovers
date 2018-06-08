package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import org.apache.log4j.Logger;


public class ConversacionQueries {

	private static Logger log = Logger.getLogger(ConversacionQueries.class);

	
	public static Conversacion findConversacion(EntityManager entityManager, int id_user1, int id_user2) {
		try {
			
			User u1 = UserQueries.findWithId(entityManager, id_user1);
			User u2 = UserQueries.findWithId(entityManager, id_user2);
			
			Conversacion c = entityManager.createQuery("from Conversacion m where m.user1 = :id_user1 and m.user2 = :id_user2", Conversacion.class).setParameter("id_user1", u1).setParameter("id_user2", u2).getSingleResult();
			
			return c;
			
		} catch (Exception e) {
			log.info("No hay conversacion para esos usuarios",e);
			return null;
		}
	}
	
	public static List<MensajeChat> findMensajes(EntityManager entityManager, Conversacion c) {
		try {
			return c.getMensajes();
			
		} catch (Exception e) {
			log.info("No hay mensajes en esta conversacion",e);
			return null;
		}
	}
	
	public static Conversacion findConversacionById(EntityManager entityManager, int id) {
		try {
			Conversacion c = entityManager.createQuery("from Conversacion m where m.id = :id", Conversacion.class).setParameter("id", id).getSingleResult();
			
			
			return c;
			
		} catch (Exception e) {
			log.info("No hay conversacion para esos usuarios",e);
			return null;
		}
	}
	
	public static boolean existeConversacion(EntityManager entityManager, int id_user1, int id_user2) {
		try {
			User u1 = UserQueries.findWithId(entityManager, id_user1);
			User u2 = UserQueries.findWithId(entityManager, id_user2);
			Conversacion m = entityManager.createQuery("from Conversacion m where m.user1 = :id_user1 and m.user2 = :id_user2", Conversacion.class).setParameter("id_user1", u1).setParameter("id_user2", u2).getSingleResult();
			//Conversacion m2 = entityManager.createQuery("from Conversacion m where m.user1 = :id_user2 and m.user2 = :id_user1", Conversacion.class).setParameter("id_user1", id_user1).setParameter("id_user2", id_user2).getSingleResult();
			if(m==null) {
				return false;
			}else {
				return true;
			}
			
			
		} catch (Exception e) {
			log.info("No hay conversacion para esos usuarios",e);
			return false;
		}
	}
	
	public static List<Conversacion> findConversacionesUser(EntityManager entityManager, int id_user) {
		try {
			User u1 = UserQueries.findWithId(entityManager, id_user);
			
			List<Conversacion> m = entityManager.createQuery("from Conversacion m where m.user1 = :id_user or m.user2 = :id_user", Conversacion.class).setParameter("id_user", u1).getResultList();
			
			//Conversacion m2 = entityManager.createQuery("from Conversacion m where m.user1 = :id_user2 and m.user2 = :id_user1", Conversacion.class).setParameter("id_user1", id_user1).setParameter("id_user2", id_user2).getSingleResult();
			return m;
			
		} catch (Exception e) {
			log.info("No hay conversaciones para ese usuario",e);
			return null;
		}
	}
	
	public static void crearConversacion(EntityManager entityManager, User user1, User user2) {
		Conversacion c = new Conversacion();
		
		c.setUser1(user1);
		c.setUser2(user2);
		entityManager.persist(c);
		entityManager.flush();
		
	}
	
	
	
}