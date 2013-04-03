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
package org.spring.web.rest.repo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.spring.web.rest.model.Resource;
import org.spring.web.rest.repo.ResourceRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ResourceRepoJDBC extends GenericRepoJDBC<Long, Resource> implements
		ResourceRepo {

	static private final String TABLE_NAME = "Resource";

	@Override
	protected Resource entityFromResultSet(ResultSet rs) throws SQLException {
		Resource resource = new Resource();
		resource.setId(rs.getLong("id"));
		resource.setName(rs.getString("name"));
		return resource;
	}

	@Override
	protected String getIdName() {
		return "id";
	}

	@Override
	protected Object[] getInsertProperties(Resource e) {
		return new Object[] { e.getId(), e.getName() };
	}

	@Override
	protected String getInsertStatment() {
		return "insert into " + getTableName() + " values (?,?)";
	}

	@Transactional(readOnly = true)
	public Long getResourceId(String name) {
		String sql = "select id from " + getTableName() + " where name=?";
		Long id = jdbcTemplate.queryForObject(sql, new Object[] { name },
				Long.class);
		return id;
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected Object[] getUpdateProperties(Resource e) {
		return new Object[] { e.getName(), e.getId() };
	}

	@Override
	protected String getUpdateStatment() {
		return "update " + getTableName() + " set name=? where id=?";
	}

	public Boolean isResourceNameExist(String resourceName) {
		String sql = "select count(" + getIdName() + ") " + "from "
				+ getTableName() + " where name=?";
		Integer count = jdbcTemplate.queryForObject(sql,
				new Object[] { resourceName }, Integer.class);
		if (count == null) {
			count = 0;
		}
		return (count != 0);
	}
}
