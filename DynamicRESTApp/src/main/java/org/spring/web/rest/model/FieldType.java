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
package org.spring.web.rest.model;

import org.springframework.util.StringUtils;

public enum FieldType {
	date, integer, list, none, refrence, string;

	public static FieldType getFieldType(String type) {
		if (!StringUtils.hasText(type)) {
			return none;
		}
		if (type.equalsIgnoreCase(string.name())) {
			return string;
		} else if (type.equalsIgnoreCase(integer.name())) {
			return integer;
		} else if (type.equalsIgnoreCase(date.name())) {
			return date;
		} else if (type.equalsIgnoreCase(refrence.name())) {
			return refrence;
		} else if (type.equalsIgnoreCase(list.name())) {
			return list;
		} else {
			return none;
		}
	}
}
