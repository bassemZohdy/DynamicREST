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

public class EmptyResourceNameException extends NotValidException {

	private static final String ERROR_CODE = "0004";
	private static final long serialVersionUID = 8504582794995186641L;

	public EmptyResourceNameException(String resurceName) {
		super("Resource name " + resurceName + " not valid to be created");
	}

	public EmptyResourceNameException(String resurceName, Throwable e) {
		super("Resource name " + resurceName + " not valid to be created", e);
	}

	@Override
	public String getErrorCode() {
		return ERROR_CODE;
	}

}
