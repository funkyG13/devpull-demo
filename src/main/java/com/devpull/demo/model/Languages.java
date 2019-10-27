package com.devpull.demo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
public class Languages implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="language_id")
	private int id;
	
	@Column(name="language_name")
	private String languageName;
	
	@ManyToMany(mappedBy = "languageList")
	private List<User> users ;
	
	public Languages() {

	}

	public Languages(int id, String languageName) {
		this.id = id;
		this.languageName = languageName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	@Override
	public String toString() {
		return "Languages [id=" + id + ", languageName=" + languageName + "]";
	}
	
	
	
}
