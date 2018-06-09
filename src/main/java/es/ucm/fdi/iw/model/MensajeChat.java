package es.ucm.fdi.iw.model;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;




@Entity
public class MensajeChat {
	private int id;
	private User sender;
	//private User user2;
	private String texto;
	//private hashmap <id text>
	
	@Id
	@GeneratedValue
	public int getId() {
	return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	@ManyToOne(targetEntity=User.class)
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
}
