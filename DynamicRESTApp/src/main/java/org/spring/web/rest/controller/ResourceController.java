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
package org.spring.web.rest.controller;

import java.util.List;

import org.spring.web.rest.exception.NotFoundException;
import org.spring.web.rest.exception.NotValidException;
import org.spring.web.rest.model.Error;
import org.spring.web.rest.model.Field;
import org.spring.web.rest.model.Resource;
import org.spring.web.rest.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/resource", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Resource r)
			throws NotValidException, NotFoundException {
		int id = resourceService.createResource(r);
		UriComponents uriCom = MvcUriComponentsBuilder.fromMethodName(
				this.getClass(), "get", id).buildAndExpand();
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", uriCom.toUri().toString());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/resource/{resourceId}/field", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createField(@RequestBody Field f, @PathVariable Long resourceId)
			throws NotFoundException, NotValidException {
		resourceService.createField(resourceId, f);
	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws NotFoundException {
		resourceService.deleteResource(id);
	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Resource r, @PathVariable Long id)
			throws NotFoundException {
		resourceService.updateResource(id, r);
	}

	@RequestMapping(value = "/resource/{resourceId}/field/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteField(@PathVariable Long resourceId, @PathVariable Long id)
			throws NotFoundException {
		resourceService.deleteField(resourceId, id);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Error error(NotFoundException e) {
		Error error = new Error();
		error.setErrorCode(e.getErrorCode());
		error.setErrorMessage(e.getMessage());
		return error;

	}

	@ExceptionHandler(NotValidException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public Error error(NotValidException e) {
		Error error = new Error();
		error.setErrorCode(e.getErrorCode());
		error.setErrorMessage(e.getMessage());
		return error;

	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
	public Resource get(@PathVariable Long id) throws NotFoundException {
		return resourceService.getResource(id);
	}

	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	public List<Resource> list() {
		return resourceService.listResource();
	}

	@RequestMapping(value = "/resource/{resourceId}/field", method = RequestMethod.GET)
	public List<Field> listField(@PathVariable Long resourceId)
			throws NotFoundException, NotValidException {
		return resourceService.getResource(resourceId).getFields();
	}
}
