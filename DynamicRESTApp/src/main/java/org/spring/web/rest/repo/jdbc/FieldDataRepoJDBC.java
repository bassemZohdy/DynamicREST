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

import org.spring.web.rest.model.FieldData;
import org.spring.web.rest.repo.FieldDataRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FieldDataRepoJDBC extends GenericRepoJDBC<Long, FieldData>
		implements FieldDataRepo {

	static private final String TABLE_NAME = "FieldData";

	@Transactional
	public List<FieldData> deleteAll(Long resourceDataId) {
		List<FieldData> list = list(resourceDataId);
		String sql = "delete from " + getTableName()
				+ " where resourceDataId=?";
		jdbcTemplate.update(sql, new Object[] { resourceDataId });
		return list;
	}

	@Override
	protected FieldData entityFromResultSet(ResultSet rs) throws SQLException {
		FieldData fieldData = new FieldData();
		fieldData.setId(rs.getLong("id"));
		fieldData.setValue(rs.getString("value"));
		fieldData.setFieldId(rs.getLong("fieldId"));
		fieldData.setResourceDataId(rs.getLong("resourceDataId"));
		return fieldData;
	}

	@Transactional(readOnly = true)
	public FieldData getByFieldId(Long resourceDataId, Long fieldId) {
		String sql = "select * from " + getTableName()
				+ " where resourceDataId=? and fieldId=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { resourceDataId,
				fieldId }, getRowMapper());
	}

	@Override
	protected String getIdName() {
		return "id";
	}

	@Override
	protected Object[] getInsertProperties(FieldData e) {
		return new Object[] { e.getId(), e.getValue(), e.getFieldId(),
				e.getResourceDataId() };
	}

	@Override
	protected String getInsertStatment() {
		return "insert into " + getTableName() + " values (?,?,?,?)";
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected Object[] getUpdateProperties(FieldData e) {
		return new Object[] { e.getValue(), e.getId() };
	}

	@Override
	protected String getUpdateStatment() {
		return "update " + getTableName() + " set value=? where id=?";
	}

	@Transactional(readOnly = true)
	public List<FieldData> list(Long resourceDataId) {
		String sql = "select * from " + getTableName()
				+ " where resourceDataId=?";
		return jdbcTemplate.query(sql, new Object[] { resourceDataId },
				getRowMapper());
	}

}
