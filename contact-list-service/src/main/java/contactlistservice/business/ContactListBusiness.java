package contactlistservice.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contactlistservice.business.service.ContactService;
import contactlistservice.business.service.PersonService;
import contactlistservice.entity.Contact;
import contactlistservice.entity.Person;
import contactlistservice.exception.BusinessException;

@Service
public class ContactListBusiness {
	private static final Logger LOG = LoggerFactory.getLogger(ContactListBusiness.class);
	
	private PersonService personService;
	private ContactService contactService;

	@Autowired
	public ContactListBusiness(PersonService personService, ContactService contactService) {
		this.personService = personService;
		this.contactService = contactService;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public Person getPerson(long id) throws BusinessException {
		try {
			return personService.getPerson(id);
		} catch (Exception e) {
			LOG.error("Problemas ao buscar um person por id", e);
			throw new BusinessException(e);
		}
	}

	/**
	 * 
	 * @param personId
	 * @return
	 * @throws BusinessException 
	 */
	public List<Contact> getContacts(long personId) throws BusinessException {
		try {
			return personService.getPerson(personId).getContacts();
		} catch (Exception e) {
			LOG.error("Problemas ao buscar um person por id", e);
			throw new BusinessException(e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public Contact getContact(long id) throws BusinessException {
		try {
			return contactService.getContact(id);
		} catch (Exception e) {
			LOG.error("Problemas ao buscar um Contact por id", e);
			throw new BusinessException(e);
		}
	}

	/**
	 * 
	 * @param newPerson
	 * @return
	 * @throws BusinessException
	 */
	public Person addPerson(Person newPerson) throws BusinessException {
		try {
			return personService.saveUpdate(newPerson);
		} catch(Exception e) {
			LOG.error("Problema ao tentar salvar o novo Person", e);
			throw new BusinessException(e);
		}
	}
	
	/**
	 * 
	 * @param personId
	 * @param newContact
	 * @return
	 * @throws BusinessException 
	 */
	public Contact addConntact(long personId, Contact newContact) throws BusinessException {
		try {
			Person person = personService.getPerson(personId);
			newContact.setPerson(person);
			
			return contactService.saveUpdate(newContact);
		} catch (Exception e) {
			LOG.error("Problema ao tentar salvar novo Contact para um Person", e);
			throw new BusinessException(e);
		}
	}
	
	/**
	 * 
	 * @param person
	 * @return
	 * @throws BusinessException
	 */
	public Person updatePerson(Person person) throws BusinessException {
		try {
			return personService.saveUpdate(person);
		} catch(Exception e) {
			LOG.error("Problema ao tentar atualizar Person", e);
			throw new BusinessException(e);
		}
	}
	
	/**
	 * 
	 * @param contact
	 * @return
	 * @throws BusinessException
	 */
	public Contact updateContact(Contact contact) throws BusinessException {
		try {
			return contactService.saveUpdate(contact);
		} catch(Exception e) {
			LOG.error("Problema ao tentar atualizar Contact", e);
			throw new BusinessException(e);
		}
	}

	/**
	 * 
	 * @param id
	 * @throws BusinessException 
	 */
	public void deletePerson(long id) throws BusinessException {
		try {
			personService.delete(id);
		} catch (Exception e) {
			LOG.error("Problema ao tentar remover Person por id:"+id, e);
			throw new BusinessException(e);
		}
	}
	
	/**
	 * 
	 * @param id
	 * @throws BusinessException 
	 */
	public void deleteContact(long id) throws BusinessException {
		try {
			contactService.delete(id);
		} catch (Exception e) {
			LOG.error("Problema ao tentar remover Contact por id:"+id, e);
			throw new BusinessException(e);
		}
	}
	
}
