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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.spring.web.rest.model.ResourceData;

@Entity
public class Resource extends AbstractEntity {
	
	@NotNull
	@Column(unique = true)
	private String name;
	@Transient
	private List<ResourceData> data;
	@OneToMany(cascade=CascadeType.ALL,targetEntity=Field.class)
	private List<Field> fields;


	public List<ResourceData> getData() {
		return data;
	}

	public List<Field> getFields() {
		return fields;
	}

	public String getName() {
		return name;
	}

	public void setData(List<ResourceData> data) {
		this.data = data;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void setName(String name) {
		this.name = name;
	}

}
