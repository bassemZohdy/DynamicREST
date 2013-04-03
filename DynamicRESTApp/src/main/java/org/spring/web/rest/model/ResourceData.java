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

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class ResourceData {

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimeStamp;
	@JsonProperty("id")
	private Long dataId;
	private List<FieldData> fieldsData;
	@JsonIgnore
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTimeStamp;
	private Long resourceId;

	public Date getCreateTimeStamp() {
		return createTimeStamp;
	}

	public Long getDataId() {
		return dataId;
	}

	public List<FieldData> getFieldsData() {
		return fieldsData;
	}

	public Long getId() {
		return id;
	}

	public Date getModifyTimeStamp() {
		return modifyTimeStamp;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setCreateTimeStamp(Date createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public void setFieldsData(List<FieldData> fieldsData) {
		this.fieldsData = fieldsData;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setModifyTimeStamp(Date modifyTimeStamp) {
		this.modifyTimeStamp = modifyTimeStamp;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
