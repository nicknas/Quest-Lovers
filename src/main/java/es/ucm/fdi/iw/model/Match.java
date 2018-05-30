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
	
	private int id;
	@ManyToOne(targetEntity=User.class)
	private int idUser1;
	@ManyToOne(targetEntity=User.class)
	private int idUser2;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdUser1() {
		return idUser1;
	}
	public void setIdUser1(int idUser1) {
		this.idUser1 = idUser1;
	}
	public int getIdUser2() {
		return idUser2;
	}
	public void setIdUser2(int idUser2) {
		this.idUser2 = idUser2;
	}
	
}
