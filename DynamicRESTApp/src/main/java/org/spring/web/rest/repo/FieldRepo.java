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
package org.spring.web.rest.repo;

import java.util.List;

import org.spring.web.rest.model.Field;

public interface FieldRepo extends Repo<Long, Field> {

	public void deleteAll(Long resourceId);

	public Long getFieldId(Long resourceId, Long fieldId);

	public Long getFieldId(Long resourceId, String name);

	public Boolean isFieldId(Long resourceId, Long fieldId);

	public Boolean isFieldNameExist(Long resourceId, String fieldName);

	public List<Field> list(Long resourceId);

	public int max(Long resourceId);
}
