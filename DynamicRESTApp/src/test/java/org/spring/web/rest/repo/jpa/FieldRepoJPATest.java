/**
 * 
 */
package org.spring.web.rest.repo.jpa;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.web.rest.domain.Field;
import org.spring.web.rest.domain.Resource;
import org.spring.web.rest.model.FieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bassem
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/resources/spring/root-context.xml")
@ActiveProfiles("test")
public class FieldRepoJPATest {

	@Autowired
	ResourceRepoJPA resourceRepo;

	@Autowired
	FieldRepoJPA fieldRepo;

	@Before
	public void init() {
		Resource r = new Resource();
		r.setName("person");
		resourceRepo.save(r);
		resourceRepo.flush();
	}

	@After
	public void done() {
		resourceRepo.deleteAll();
		resourceRepo.flush();
	}

	@Test
	@Transactional
	public void testSave() {
		Resource r = resourceRepo.findByName("person");
		r.setFields(new ArrayList<Field>());
		r.getFields().add(new Field("firstName", FieldType.string,r));
		r.getFields().add(new Field("lastname", FieldType.string,r));
		resourceRepo.save(r);
		assertEquals(fieldRepo.findAllByResource(r).size(),2);
		assertEquals(fieldRepo.findAll().size(),2);
	}
	
	@Test
	@Transactional
	public void testSaveField() {
		Resource r = resourceRepo.findByName("person");
		fieldRepo.save(new Field("firstName", FieldType.string,r));
		fieldRepo.save(new Field("lastname", FieldType.string,r));
		fieldRepo.flush();
		r = resourceRepo.findByName("person");
		assertEquals(fieldRepo.findAll().size(),2);
		assertEquals(fieldRepo.findAllByResource(r).size(),2);
	}

}
