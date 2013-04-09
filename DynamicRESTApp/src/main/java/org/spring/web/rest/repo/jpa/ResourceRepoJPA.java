/**
 * 
 */
package org.spring.web.rest.repo.jpa;

import java.util.List;

import org.spring.web.rest.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bassem
 * 
 */
public interface ResourceRepoJPA extends JpaRepository<Resource, Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	@Transactional
	public Resource save(Resource entity);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#delete(java.io.
	 * Serializable)
	 */
	@Transactional
	public void delete(Long id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	@Transactional(readOnly = true)
	public List<Resource> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findOne(java.io.
	 * Serializable)
	 */
	@Transactional(readOnly = true)
	public Resource findOne(Long id);

	@Transactional(readOnly = true)
	public Resource findByName(String name);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#exists(java.io.
	 * Serializable)
	 */
	@Transactional(readOnly = true)
	public boolean exists(Long id);

	//@Transactional(readOnly = true)
	//public boolean existsByName(String name);
}
