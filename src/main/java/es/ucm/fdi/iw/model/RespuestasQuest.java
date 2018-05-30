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
public class RespuestasQuest {
	private int id;
	@ManyToOne(targetEntity=Quest.class)
	private int idQuest;
	@ManyToOne(targetEntity=User.class)
	private int idUser;
	private String resultado;
	
	@Id
	@GeneratedValue
	@Column(unique=true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdQuest() {
		return idQuest;
	}
	public void setIdQuest(int idQuest) {
		this.idQuest = idQuest;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
