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

public class NotValidFieldNameException extends NotValidException {

	private static final String ERROR_CODE = "0006";
	private static final long serialVersionUID = -6031237008035138673L;

	public NotValidFieldNameException(String name) {
		super("Field name " + name + " is not valid");
	}

	public NotValidFieldNameException(String name, Throwable e) {
		super("Resource name " + name + " is not valid", e);
	}

	@Override
	public String getErrorCode() {
		return ERROR_CODE;
	}

}
