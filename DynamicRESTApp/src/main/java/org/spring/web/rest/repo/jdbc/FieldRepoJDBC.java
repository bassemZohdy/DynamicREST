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
import java.util.List;

import org.spring.web.rest.model.Field;
import org.spring.web.rest.repo.FieldRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FieldRepoJDBC extends GenericRepoJDBC<Long, Field> implements
		FieldRepo {

	static private final String TABLE_NAME = "Field";

	@Transactional
	public void deleteAll(Long resourceId) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ")
				.append(getTableName() + " where resourceId=?");
		jdbcTemplate.update(sb.toString(), resourceId);
	}

	@Override
	protected Field entityFromResultSet(ResultSet rs) throws SQLException {
		Field field = new Field();
		field.setId(rs.getLong("id"));
		field.setFieldId(rs.getLong("fieldId"));
		field.setResourceId(rs.getLong("resourceId"));
		field.setName(rs.getString("name"));
		field.setType(rs.getString("type"));
		return field;
	}

	@Transactional(readOnly = true)
	public Long getFieldId(Long resourceId, Long fieldId) {
		String sql = "select " + getIdName() + " from " + getTableName()
				+ " where resourceId=? and fieldId = ? ";
		Long id = jdbcTemplate.queryForObject(sql, new Object[] { resourceId,
				fieldId }, Long.class);
		return id;
	}

	@Transactional(readOnly = true)
	public Long getFieldId(Long resourceId, String name) {
		String sql = "select " + getIdName() + " from " + getTableName()
				+ " where resourceId=? and name = ? ";
		Long id = jdbcTemplate.queryForObject(sql, new Object[] { resourceId,
				name }, Long.class);
		return id;
	}

	@Override
	protected String getIdName() {
		return "id";
	}

	@Override
	protected Object[] getInsertProperties(Field e) {
		return new Object[] { e.getId(), e.getFieldId(), e.getResourceId(),
				e.getName(), e.getType() };
	}

	@Override
	protected String getInsertStatment() {
		return "insert into " + getTableName() + " values (?,?,?,?,?)";
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected Object[] getUpdateProperties(Field e) {
		return new Object[] { e.getName(), e.getType(), e.getId() };
	}

	@Override
	protected String getUpdateStatment() {
		return "update " + getTableName() + " set name=?,type=? where id=?";
	}

	public Boolean isFieldId(Long resourceId, Long fieldId) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(fieldId) from ").append(
				getTableName() + " where resourceId=? and fieldId=?");
		Integer count = jdbcTemplate.queryForObject(sb.toString(),
				new Object[] { resourceId, fieldId }, Integer.class);
		if (count == null) {
			count = 0;
		}
		return (count != 0);
	}

	public Boolean isFieldNameExist(Long resourceId, String fieldName) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(fieldId) from ").append(
				getTableName() + " where resourceId=? and name=?");
		Integer count = jdbcTemplate.queryForObject(sb.toString(),
				new Object[] { resourceId, fieldName }, Integer.class);
		if (count == null) {
			count = 0;
		}
		return (count != 0);
	}

	@Transactional(readOnly = true)
	public List<Field> list(Long resourceId) {
		String sql = "select * from " + getTableName() + " where resourceId=?";
		return jdbcTemplate.query(sql, new Object[] { resourceId },
				getRowMapper());
	}

	@Transactional(readOnly = true)
	public int max(Long resourceId) {
		StringBuilder sb = new StringBuilder();
		sb.append("select max(fieldId) from ").append(
				getTableName() + " where resourceId=?");
		Integer max = jdbcTemplate.queryForObject(sb.toString(),
				new Object[] { resourceId }, Integer.class);
		return (max == null) ? 0 : max;
	}

}
