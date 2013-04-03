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

public class ResourceNameNotFoundException extends NotFoundException {

	private static final String ERROR_CODE = "0001";
	private static final long serialVersionUID = -4230749539634230380L;

	public ResourceNameNotFoundException(String resourceName) {
		super(new String("Resource " + resourceName + " not found"));
	}

	public ResourceNameNotFoundException(String resourceName, Throwable e) {
		super(new String("Resource " + resourceName + " not found"), e);
	}

	@Override
	public String getErrorCode() {
		return ERROR_CODE;
	}
}
