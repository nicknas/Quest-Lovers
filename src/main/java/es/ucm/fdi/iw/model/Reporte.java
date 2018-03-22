package es.ucm.fdi.iw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Null;

@Entity
public class Reporte {
	private long id;
	private String reportador;
	private String reportado;
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

	public String getReportador() {
		return reportador;
	}

	public void setReportador(String reportador) {
		this.reportador = reportador;
	}

	public String getReportado() {
		return reportado;
	}

	public void setReportado(String reportado) {
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
