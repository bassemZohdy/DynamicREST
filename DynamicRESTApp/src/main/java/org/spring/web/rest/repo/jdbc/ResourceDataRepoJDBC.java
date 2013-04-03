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
import java.util.Date;
import java.util.List;

import org.spring.web.rest.model.ResourceData;
import org.spring.web.rest.repo.ResourceDataRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ResourceDataRepoJDBC extends GenericRepoJDBC<Long, ResourceData>
		implements ResourceDataRepo {

	static private final String TABLE_NAME = "ResourceData";

	@Transactional
	public void deleteAll(Long resourceId) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ")
				.append(getTableName() + " where resourceId=?");
		jdbcTemplate.update(sb.toString(), resourceId);
	}

	@Override
	protected ResourceData entityFromResultSet(ResultSet rs)
			throws SQLException {
		ResourceData resourceData = new ResourceData();
		resourceData.setId(rs.getLong("id"));
		resourceData.setCreateTimeStamp(rs.getDate("createTimeStamp"));
		resourceData.setModifyTimeStamp(rs.getDate("modifyTimeStamp"));
		resourceData.setResourceId(rs.getLong("resourceId"));
		resourceData.setDataId(rs.getLong("dataId"));
		return resourceData;
	}

	@Override
	protected String getIdName() {
		return "id";
	}

	@Override
	protected Object[] getInsertProperties(ResourceData e) {
		return new Object[] { e.getId(), e.getDataId(), e.getResourceId(),
				new Date() };
	}

	@Override
	protected String getInsertStatment() {
		return "insert into " + getTableName() + " values (?,?,?,?,null)";
	}

	@Transactional(readOnly = true)
	public Long getResourceDataId(Long resourceId, Long dataId) {
		String sql = "select id from " + getTableName()
				+ " where resourceId=? and dataId=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { resourceId,
				dataId }, Long.class);
	}

	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected Object[] getUpdateProperties(ResourceData e) {
		return new Object[] { new Date(), e.getId() };
	}

	/*
	 * public ResourceData get(Long resourceId, Long id) throws
	 * ResourceDataNotFoundException { try { String sql = "select * from " +
	 * getTableName() + " where resourceId=? and id=?"; return
	 * jdbcTemplate.queryForObject(sql, new Object[] { resourceId, id },
	 * getRowMapper()); } catch (EmptyResultDataAccessException e) { throw new
	 * ResourceDataNotFoundException(id, e); } }
	 * 
	 * public void delete(Long resourceId, Long id) throws
	 * ResourceDataNotFoundException { try { String sql = "delete from " +
	 * getTableName() + " where resourceId=? and id=?"; jdbcTemplate.update(sql,
	 * new Object[] { resourceId, id }); } catch (EmptyResultDataAccessException
	 * e) { throw new ResourceDataNotFoundException(id, e); } }
	 */

	@Override
	protected String getUpdateStatment() {
		return "update " + getTableName() + " set modifyTimeStamp=? where id=?";
	}

	public Boolean isResourceDataExist(Long resourceId, Long dataId) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(dataId) from ").append(
				getTableName() + " where resourceId=? and dataId=?");
		Integer count = jdbcTemplate.queryForObject(sb.toString(),
				new Object[] { resourceId, dataId }, Integer.class);
		if (count == null) {
			count = 0;
		}
		return (count != 0);
	}

	@Transactional(readOnly = true)
	public List<ResourceData> list(Long resourceId) {
		String sql = "select * from " + getTableName() + " where resourceId=?";
		return jdbcTemplate.query(sql, new Object[] { resourceId },
				getRowMapper());
	}

	@Transactional(readOnly = true)
	public int max(Long resourceId) {
		StringBuilder sb = new StringBuilder();
		sb.append("select max(dataId) from ").append(
				getTableName() + " where resourceId=?");
		Integer max = jdbcTemplate.queryForObject(sb.toString(),
				new Object[] { resourceId }, Integer.class);
		return (max == null) ? 0 : max;
	}

}
