package com.desafiopitang.usercar.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "usuario")
public class User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8142620760369863966L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Campo obrigatório")
	@NotBlank
	private String firstName; 
	@NotBlank
	@NotNull(message = "Campo obrigatório")
	private String lastName;
	@NotBlank
	@Email(message = "Email inválido")
	private String email;	
	@NotNull(message = "Campo obrigatório")
	private Date birthday;
	@NotNull(message = "Campo obrigatório")
	@NotBlank
	private String login;
	@NotBlank
	@NotNull(message = "Campo obrigatório")
	private String password;
	@NotBlank
	@NotNull(message = "Campo obrigatório")
	private String phone;
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL )
	private List<Car> cars = new ArrayList<>(); 
	
	public void addCar(Car car) {
	       getCars().add(car);
	       car.setUser(this);
	    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
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
	
}
