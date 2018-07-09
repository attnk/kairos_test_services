package contactlistservice.business.service;

import static org.springframework.util.Assert.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contactlistservice.entity.Contact;
import contactlistservice.exception.RepositoryException;
import contactlistservice.exception.ServiceException;
import contactlistservice.repository.ContactRepository;

@Service
public class ContactService {

	private ContactRepository contactRepo;
	
	@Autowired
	public ContactService(ContactRepository contactRepo) {
		this.contactRepo = contactRepo;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RepositoryException
	 */
	public Contact getContact(long id) throws RepositoryException {
		try {
			return contactRepo.findById(id).get();
		} catch (Exception e) {
			throw new RepositoryException(
					"Problemas ao buscar um Contact por id: "+ id,
					e);
		}
	}

	/**
	 * 
	 * @param contact
	 * @return
	 * @throws RepositoryException
	 * @throws ServiceException 
	 */
	public Contact saveUpdate(Contact contact) 
			throws RepositoryException, ServiceException {
		try {
			notNull(contact, "O Contato não pode ser nulo!");
			return contactRepo.save(contact);
		} catch (IllegalArgumentException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new RepositoryException(
					"Problemas ao salvar um novo Contato",
					e);
		}
	}

	/**
	 * 
	 * @param id
	 * @throws RepositoryException
	 */
	public void delete(long id) throws RepositoryException {
		try {
			contactRepo.deleteById(id);
		} catch (Exception e) {
			throw new RepositoryException(
					"Problemas ao remover um Contato de id:"+id,
					e);
		}
	}
	
}
