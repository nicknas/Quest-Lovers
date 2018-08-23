package es.ucm.fdi.iw.model;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

public class UserPhotoQueries {
	private static Logger log = Logger.getLogger(UserPhotoQueries.class);
	
	public static UserPhoto findPhotoById(EntityManager entityManager, int id) {
		try {
			UserPhoto p = entityManager.createQuery("from UserPhoto p where p.id = :id", UserPhoto.class)
					.setParameter("id", id)
					.getSingleResult();

			return p;
		} catch (Exception e) {
			log.info("No existe la foto con id: " + id);
			return null;
		}
	}
}
