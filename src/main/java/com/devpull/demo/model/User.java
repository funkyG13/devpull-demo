package com.devpull.demo.model;


import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="user")
public class User implements Serializable {
    
   
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="first_name")
    @NotEmpty
    private String firstName;
    
    @Column(name="last_name")
    @NotEmpty
    private String lastName;
    
    @Column(name="email")
    @NotEmpty
    private String email;
    
    @Column(name="username")
    @NotEmpty
    private String username;
    
    @Column(name="password")
    @NotEmpty
    private String password;
    
    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name="role_id")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy="senderMsg",  cascade=CascadeType.ALL)
    private List<Message> msgSender;
    
    @JsonIgnore
    @OneToMany(mappedBy="receiverMsg", cascade=CascadeType.ALL )
    private List<Message> msgReceiver;
    
    @JsonIgnore
    @OneToMany(mappedBy="userId",cascade=CascadeType.ALL)
    private List<PersistentLogins> persistentLogins;
    
    
    @ManyToMany
    @JoinTable(name="user_lang", 
	joinColumns= {@JoinColumn(name="user_id")},
	inverseJoinColumns= {@JoinColumn(name="language_id")})
    private List<Languages> languageList;
    
    public User() {
    }
    
    
    public User(String username, String password) {
	this.username = username;
	this.password = password;
}

	public User(String firstName, String lastName, String email, String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;

	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public List<Message> getMsgSender() {
		return msgSender;
	}


	public void setMsgSender(List<Message> msgSender) {
		this.msgSender = msgSender;
	}


	public List<Message> getMsgReceiver() {
		return msgReceiver;
	}


	public void setMsgReceiver(List<Message> msgReceiver) {
		this.msgReceiver = msgReceiver;
	}



	public List<PersistentLogins> getPersistentLogins() {
		return persistentLogins;
	}


	public void setPersistentLogins(List<PersistentLogins> persistentLogins) {
		this.persistentLogins = persistentLogins;
	}

	public List<Languages> getLanguageList() {
		return languageList;
	}


	public void setLanguageList(List<Languages> languageList) {
		this.languageList = languageList;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", password=" + password + ",]";
	}




    
    
    
}

