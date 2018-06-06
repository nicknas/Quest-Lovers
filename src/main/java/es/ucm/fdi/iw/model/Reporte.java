package es.ucm.fdi.iw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Null;

@Entity
public class Reporte {
	private long id;
	private User reportador;
	private User reportado;
	private String comentario;
	private byte visto;//a 0 si no se ha visto
	//probar nullable
	private byte baneado;// a 0 si no se ha baneado
	
	@Id
	@GeneratedValue
	public long getId() {
	return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	@ManyToOne(targetEntity=User.class)
	public User getReportador() {
		return reportador;
	}
	
	public void setReportador(User reportador) {
		this.reportador = reportador;
	}

	@ManyToOne(targetEntity=User.class)
	public User getReportado() {
		return reportado;
	}
	
	public void setReportado(User reportado) {
		this.reportado = reportado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public byte getVisto() {
		return visto;
	}

	public void setVisto(byte visto) {
		this.visto = visto;
	}

	public byte getBaneado() {
		return baneado;
	}
	public void setBaneado(byte baneado) {
		this.baneado = baneado;
	}	
}
