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

public class FieldData {

	private Long fieldId;
	private Long id;
	private Long resourceDataId;
	private String value;

	public Long getFieldId() {
		return fieldId;
	}

	public Long getId() {
		return id;
	}

	public Long getResourceDataId() {
		return resourceDataId;
	}

	public String getValue() {
		return value;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setResourceDataId(Long resourceDataId) {
		this.resourceDataId = resourceDataId;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
