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

public class IdNotFoundException extends NotFoundException {

	private static final String ERROR_CODE = "0007";
	private static final long serialVersionUID = -3580580257645178488L;

	public IdNotFoundException(Object obj) {
		super(new String("Id " + obj + " not found"));
	}

	@Override
	public String getErrorCode() {
		return ERROR_CODE;
	}
}
