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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class Field {

	@JsonProperty("id")
	private Long fieldId;
	@JsonIgnore
	private Long id;
	private String name;
	private Long resourceId;
	private String type;

	public Long getFieldId() {
		return fieldId;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public String getType() {
		return type;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public void setType(String type) {
		this.type = type;
	}

}
