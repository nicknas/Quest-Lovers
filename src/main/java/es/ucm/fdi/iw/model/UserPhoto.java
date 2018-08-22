package es.ucm.fdi.iw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserPhoto {
	private int id;
	private String path;
	private User userPhoto;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "ID_USER", referencedColumnName = "id")
	public User getUserPhoto() {
		return userPhoto;
	}
	
	public void setUserPhoto(User userPhoto) {
		this.userPhoto = userPhoto;
	}
}
