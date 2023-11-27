package com.fjla.PT.model;

import java.util.Collection;
import java.util.Date;
//import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "Users") // la tabla User en h2 no se puede crear porque 'User' es palabra reservada
public class Users implements UserDetails{ 
	
	
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//private long  id;
	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_email")
	private List<Phones> phones;
	
	@Column(name = "created", nullable = false)
	private Date created = new Date();
	
	@Column(name = "modified", nullable = false)
	private Date modified = new Date();
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "isactive", nullable = false)
	private boolean isactive;
	
	//@Enumerated(EnumType.STRING)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id_role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	

	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	@PreUpdate
	public void setLastUpdate() {  this.modified = new Date(); }
	
	
	
	
	

	public Users() {

	}

	public Users(String name, String email, String password, List<Phones> phones) {
		this.id = java.util.UUID.randomUUID();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = phones;
	}

	public UUID  getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Phones> getPhones() {
		return phones;
	}

	public void setPhones(List<Phones> phones) {
		this.phones = phones;
	}
	
	
	public Date getCreated() {
		return created;
	}


	public Date getModified() {
		return modified;
	}


	public String getToken() {
		return token;
	}


	public boolean isIsactive() {
		return isactive;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phones="
				+ phones + "]";
	}


	


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.getRole();
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
		//return List.of(new SimpleGrantedAuthority(this.roles));
	}


	
	
	
	
}




/* 
@Entity
@Data
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(unique = true)
	private String name;
	private String email;
	private String password;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_email")
	private List<Phones> phones;

	private boolean enabled;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Role> roles;


	private Date created = new Date();
	private Date modified = new Date();
	
	@PreUpdate
	public void setLastUpdate() {  this.modified = new Date(); }
	
	private String token;
	
	private boolean isactive;

}*/