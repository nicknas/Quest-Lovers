package es.ucm.fdi.iw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Null;

@Entity
public class Conversacion {
	private int id;
	private User user1;
	private User user2;
	private String texto;
	private List<MensajeChat> mensajes;
	
	@Id
	@GeneratedValue
	public int getId() {
	return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne(targetEntity=User.class)
	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}
	@ManyToOne(targetEntity=User.class)
	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public String getTexto() {
		return texto;
	}
	
	/*
	public List<String> getTexto() {
		return texto;
	}
	
	public void setTexto(List<String> texto) {
		this.texto = texto;
	}
	public void setTexto(String texto) {
		List<String> lista= this.texto;
		lista.add(texto); 
		this.texto = lista;
		
	}
	*/

	public void setTexto(String texto) {
		this.texto = texto;
	}
	@ElementCollection
	public List<MensajeChat> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<MensajeChat> mensajes) {
		this.mensajes = mensajes;
	}
	
}
