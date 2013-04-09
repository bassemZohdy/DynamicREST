/**
 * 
 */
package org.spring.web.rest.repo.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.web.rest.domain.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bassem
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/test/resources/spring/root-context.xml")
@ActiveProfiles("test")
public class ResourceRepoJPATest {

	@Autowired
	ResourceRepoJPA repo;
	
	@Test
	@Transactional
	public void testSave() {
		Resource r = new Resource();
		r.setName("person");
		repo.save(r);
		repo.flush();
		assertNotNull(r.getId());
		assertEquals(1, repo.count());
	}
	
	@Test
	@Transactional
	public void testExist() {
		assertFalse(repo.exists(1l));
		Resource r = new Resource();
		r.setName("person");
		repo.save(r);
		repo.flush();
		assertTrue(repo.exists(r.getId()));
	}

	@Test
	@Transactional
	public void testFindOne() {
		Resource r = new Resource();
		r.setName("person");
		repo.save(r);
		repo.flush();
		Resource newR=repo.findOne(r.getId());
		assertNotNull(newR);
	}
	
	@Test
	@Transactional
	public void testFindByName() {
		Resource r = new Resource();
		r.setName("person");
		assertNull(repo.findByName(r.getName()));
		repo.save(r);
		repo.flush();
		Resource newR=repo.findByName(r.getName());
		assertNotNull(newR);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	@Transactional
	public void testNameUnique() {
		Resource r = new Resource();
		r.setName("person");
		repo.save(r);
		Resource r2 = new Resource();
		r2.setName("person");
		repo.save(r2);
		repo.flush();		
	}
	
	@Test
	@Transactional
	public void testDelete() {
		Resource r = new Resource();
		r.setName("person");
		repo.save(r);
		repo.flush();
		assertNotNull(r.getId());
		Long id = r.getId();
		repo.delete(id);
		r = repo.findOne(id);
		assertNull(r);
	}
	
	@Test
	@Transactional
	public void testFindDeleteAll() {
		Resource r = new Resource();
		r.setName("person");
		repo.save(r);
		repo.flush();
		assertEquals(repo.findAll().size(), 1);
		repo.deleteAll();
		assertEquals(repo.findAll().size(), 0);
	}
}
