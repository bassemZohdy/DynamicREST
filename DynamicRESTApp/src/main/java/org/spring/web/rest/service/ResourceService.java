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
package org.spring.web.rest.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.spring.web.rest.exception.AlreadyExistResourceNameException;
import org.spring.web.rest.exception.EmptyResourceNameException;
import org.spring.web.rest.exception.FieldIdNotFoundException;
import org.spring.web.rest.exception.FieldNameNotFoundException;
import org.spring.web.rest.exception.IdNotFoundException;
import org.spring.web.rest.exception.NotFoundException;
import org.spring.web.rest.exception.NotValidException;
import org.spring.web.rest.exception.NotValidFieldNameException;
import org.spring.web.rest.exception.NotValidFieldTypeException;
import org.spring.web.rest.exception.ResourceDataNotFoundException;
import org.spring.web.rest.exception.ResourceNameNotFoundException;
import org.spring.web.rest.model.Field;
import org.spring.web.rest.model.FieldData;
import org.spring.web.rest.model.FieldType;
import org.spring.web.rest.model.Resource;
import org.spring.web.rest.model.ResourceData;
import org.spring.web.rest.repo.FieldDataRepo;
import org.spring.web.rest.repo.FieldRepo;
import org.spring.web.rest.repo.ResourceDataRepo;
import org.spring.web.rest.repo.ResourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class ResourceService {

	private static final SimpleDateFormat SDF = new SimpleDateFormat(
			"yyyy-MMM-dd h:mm a");
	@Autowired
	private FieldDataRepo fieldDataRepo;
	@Autowired
	private FieldRepo fieldRepo;
	@Autowired
	private ResourceDataRepo resourceDataRepo;

	@Autowired
	private ResourceRepo resourceRepo;

	@Transactional(rollbackFor = NotFoundException.class)
	public void add(String resourceName, Map<String, String> map)
			throws NotFoundException {
		if (!resourceRepo.isResourceNameExist(resourceName)) {
			throw new ResourceNameNotFoundException(resourceName);
		}
		Long resourceId = resourceRepo.getResourceId(resourceName);
		Resource resource = getResource(resourceId);
		ResourceData data = new ResourceData();
		data.setResourceId(resource.getId());
		Long resourceDataId = Long.valueOf(resourceDataRepo.max() + 1);
		Long dataId = Long.valueOf(resourceDataRepo.max(resourceId) + 1);
		data.setId(resourceDataId);
		data.setDataId(dataId);
		resourceDataRepo.add(data);
		data = resourceDataRepo.get(resourceDataId);
		for (Entry<String, String> e : map.entrySet()) {
			FieldData fData = new FieldData();
			if (!fieldRepo.isFieldNameExist(resource.getId(), e.getKey())) {
				throw new FieldNameNotFoundException(e.getKey());
			}
			fData.setFieldId(fieldRepo.getFieldId(resource.getId(), e.getKey()));
			fData.setResourceDataId(resourceDataId);
			fData.setValue(e.getValue());
			fData.setId(Long.valueOf(fieldDataRepo.max() + 1));
			fieldDataRepo.add(fData);
		}
	}

	@Transactional
	public void createField(Long resourceId, Field f) throws NotFoundException,
			NotValidException {
		if (!resourceRepo.isIdExist(resourceId)) {
			throw new IdNotFoundException(resourceId);
		}
		if (fieldRepo.isFieldNameExist(resourceId, f.getName())) {
			throw new NotValidFieldNameException(f.getName());
		}
		if (FieldType.getFieldType(f.getType()).equals(FieldType.none)) {
			throw new NotValidFieldTypeException(f.getType());
		}

		f.setResourceId(resourceId);
		f.setFieldId((long) (fieldRepo.max(resourceId) + 1));
		f.setId((long) (fieldRepo.max() + 1));
		fieldRepo.add(f);
	}

	@Transactional(rollbackFor = { NotValidException.class })
	public int createResource(Resource r) throws NotValidException {
		if (!StringUtils.hasText(r.getName())) {
			throw new EmptyResourceNameException(r.getName());
		}
		if (resourceRepo.isResourceNameExist(r.getName())) {
			throw new AlreadyExistResourceNameException(r.getName());
		}
		int id = resourceRepo.max() + 1;
		r.setId((long) id);
		resourceRepo.add(r);
		return id;
	}

	@Transactional(rollbackFor = NotFoundException.class)
	public void delete(String resourceName, String dataIdString)
			throws NotFoundException {
		if (!resourceRepo.isResourceNameExist(resourceName)) {
			throw new ResourceNameNotFoundException(resourceName);
		}
		Long resourceId = resourceRepo.getResourceId(resourceName);
		Long dataId = Long.valueOf(dataIdString);
		if (!resourceDataRepo.isResourceDataExist(resourceId, dataId)) {
			throw new ResourceDataNotFoundException(dataId);
		}
		long id = resourceDataRepo.getResourceDataId(resourceId, dataId);
		fieldDataRepo.deleteAll(id);
		resourceDataRepo.delete(id);
	}

	@Transactional(rollbackFor = { NotFoundException.class })
	public void deleteField(Long resourceId, Long fieldId)
			throws NotFoundException {
		if (!resourceRepo.isIdExist(resourceId)) {
			throw new IdNotFoundException(resourceId);
		}
		if (!fieldRepo.isFieldId(resourceId, fieldId)) {
			throw new FieldIdNotFoundException(fieldId);
		}

		Long id = fieldRepo.getFieldId(resourceId, fieldId);
		fieldRepo.delete(id);
	}

	@Transactional(rollbackFor = { NotFoundException.class })
	public void deleteResource(Long resourceId) throws NotFoundException {
		if (!resourceRepo.isIdExist(resourceId)) {
			throw new IdNotFoundException(resourceId);
		}
		List<ResourceData> resourceDataList = resourceDataRepo.list(resourceId);
		for (ResourceData rd : resourceDataList) {
			fieldDataRepo.deleteAll(rd.getId());
		}
		resourceDataRepo.deleteAll(resourceId);
		fieldRepo.deleteAll(resourceId);
		resourceRepo.delete(resourceId);
	}

	@Transactional(readOnly = true)
	public Resource getResource(Long id) throws NotFoundException {
		if (!resourceRepo.isIdExist(id)) {
			throw new IdNotFoundException(id);
		}
		Resource resource = resourceRepo.get(id);
		resource.setFields(fieldRepo.list(id));
		return resource;
	}

	@Transactional(readOnly = true)
	public Resource getResource(String resourceName) throws NotFoundException {
		if (!resourceRepo.isResourceNameExist(resourceName)) {
			throw new ResourceNameNotFoundException(resourceName);
		}
		return getResource(resourceRepo.getResourceId(resourceName));
	}

	@Transactional(readOnly = true)
	public List<Map<String, String>> getResourceData(String resourceName)
			throws NotFoundException {
		if (!resourceRepo.isResourceNameExist(resourceName)) {
			throw new ResourceNameNotFoundException(resourceName);
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Long resourceId = resourceRepo.getResourceId(resourceName);
		for (ResourceData rd : resourceDataRepo.list(resourceId)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", rd.getDataId().toString());
			for (FieldData fd : fieldDataRepo.list(rd.getId())) {
				map.put(fieldRepo.get(fd.getFieldId()).getName(), fd.getValue());
			}
			if (rd.getCreateTimeStamp() != null) {
				map.put("creationDate", SDF.format(rd.getCreateTimeStamp()));
			}
			if (rd.getModifyTimeStamp() != null) {
				map.put("modifiedDate", SDF.format(rd.getModifyTimeStamp()));
			}
			list.add(map);
		}
		return list;
	}

	@Transactional(readOnly = true)
	public Map<String, String> getResourceData(String resourceName, Long dataId)
			throws NotFoundException {
		if (!resourceRepo.isResourceNameExist(resourceName)) {
			throw new ResourceNameNotFoundException(resourceName);
		}
		Long resourceId = resourceRepo.getResourceId(resourceName);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", dataId.toString());
		if (!resourceDataRepo.isResourceDataExist(resourceId, dataId)) {
			throw new ResourceDataNotFoundException(dataId);
		}
		Long id = resourceDataRepo.getResourceDataId(resourceId, dataId);
		ResourceData rd = resourceDataRepo.get(id);
		for (FieldData fd : fieldDataRepo.list(rd.getId())) {
			map.put(fieldRepo.get(fd.getFieldId()).getName(), fd.getValue());
		}
		if (rd.getCreateTimeStamp() != null) {
			map.put("creationDate", SDF.format(rd.getCreateTimeStamp()));
		}
		if (rd.getModifyTimeStamp() != null) {
			map.put("modifiedDate", SDF.format(rd.getModifyTimeStamp()));
		}
		return map;
	}

	@Transactional(readOnly = true)
	public List<Resource> listResource() {
		List<Resource> list = new ArrayList<Resource>();
		list = resourceRepo.list();
		for (Resource r : list) {
			r.setFields(fieldRepo.list(r.getId()));
		}
		return list;
	}

	@Transactional(rollbackFor = NotFoundException.class)
	public void update(String resourceName, Map<String, String> map, Long dataId)
			throws NotFoundException {
		if (!resourceRepo.isResourceNameExist(resourceName)) {
			throw new ResourceNameNotFoundException(resourceName);
		}
		Long resourceId = resourceRepo.getResourceId(resourceName);
		if (!resourceDataRepo.isResourceDataExist(resourceId, dataId)) {
			throw new ResourceDataNotFoundException(dataId);
		}
		long id = resourceDataRepo.getResourceDataId(resourceId, dataId);
		ResourceData data = resourceDataRepo.get(id);
		data.setModifyTimeStamp(new Date());
		resourceDataRepo.update(id, data);
		for (Entry<String, String> e : map.entrySet()) {
			if (e.getKey().equals("id")) {
				continue;
			}
			Long fieldId = fieldRepo.getFieldId(resourceId, e.getKey());
			FieldData fData = fieldDataRepo.getByFieldId(data.getId(), fieldId);
			fData.setValue(e.getValue());
			fieldDataRepo.update(fData.getId(), fData);
		}
	}

	@Transactional(rollbackFor = { NotFoundException.class })
	public void updateResource(Long id, Resource r) throws NotFoundException {
		if (!resourceRepo.isIdExist(id)) {
			throw new IdNotFoundException(id);
		}
		resourceRepo.update(id, r);
	}

}
