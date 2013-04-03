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

public class NotValidFieldTypeException extends NotValidException {

	private static final String ERROR_CODE = "0008";
	private static final long serialVersionUID = 3909619472704586942L;

	public NotValidFieldTypeException(String type) {
		super("Field Type " + type + " is not valid");
	}

	public NotValidFieldTypeException(String type, Throwable e) {
		super("Field Type " + type + " is not valid", e);
	}

	@Override
	public String getErrorCode() {
		return ERROR_CODE;
	}

}
