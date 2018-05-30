package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.apache.log4j.Logger;


public class MatchQueries {

	private static Logger log = Logger.getLogger(MatchQueries.class);

	
	public static List<User> findMatchesUser(EntityManager entityManager, int id_user) {
		try {
			List<Match> r = entityManager.createQuery("from Match m where m.idUser1 = :id_user or m.idUser2 = :id_user", Match.class).setParameter("id_user", id_user).getResultList();
			List<User> list_users = new ArrayList<User>();
			for(int i=0; i<r.size();i++) {
				if(r.get(i).getIdUser1() == id_user) {
					list_users.add(UserQueries.findWithId(entityManager, r.get(i).getIdUser2()));
				}
				if(r.get(i).getIdUser2() == id_user) {
					list_users.add(UserQueries.findWithId(entityManager, r.get(i).getIdUser1()));
				}
				
			}
			
			return list_users;
			
		} catch (Exception e) {
			log.info("No hay matches para este usuario",e);
			return null;
		}
	}
	public static Match findMatchId(EntityManager entityManager, int id_user1, int id_user2) {
		try {
			Match m = entityManager.createQuery("from Match m where m.idUser1 = :id_user1 and m.idUser2 = :id_user2", Match.class).setParameter("id_user1", id_user1).setParameter("id_user2", id_user2).getSingleResult();
			
			
			return m;
			
		} catch (Exception e) {
			log.info("No hay matches para esos usuarios",e);
			return null;
		}
	}
	
	
	
}