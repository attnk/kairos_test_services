package contactlistservice.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contactlistservice.entity.Contact;
import contactlistservice.exception.RepositoryException;
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
	
	// ADD - contact to person
	
	// UPDATE - contact by id
	
	// DELETE - contact by id
	
}
