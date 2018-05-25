package es.ucm.fdi.iw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.log4j.Logger;

@Entity
public class Match {
	
	private long id;
	@ManyToOne(targetEntity=User.class)
	private long idUser1;
	@ManyToOne(targetEntity=User.class)
	private long idUser2;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getIdUser1() {
		return idUser1;
	}
	public void setIdUser1(long idUser1) {
		this.idUser1 = idUser1;
	}
	public long getIdUser2() {
		return idUser2;
	}
	public void setIdUser2(long idUser2) {
		this.idUser2 = idUser2;
	}
	
}
