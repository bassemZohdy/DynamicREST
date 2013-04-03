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
package org.spring.web.rest.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Resource {

	@JsonIgnore
	private List<ResourceData> data;
	private List<Field> fields;
	private Long id;
	private String name;

	public List<ResourceData> getData() {
		return data;
	}

	public List<Field> getFields() {
		return fields;
	}

	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
