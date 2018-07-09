package contactlistservice.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contactlistservice.business.service.ContactService;
import contactlistservice.business.service.PersonService;

@Service
public class ContactListBusiness {
	private static final Logger LOG = LoggerFactory.getLogger(ContactListBusiness.class);
	
	private PersonService presonService;
	private ContactService contactService;

	@Autowired
	public ContactListBusiness(PersonService personService, ContactService contactService) {
		this.presonService = personService;
		this.contactService = contactService;
	}
	
	// GET - person by id
	
	// GET - contacts by person id
	
	// GET - contact by contact id
	
	// ADD - person 
	
	// ADD - contact to person
	
	// UPDATE - person by id 
	
	// UPDATE - contact by id
	
	// DELETE - person by id
	
	// DELETE - contact by id
	
	
}
