package contactlistservice.business.service;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static contactlistservice.template.MockTemplate.getContactEmailPersonOne;
import static contactlistservice.template.MockTemplate.getNewContactEmailPersonOne;
import static contactlistservice.template.MockTemplate.getUpdateConnntactEmailPersonOne;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import contactlistservice.entity.Contact;
import contactlistservice.exception.RepositoryException;
import contactlistservice.exception.ServiceException;
import contactlistservice.repository.ContactRepository;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

	@Mock
	private ContactRepository contactRepository;
	
	@InjectMocks
	private ContactService service;
	
	@Before
	public void init() {
		loadTemplates("contactlistservice.template.entity");
	}
	
	// GET - contact by contact id
	@Test
	public void shouldGetContactByContactIdReturnContactWhenEverythingIsOk() 
			throws RepositoryException {
		// GIVEN
		Optional<Contact> contact = Optional.of(getContactEmailPersonOne());
		long id = contact.get().getId();
		Contact result = null;
		
		when(contactRepository.findById(id)).thenReturn(contact);
		
		// WHEN
		result = service.getContact(id);
		
		// THEN
		assertNotNull(result);
		assertTrue(contact.get().equals(result));
	}
	
	@Test(expected = RepositoryException.class)
	public void shouldGetContactByContactIdThrowRepositoryExceptionWhenContactRepositoryThrowSomeException() 
			throws RepositoryException {
		// GIVEN
		Optional<Contact> contact = Optional.of(getContactEmailPersonOne());
		long id = contact.get().getId();
		Contact result = null;
		
		when(contactRepository.findById(id)).thenThrow(new IllegalArgumentException("ERRO!!"));
		
		try {
			// WHEN
			result = service.getContact(id);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			throw e;
		}
	}
	
	// ADD - contact
	@Test
	public void shouldAddContactSaveNewConntactToAPersonWhenEverythingIsOk() 
			throws RepositoryException, ServiceException {
		// GIVEN
		Optional<Contact> newContact = Optional.of(getNewContactEmailPersonOne());
		Optional<Contact> contact = Optional.of(getContactEmailPersonOne());
		Contact result = null;
		
		when(contactRepository.save(newContact.get())).thenReturn(contact.get());
		
		// WHEN
		result = service.saveUpdate(newContact.get());
		
		// THEN
		assertNotNull(result);
		assertTrue(contact.get().equals(result));
	}
	
	@Test(expected = ServiceException.class)
	public void shouldAddContactThrowServiceExceptionWhenNewContactIsNull() 
			throws RepositoryException, ServiceException {
		// GIVEN
		Contact result = null;
		
		try {
			// WHEN
			result = service.saveUpdate(null);
		} catch (Exception e) {
			// THEN
			assertNull(result);
			throw e;
		}
	}
	
	@Test(expected = RepositoryException.class)
	public void shouldAddContactThrowRepositoryExceptionWhenRepositoryThrowAnyException() 
			throws RepositoryException, ServiceException {
		// GIVEN
		Optional<Contact> newContact = Optional.of(getNewContactEmailPersonOne());
		Contact result = null;
		
		when(contactRepository.save(newContact.get())).thenThrow(new PersistenceException());
		
		try {
			// WHEN
			result = service.saveUpdate(newContact.get());
		} catch (Exception e) {
			// THEN
			assertNull(result);
			throw e;
		}
	}
	
	// UPDATE - contact
	@Test
	public void shouldUpdateContactUpdateConntactWhenEverythingIsOk() 
			throws RepositoryException, ServiceException {
		// GIVEN
		Contact result = null;
		Optional<Contact> updateContact = Optional.of(getUpdateConnntactEmailPersonOne());
		
		when(contactRepository.save(updateContact.get())).thenReturn(updateContact.get());
		
		// WHEN
		result = service.saveUpdate(updateContact.get());
		
		// THEN
		assertNotNull(result);
		assertTrue(updateContact.get().equals(result));
	}
	
	// DELETE - contact by id
	@Test
	public void shouldDeleteContactByIdWhenEverythinngIsOk() 
			throws RepositoryException {
		// GIVEN
		long id = getContactEmailPersonOne().getId();
		
		doNothing().when(contactRepository).deleteById(id);
		
		// THEN
		service.delete(id);
		
		// WHEN
		verify(contactRepository, times(1)).deleteById(id);
		verifyNoMoreInteractions(contactRepository);
	}
	
	@Test(expected = RepositoryException.class)
	public void shouldDeleteContactByIdThrowRepositpryExceptionnWhenRepositoryThrowAnyExcepton() 
			throws RepositoryException {
		// GIVEN
		long id = getContactEmailPersonOne().getId();
		
		doThrow(new IllegalArgumentException("ERRO!")).when(contactRepository).deleteById(id);
		
		try {
			// THEN
			service.delete(id);
		} catch (Exception e) {
			// WHEN
			verify(contactRepository, times(1)).deleteById(id);
			verifyNoMoreInteractions(contactRepository);
			throw e;
		}
		
	}
}
