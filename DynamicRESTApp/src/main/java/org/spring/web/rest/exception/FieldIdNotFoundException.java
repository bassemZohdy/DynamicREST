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

public class FieldIdNotFoundException extends NotFoundException {

	private static final String ERROR_CODE = "0006";
	private static final long serialVersionUID = -7219070348172652536L;

	public FieldIdNotFoundException(Long id) {
		super(new String("Field with id " + id + " not found"));
	}

	public FieldIdNotFoundException(Long id, Throwable e) {
		super(new String("Field with id " + id + " not found"), e);
	}

	@Override
	public String getErrorCode() {
		return ERROR_CODE;
	}

}
