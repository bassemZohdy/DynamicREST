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

import org.spring.web.rest.model.FieldData;

public interface FieldDataRepo extends Repo<Long, FieldData> {

	public List<FieldData> deleteAll(Long resourceDataId);

	public FieldData getByFieldId(Long resourceDataId, Long fieldId);

	public List<FieldData> list(Long resourceDataId);
}
