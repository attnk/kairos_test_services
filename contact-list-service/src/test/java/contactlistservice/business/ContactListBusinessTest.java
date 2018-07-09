package contactlistservice.business;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static contactlistservice.template.MockTemplate.getContactEmailPersonOne;
import static contactlistservice.template.MockTemplate.getNewContactEmailPersonOne;
import static contactlistservice.template.MockTemplate.getNewPersonOne;
import static contactlistservice.template.MockTemplate.getPersonOne;
import static contactlistservice.template.MockTemplate.getUpdatePersonOne;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import contactlistservice.business.service.ContactService;
import contactlistservice.business.service.PersonService;
import contactlistservice.entity.Contact;
import contactlistservice.entity.Person;
import contactlistservice.exception.BusinessException;
import contactlistservice.exception.RepositoryException;
import contactlistservice.exception.ServiceException;
import contactlistservice.template.MockTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ContactListBusinessTest {

	@Mock
	private PersonService personService;
	
	@Mock
	private ContactService contactService;
	
	@InjectMocks
	private ContactListBusiness business;
	
	@Before
	public void init() {
		loadTemplates("contactlistservice.template.entity");
	}
	
	// GET - person by id
	@Test
	public void shouldGetPersonByIdReturnPersonWhenEverythingIsOk() 
			throws RepositoryException, BusinessException {
		// GIVEN
		Person person = MockTemplate.getPersonOne();
		Person result = null;
		long id = person.getId();
		
		when(personService.getPerson(id)).thenReturn(person);
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		// WHEN
		result = business.getPerson(id);
		
		// THEN
		assertNotNull(result);
		assertTrue(person.equals(result));
		
		order.verify(personService, times(1)).getPerson(id);
		order.verifyNoMoreInteractions();
	}
	
	@Test(expected = BusinessException.class)
	public void shouldGetPersonByIdThrowBusinessExceptionWhenPersonServiceThrowAnyException() 
			throws RepositoryException, BusinessException {
		// GIVEN
		Person person = MockTemplate.getPersonOne();
		Person result = null;
		long id = person.getId();
		
		when(personService.getPerson(id)).thenThrow(
				new RepositoryException("ERRO!", new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.getPerson(id);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			
			order.verify(personService, times(1)).getPerson(id);
			order.verifyNoMoreInteractions();
			throw e;
			
		}
		
	}
	
	// GET - contacts by person id
	@Test
	public void shouldGetContactsByPersonIdReturnListOfCotactsFromPersonWhenEverythigIsOk() 
			throws RepositoryException, BusinessException {
		// GIVEN
		Person person = getPersonOne();
		long personId = person.getId();
		List<Contact> contacts = person.getContacts();
		List<Contact> result = new ArrayList<>();
		
		when(personService.getPerson(personId)).thenReturn(person);
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		// WHEN
		result = business.getContacts(personId);
		
		// THEN
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertTrue(contacts.containsAll(result));
		
		order.verify(personService, times(1)).getPerson(personId);
		order.verifyNoMoreInteractions();
	}
	
	@Test(expected = BusinessException.class)
	public void shouldGetContactsByPersonIdThrowBusinessExceptionWhenPersonServiceThorwsAnyException() 
			throws RepositoryException, BusinessException {
		// GIVEN
		Person person = getPersonOne();
		long personId = person.getId();
		List<Contact> result = new ArrayList<>();
		
		when(personService.getPerson(personId)).thenThrow(
				new RepositoryException("ERRO!", new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.getContacts(personId);
		} catch (Exception e) {
			// THEN
			assertNotNull(result);
			assertTrue(result.isEmpty());
			
			order.verify(personService, times(1)).getPerson(personId);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
		
	}
	
	// GET - contact by contact id
	@Test
	public void shouldGetContactByIdReturnContactWhenEverythingIsOk() 
			throws RepositoryException, BusinessException {
		// GIVEN
		Contact contact = getContactEmailPersonOne();
		long id = contact.getId();
		
		Contact result = null;
		
		when(contactService.getContact(id)).thenReturn(contact);
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		// WHEN
		result = business.getContact(id);
		
		// THEN
		assertNotNull(result);
		assertTrue(contact.equals(result));
		
		order.verify(contactService, times(1)).getContact(id);
		order.verifyNoMoreInteractions();
	}
	
	@Test(expected = BusinessException.class)
	public void shouldGetContactByIdThrowBusinessExceptionWhenContactServiceThrowsAnyException() 
			throws RepositoryException, BusinessException {
		// GIVEN
		Contact contact = getContactEmailPersonOne();
		long id = contact.getId();
		
		Contact result = null;
		
		when(contactService.getContact(id)).thenThrow(
				new RepositoryException("ERRO!", new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.getContact(id);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			
			order.verify(contactService, times(1)).getContact(id);
			order.verifyNoMoreInteractions();
			throw e;
			
		}
		
	}
	
	// ADD - person  
	@Test
	public void shouldAddPersonSaveNewPersonWhenEverythingIsOk() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Person person = getPersonOne();
		Person newPerson = getNewPersonOne();
		Person result = null;
		
		when(personService.saveUpdate(newPerson)).thenReturn(person);
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		// WHEN
		result = business.addPerson(newPerson);
		
		// THEN
		assertNotNull(result);
		assertTrue(person.equals(result));
		
		order.verify(personService, times(1)).saveUpdate(newPerson);
		order.verifyNoMoreInteractions();
	}
	
	@Test(expected = BusinessException.class)
	public void shouldAddPersonThrowBusinessExceptionWhenPersonServiceThrowRepositoryException() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Person newPerson = getNewPersonOne();
		Person result = null;
		
		when(personService.saveUpdate(newPerson)).thenThrow(
				new RepositoryException("ERRO!", new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.addPerson(newPerson);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			
			order.verify(personService, times(1)).saveUpdate(newPerson);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
	}
	
	@Test(expected = BusinessException.class)
	public void shouldAddPersonThrowBusinessExceptionWhenPersonServiceThrowServiceException() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Person newPerson = getNewPersonOne();
		Person result = null;
		
		when(personService.saveUpdate(newPerson)).thenThrow(
				new ServiceException(new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.addPerson(newPerson);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			
			order.verify(personService, times(1)).saveUpdate(newPerson);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
	}
	
	// ADD - contact to person Id
	@Test
	public void shouldAddContactToPersonByIdSaveNewContactWhenEveryThingIsOk() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Contact contact = getContactEmailPersonOne();
		Contact newContact = getNewContactEmailPersonOne();
		long personId = getPersonOne().getId();
		Contact result = null;
		
		when(personService.getPerson(personId)).thenReturn(getPersonOne());
		when(contactService.saveUpdate(newContact)).thenReturn(contact);
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		// WHEN
		result = business.addConntact(personId, newContact);
		
		// THEN
		assertNotNull(result);
		assertTrue(contact.equals(result));
		
		order.verify(personService, times(1)).getPerson(personId);
		order.verify(contactService, times(1)).saveUpdate(newContact);
		order.verifyNoMoreInteractions();
	}
	
	@Test(expected = BusinessException.class)
	public void shouldAddContactToPersonByIdThrowBusinessExceptionWhenGetPersoThrowRepositoryException() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Contact newContact = getNewContactEmailPersonOne();
		long personId = getPersonOne().getId();
		Contact result = null;
		
		when(personService.getPerson(personId)).thenThrow(
				new RepositoryException("ERRO!", new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.addConntact(personId, newContact);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			
			order.verify(personService, times(1)).getPerson(personId);
			order.verify(contactService, never()).saveUpdate(newContact);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
	}
	
	@Test(expected = BusinessException.class)
	public void shouldAddContactToPersonByIdThrowBusinessExceptionWhenSaveUpdateContactThrowRepositoryException() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Contact newContact = getNewContactEmailPersonOne();
		long personId = getPersonOne().getId();
		Contact result = null;
		
		when(personService.getPerson(personId)).thenReturn(getPersonOne());
		when(contactService.saveUpdate(newContact)).thenThrow(
				new RepositoryException("ERRO!", new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.addConntact(personId, newContact);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			
			order.verify(personService, times(1)).getPerson(personId);
			order.verify(contactService, times(1)).saveUpdate(newContact);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
	}
	
	@Test(expected = BusinessException.class)
	public void shouldAddContactToPersonByIdThrowBusinessExceptionWhenSaveUpdateContactThrowServiceException() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Contact newContact = getNewContactEmailPersonOne();
		long personId = getPersonOne().getId();
		Contact result = null;
		
		when(personService.getPerson(personId)).thenReturn(getPersonOne());
		when(contactService.saveUpdate(newContact)).thenThrow(
				new ServiceException(new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.addConntact(personId, newContact);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			
			order.verify(personService, times(1)).getPerson(personId);
			order.verify(contactService, times(1)).saveUpdate(newContact);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
	}
	
	// UPDATE - person by id 
	@Test
	public void shouldUpdatePersonSaveNewPersonWhenEverythingIsOk() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Person person = getUpdatePersonOne();
		Person result = null;
		
		when(personService.saveUpdate(person)).thenReturn(person);
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		// WHEN
		result = business.addPerson(person);
		
		// THEN
		assertNotNull(result);
		assertTrue(person.equals(result));
		
		order.verify(personService, times(1)).saveUpdate(person);
		order.verifyNoMoreInteractions();
	}
	
	@Test(expected = BusinessException.class)
	public void shouldUpdatePersonThrowBusinessExceptionWhenPersonServiceThrowRepositoryException() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Person person = getUpdatePersonOne();
		Person result = null;
		
		when(personService.saveUpdate(person)).thenThrow(
				new RepositoryException("ERRO!", new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.addPerson(person);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			
			order.verify(personService, times(1)).saveUpdate(person);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
	}
	
	@Test(expected = BusinessException.class)
	public void shouldUpdatePersonThrowBusinessExceptionWhenPersonServiceThrowServiceException() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Person person = getUpdatePersonOne();
		Person result = null;
		
		when(personService.saveUpdate(person)).thenThrow(
				new ServiceException(new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.addPerson(person);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			
			order.verify(personService, times(1)).saveUpdate(person);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
	}
	
	// UPDATE - contact by id
	@Test
	public void shouldUpdateConntactUpdateDataWhenEverythinngIsOk() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Contact contact = MockTemplate.getUpdateConnntactEmailPersonOne();
		Contact result = null;
		
		when(contactService.saveUpdate(contact)).thenReturn(contact);
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		// WHEN
		result = business.updateContact(contact);
		
		// THEN
		assertNotNull(result);
		assertTrue(contact.equals(result));
		
		order.verify(contactService, times(1)).saveUpdate(contact);
		order.verifyNoMoreInteractions();
	}
	
	@Test(expected = BusinessException.class)
	public void shouldUpdateConntactThrowBusinessExceptionWhenContactServiceThrowRepositoryException() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Contact contact = MockTemplate.getUpdateConnntactEmailPersonOne();
		Contact result = null;
		
		when(contactService.saveUpdate(contact)).thenThrow(
				new RepositoryException("ERRO!", new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.updateContact(contact);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			
			order.verify(contactService, times(1)).saveUpdate(contact);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
	}
	
	@Test(expected = BusinessException.class)
	public void shouldUpdateConntactThrowBusinessExceptionWhenContactServiceThrowServiceException() 
			throws RepositoryException, ServiceException, BusinessException {
		// GIVEN
		Contact contact = MockTemplate.getUpdateConnntactEmailPersonOne();
		Contact result = null;
		
		when(contactService.saveUpdate(contact)).thenThrow(
				new ServiceException(new IllegalArgumentException("ERRO!!")));
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			result = business.updateContact(contact);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			
			order.verify(contactService, times(1)).saveUpdate(contact);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
	}
	
	// DELETE - person by id
	@Test
	public void shouldDeletePersonDeleteDataWhenEverythingIsOk() 
			throws RepositoryException, BusinessException {
		// GIVEN
		long id = getPersonOne().getId();
		
		doNothing().when(personService).delete(id);
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		// WHEN
		business.deletePerson(id);
		
		// THEN
		order.verify(personService, times(1)).delete(id);
		order.verifyNoMoreInteractions();
	}
	
	@Test(expected = BusinessException.class)
	public void shouldDeletePersonThrowBusinessExceptionWhenPersonServiceThrowAnyException() 
			throws RepositoryException, BusinessException {
		// GIVEN
		long id = getPersonOne().getId();
		
		doThrow(new RepositoryException("ERRO!", new IllegalArgumentException("ERRO!!")))
		.when(personService).delete(id);
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			business.deletePerson(id);
		} catch (Exception e) {
			// THEN
			order.verify(personService, times(1)).delete(id);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
	}
	
	// DELETE - contact by id
	@Test
	public void shouldDeleteContactDeleteDataWhenEverythingIsOk() 
			throws RepositoryException, BusinessException {
		// GIVEN
		long id = getContactEmailPersonOne().getId();
		
		doNothing().when(contactService).delete(id);
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		// WHEN
		business.deleteContact(id);
		
		// THEN
		order.verify(contactService, times(1)).delete(id);
		order.verifyNoMoreInteractions();
	}
	
	@Test(expected = BusinessException.class)
	public void shouldDeleteContactThrowBusinessExceptionWhenContactServiceThrowAnyException() 
			throws RepositoryException, BusinessException {
		// GIVEN
		long id = getContactEmailPersonOne().getId();
		
		doThrow(new RepositoryException("ERRO!", new IllegalArgumentException("ERRO!!")))
		.when(contactService).delete(id);
		
		InOrder order = Mockito.inOrder(personService, contactService);
		
		try {
			// WHEN
			business.deleteContact(id);
		} catch (Exception e) {
			// THEN
			order.verify(contactService, times(1)).delete(id);
			order.verifyNoMoreInteractions();
			
			throw e;
		}
	}
}
