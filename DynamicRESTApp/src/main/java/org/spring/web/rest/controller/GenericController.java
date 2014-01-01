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
import java.util.Map;

import org.spring.web.rest.exception.NotFoundException;
import org.spring.web.rest.model.Error;
import org.spring.web.rest.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericController {

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/{resourceName}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void add(@PathVariable String resourceName,
			@RequestBody Map<String, String> map) throws NotFoundException {
		resourceService.add(resourceName, map);
	}

	@RequestMapping(value = "/{resourceName}/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id,
			@PathVariable String resourceName) throws NotFoundException {
		resourceService.delete(resourceName, id);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Error error(NotFoundException e) {
		Error error = new Error();
		error.setErrorCode(e.getErrorCode());
		error.setErrorMessage(e.getMessage());
		return error;

	}

	@RequestMapping(value = "/{resourceName}", method = RequestMethod.GET)
	public List<Map<String, String>> get(@PathVariable String resourceName)
			throws NotFoundException {
		return resourceService.getResourceData(resourceName);
	}

	@RequestMapping(value = "/{resourceName}/{id}", method = RequestMethod.GET)
	public Map<String, String> get(@PathVariable String resourceName,
			@PathVariable Long id) throws NotFoundException {
		return resourceService.getResourceData(resourceName, id);
	}

	@RequestMapping(value = "/{resourceName}/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id,
			@PathVariable String resourceName,
			@RequestBody Map<String, String> map) throws NotFoundException {
		resourceService.update(resourceName, map, id);
	}
}
