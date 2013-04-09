/*******************************************************************************
 * Copyright (c) 2013 Bassem Reda Zohdy bassem.zohdy@gmail.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Bassem Reda Zohdy bassem.zohdy@gmail.com - initial API and implementation
 ******************************************************************************/
package org.spring.web.rest.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.spring.web.rest.model.FieldType;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name", "resource_id" }))
public class Field extends AbstractEntity {

	@Column
	private String name;
	@Enumerated(EnumType.STRING)
	private FieldType type;
	@ManyToOne(optional=false,targetEntity=Resource.class)
	@JoinColumn(nullable=false,name="resource_id",referencedColumnName="id")
	private Resource resource;
	private Long fieldId;

	public Field() {

	}

	/**
	 * @param name
	 * @param type
	 */
	public Field(String name, FieldType type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	

	/**
	 * @param name
	 * @param type
	 * @param resource
	 */
	public Field(String name, FieldType type, Resource resource) {
		super();
		this.name = name;
		this.type = type;
		this.resource = resource;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public String getName() {
		return name;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	/**
	 * @return the type
	 */
	public FieldType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(FieldType type) {
		this.type = type;
	}

	/**
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param resource
	 *            the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setName(String name) {
		this.name = name;
	}
}
