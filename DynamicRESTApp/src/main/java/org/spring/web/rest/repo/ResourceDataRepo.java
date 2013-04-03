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

import org.spring.web.rest.model.ResourceData;

public interface ResourceDataRepo extends Repo<Long, ResourceData> {
	public void deleteAll(Long resourceId);

	// public ResourceData get(Long resourceId, Long id) throws
	// ResourceDataNotFoundException;
	// public void delete(Long resourceId, Long id) throws
	// ResourceDataNotFoundException;
	public Long getResourceDataId(Long resourceId, Long dataId);

	public Boolean isResourceDataExist(Long resourceId, Long dataId);

	public List<ResourceData> list(Long resourceId);

	public int max(Long resourceId);
}
