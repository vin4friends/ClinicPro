package com.app.model.impl;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.app.model.impl.validation.ValidateByService;
import com.app.model.impl.validation.ValidateByServices;
import com.app.validation.LfUserEntityValidator;

/**
 * The persistent class for the LfUser database table.
 */
//@Entity
//@Table(name = "LFUSER")
//@ValidateByServices(services = {
		//@ValidateByService(serviceName = "lfUserEntityValidator", serviceMethod = "validateUniqueLogin", serviceClass = LfUserEntityValidator.class, message = "entity.user.validation_error.login.not_unique", fieldname = "login"),
		//@ValidateByService(serviceName = "lfUserEntityValidator", serviceMethod = "validateLocationAdminCountries", serviceClass = LfUserEntityValidator.class, message = "entity.user.validation_error.countries.required", fieldname = "countries"),
		//@ValidateByService(serviceName = "lfUserEntityValidator", serviceMethod = "validateSupportAndSuperAdminCountries", serviceClass = LfUserEntityValidator.class, message = "entity.user.validation_error.countries.not_allowed", fieldname = "countries") })
public class LfUser implements Serializable {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private static final int MAX_NAME_LENGTH = 255;
	private static final int MAX_FIRSTNAME_LENGTH = 255;
	private static final int MAX_LOGIN_LENGTH = 255;
	private static final int MAX_PASSWORD_LENGTH = 255;

	//@Id
	//@Column(name = "ID", nullable = false, updatable = true)
	//@GeneratedValue(strategy = IDENTITY)
	private Long id;

	/**
	 * lastname of user
	 */
	@Length(max = MAX_NAME_LENGTH)
	@NotBlank(message = "entity.user.validation_error.name.blank")
	//@Column(name = "NAME", nullable = false, length = MAX_NAME_LENGTH)
	private String name;

	/**
	 * firstname of user
	 */
	@Length(max = MAX_FIRSTNAME_LENGTH)
	@NotBlank(message = "entity.user.validation_error.firstname.blank")
	//@Column(name = "FIRSTNAME", nullable = false, length = MAX_FIRSTNAME_LENGTH)
	private String firstname;

	@Length(max = MAX_LOGIN_LENGTH)
	@NotBlank(message = "entity.user.validation_error.login.blank")
	//@Column(name = "LOGIN", nullable = false, length = MAX_LOGIN_LENGTH, unique = true)
	private String login;

	@Length(max = MAX_PASSWORD_LENGTH)
	@NotBlank(message = "entity.user.validation_error.password.blank")
	//@Column(name = "PASSWORD", nullable = false, length = MAX_PASSWORD_LENGTH)
	private String password;

	@NotNull(message = "common.validation.notnull.message")
	//@Column(name = "ISLOCKED", nullable = false)
	private Boolean isLocked = Boolean.FALSE;
	private short isLockedDummy = 0;

	@NotEmpty(message = "entity.user.validation_error.grantedAuthorities.empty")
	//@ManyToMany
	//@JoinTable(name = "USERROLES", joinColumns = //@JoinColumn(name = "USERID", referencedColumnName = "ID"), inverseJoinColumns = //@JoinColumn(name = "ROLEID", referencedColumnName = "ID"))
	private  Set<Role> grantedAuthorities = new HashSet<Role>();

	//@ManyToMany
	//@JoinTable(name = "USERCOUNTRY", joinColumns = //@JoinColumn(name = "USERID", referencedColumnName = "ID"), inverseJoinColumns = //@JoinColumn(name = "COUNTRYCODE", referencedColumnName = "COUNTRYCODE"))
	private  Set<Country> countries = new HashSet<Country>();

	public Set<Country> getCountries() {
		return this.countries;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Set<Role> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	@Transient
	public Boolean getIsLocked() {
		return  getIsLockedDummy()==0? false: true;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked=isLocked;
		isLockedDummy=(short) (isLocked?1:0);
		
	}

	public void setGrantedAuthorities(Set<Role> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

	public void setIsLockedDummy(short isLockedDummy) {
		this.isLockedDummy=  isLockedDummy;
	}

	public short getIsLockedDummy() {
		return isLockedDummy;
	}
}