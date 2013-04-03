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

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.spring.web.rest.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericRepoJDBC<ID extends Serializable, E extends Object>
		implements Repo<ID, E> {

	@Autowired
	private DataSource dataSource;
	protected JdbcTemplate jdbcTemplate;

	@Transactional
	public void add(E e) {
		jdbcTemplate.update(getInsertStatment(), getInsertProperties(e));
	}

	@Transactional
	public E delete(ID id) {
		E e = get(id);
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ").append(getTableName())
				.append(" where id = ?");
		jdbcTemplate.update(sb.toString(), new Object[] { id });
		return e;
	}

	protected abstract E entityFromResultSet(ResultSet rs) throws SQLException;

	@Transactional(readOnly = true)
	public E get(ID id) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ").append(getTableName())
				.append(" where id = ?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[] { id },
				getRowMapper());
	}

	protected abstract String getIdName();

	protected abstract Object[] getInsertProperties(E e);

	protected abstract String getInsertStatment();

	protected RowMapper<E> getRowMapper() {
		return new RowMapper<E>() {
			public E mapRow(ResultSet rs, int index) throws SQLException {
				return entityFromResultSet(rs);
			}
		};
	}

	protected abstract String getTableName();

	protected abstract Object[] getUpdateProperties(E e);

	protected abstract String getUpdateStatment();

	@PostConstruct
	private void init() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Boolean isIdExist(ID id) {
		StringBuilder sb = new StringBuilder();
		sb.append("select max(").append(getIdName()).append(") from ")
				.append(getTableName());
		Integer count = jdbcTemplate.queryForObject(sb.toString(),
				Integer.class);
		if (count == null) {
			count = 0;
		}
		return (count != 0);
	}

	@Transactional(readOnly = true)
	public List<E> list() {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ").append(getTableName());
		return jdbcTemplate.query(sb.toString(), getRowMapper());
	}

	@Transactional(readOnly = true)
	public int max() {
		StringBuilder sb = new StringBuilder();
		sb.append("select max(").append(getIdName()).append(") from ")
				.append(getTableName());
		Integer max = jdbcTemplate.queryForObject(sb.toString(), Integer.class);
		return (max == null) ? 0 : max;
	}

	@Transactional
	public void update(ID id, E e) {
		jdbcTemplate.update(getUpdateStatment(), getUpdateProperties(e));
	}
}
