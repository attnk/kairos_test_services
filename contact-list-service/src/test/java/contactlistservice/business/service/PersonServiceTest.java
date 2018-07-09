package contactlistservice.business.service;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import contactlistservice.entity.Person;
import contactlistservice.exception.RepositoryException;
import contactlistservice.exception.ServiceException;
import contactlistservice.repository.PersonRepository;
import contactlistservice.template.MockTemplate;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;
	
	@InjectMocks
	private PersonService service;
	
	@Before
	public void init() {
		loadTemplates("contactlistservice.template.entity");
	}
	
	// GET - person by id
	@Test
	public void shouldGetPersonByIdReturnPersonWhenEverythingIsOk() 
			throws RepositoryException {
		// GIVEN
		Optional<Person> person = Optional.of(MockTemplate.getPersonOne());
		long id = person.get().getId();
		Person result = null;
		
		when(personRepository.findById(id)).thenReturn(person);
		
		// WHEN
		result = service.getPerson(id);
		
		// THEN
		assertNotNull(result);
		assertTrue(person.get().equals(result));
	}
	
	@Test(expected = RepositoryException.class)
	public void shouldGetPersonByIdThrowRepositoryExceptionWhenPersonRepositoryThrowSomeException() 
			throws RepositoryException {
		// GIVEN
		Optional<Person> person = Optional.of(MockTemplate.getPersonOne());
		long id = person.get().getId();
		Person result = null;
		
		when(personRepository.findById(id)).thenThrow(new IllegalArgumentException("ERRO!"));
		
		try {
			// WHEN
			result = service.getPerson(id);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			throw e;
		}
	}
	
	// ADD - person 
	@Test
	public void shouldAddPersonSaveNewPersonWhenEverythingIsOk() 
			throws RepositoryException, ServiceException {
		// GIVEN
		Optional<Person> newPerson = Optional.of(MockTemplate.getNewPersonOne());
		Optional<Person> person = Optional.of(MockTemplate.getPersonOne());
		Person result = null;
		
		when(personRepository.save(newPerson.get())).thenReturn(person.get());
		
		// WHEN
		result = service.saveNewPerson(newPerson.get());
		
		// THEN
		assertNotNull(result);
		assertTrue(person.get().equals(result));
	}
	
	@Test(expected = ServiceException.class)
	public void shouldAddPersonThroServiceExceptionWhenPersonIsNull() 
			throws RepositoryException, ServiceException {
		// GIVEN
		Person result = null;
		
		try {
			// WHEN
			result = service.saveNewPerson(null);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			throw e;
		}
	}
	
	@Test(expected = RepositoryException.class)
	public void shouldAddPersonThroRepositoryExceptionWhenSavePersonThrowSomeException() 
			throws RepositoryException, ServiceException {
		// GIVEN
		Optional<Person> newPerson = Optional.of(MockTemplate.getNewPersonOne());
		Person result = null;
		
		when(personRepository.save(newPerson.get())).thenThrow(new PersistenceException());
		
		try {
			// WHEN
			result = service.saveNewPerson(newPerson.get());
		} catch (Exception e) {
			// THEN
			assertNull(result);
			throw e;
		}
	}
	
	// UPDATE - person by id 
	
	// DELETE - person by id
	
}
