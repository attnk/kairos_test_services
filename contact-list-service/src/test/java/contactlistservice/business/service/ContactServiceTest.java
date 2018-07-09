package contactlistservice.business.service;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static contactlistservice.template.MockTemplate.getContactEmailPersonOne;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import contactlistservice.entity.Contact;
import contactlistservice.exception.RepositoryException;
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
	
	// UPDATE - contact by id
	
	// DELETE - contact by id
	
}
