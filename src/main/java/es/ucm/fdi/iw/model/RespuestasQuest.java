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
	private long id;
	@ManyToOne(targetEntity=Quest.class)
	private long idQuest;
	@ManyToOne(targetEntity=User.class)
	private long idUser;
	private String resultado;
	
	@Id
	@GeneratedValue
	@Column(unique=true)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getIdQuest() {
		return idQuest;
	}
	public void setIdQuest(long idQuest) {
		this.idQuest = idQuest;
	}
	
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
