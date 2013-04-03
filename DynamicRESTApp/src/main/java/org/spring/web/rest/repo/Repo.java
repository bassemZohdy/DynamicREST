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
package org.spring.web.rest.repo;

import java.io.Serializable;
import java.util.List;

public interface Repo<ID extends Serializable, E> {

	public void add(E e);

	public E delete(ID id);

	public E get(ID id);

	public Boolean isIdExist(ID id);

	public List<E> list();

	public int max();

	public void update(ID id, E e);

}
