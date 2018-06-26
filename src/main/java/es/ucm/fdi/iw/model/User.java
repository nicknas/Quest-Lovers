package es.ucm.fdi.iw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	private int id;
	private String login;
	private String password;
	private String roles; // split by , to separate roles
	private String ciudad;
	private int edad;
	private String resumen;
	private String email;
	private int num_photos;
	private byte enabled;
	private List<Quest> questsEditor;
	
	@Id
	@GeneratedValue
	public int getId() {
	return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}	

	@Column(unique=true)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public byte getEnabled() {
		return enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(columnDefinition="INTEGER DEFAULT 0")
	public int getNumPhotos() {
		return this.num_photos;
	}
	
	public void setNumPhotos(int num_photos) {
		this.num_photos = num_photos;
	}
	
	@OneToMany(mappedBy = "editor_fk")
	public List<Quest> getQuestsEditor() {
		return questsEditor;
	}

	public void setQuestsEditor(List<Quest> questsEditor) {
		this.questsEditor = questsEditor;
	}

}
