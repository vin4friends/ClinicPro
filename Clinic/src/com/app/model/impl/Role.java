/**
 * 
 */
package com.app.model.impl;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

/**
 * A user role in the system.
 * 
 * //@author jomu
 */
//@Entity
//@Table(name = "ROLE")
public class Role implements Serializable {
	private static final long serialVersionUID = 7927767267441057794L;

	private static final int MAX_NAME_LENGTH = 255;
	private static final int MAX_AUTHORITY_LENGTH = 255;

	//@Id
	//@Column(name = "ID", nullable = false, updatable = true)
	//@GeneratedValue(strategy = IDENTITY)
	private Long id;

	/**
	 * The message key which will be used to display this role to an user.
	 */
	@Length(max = MAX_NAME_LENGTH)
	//@Column(name = "NAME", nullable = false, unique = true, length = MAX_NAME_LENGTH)
	private String name;

	/**
	 * The authority which this role will grant.
	 */
	@Length(max = MAX_AUTHORITY_LENGTH)
	//@Column(name = "AUTHORITY", nullable = false, unique = true, length = MAX_AUTHORITY_LENGTH)
	private String authority;

	/**
	 * Getter for the id-attribute.
	 * 
	 * //@return the id of the entity.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the id-attribute.
	 * 
	 * //@param id
	 *            the new id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for the name-attribute. usually this is a messagekey to localize
	 * the display value of the name.
	 * 
	 * //@return the name/messageKey of the role.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name-attribute.
	 * 
	 * //@param name
	 *            the new name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the Authority which this role grants.
	 * 
	 * //@return the gratned Authority.
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * Setter for the Authrority-attribute.
	 * 
	 * //@param authority
	 *            the new authority this role will grant.
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
