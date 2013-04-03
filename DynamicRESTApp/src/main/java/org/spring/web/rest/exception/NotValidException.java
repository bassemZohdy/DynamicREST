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

public abstract class NotValidException extends Exception {

	private static final long serialVersionUID = 6133552631841406451L;

	public NotValidException(String message) {
		super(message);
	}

	public NotValidException(String message, Throwable e) {
		super(message, e);
	}

	public abstract String getErrorCode();
}
