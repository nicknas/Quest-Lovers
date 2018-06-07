package es.ucm.fdi.iw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.log4j.Logger;

@Entity
public class Quest {
	@OneToMany(mappedBy="idQuest")
	private long id;
	private String titulo;
	private String descripcion;
	private String url;//url del json que contiene la quest
	
	
	@Id
	@GeneratedValue
	@Column(unique=true)
	public long getId() {
	return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
	
	

}
