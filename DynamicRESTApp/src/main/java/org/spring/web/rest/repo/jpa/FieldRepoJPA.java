/**
 * 
 */
package org.spring.web.rest.repo.jpa;

import java.util.List;

import org.spring.web.rest.domain.Field;
import org.spring.web.rest.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bassem
 * 
 */
public interface FieldRepoJPA extends JpaRepository<Field, Long> {
	
	public List<Field> findAllByResource(Resource r );
	
	public Field findOne(Long id);

	public Field save(Field entity);
	
	public void delete(Long id);
	
	public Field  findByName(String name);

}
