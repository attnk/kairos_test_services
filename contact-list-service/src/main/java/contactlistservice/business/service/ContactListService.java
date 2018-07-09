package contactlistservice.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contactlistservice.repository.ContactRepository;
import contactlistservice.repository.PersonRepository;

@Service
public class ContactListService {

	private PersonRepository personRepo;
	private ContactRepository contactRepo;
	
	@Autowired
	public ContactListService(PersonRepository personRepo, ContactRepository contactRepo) {
		this.personRepo = personRepo;
		this.contactRepo = contactRepo;
	}
	
	// GET - person by id
	
	// GET - contact by contact id
	
	// ADD - person 
	
	// ADD - contact to person
	
	// UPDATE - person by id 
	
	// UPDATE - contact by id
	
	// DELETE - person by id
	
	// DELETE - contact by id
	
}
