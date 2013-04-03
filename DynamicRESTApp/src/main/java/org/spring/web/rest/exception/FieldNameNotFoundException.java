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
package org.spring.web.rest.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class FieldNameNotFoundException extends NotFoundException {

	private static final String ERROR_CODE = "0002";
	private static final long serialVersionUID = -4230749539634230380L;

	public FieldNameNotFoundException(String name) {
		super(new String("Field " + name + " not found"));
	}

	public FieldNameNotFoundException(String name,
			EmptyResultDataAccessException e) {
		super(new String("Field " + name + "not found"), e);
	}

	@Override
	public String getErrorCode() {
		return ERROR_CODE;
	}
}
