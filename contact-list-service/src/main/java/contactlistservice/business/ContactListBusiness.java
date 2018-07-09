package contactlistservice.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contactlistservice.business.service.ContactListService;

@Service
public class ContactListBusiness {
	
	private ContactListService contactListService;

	@Autowired
	public ContactListBusiness(ContactListService contactListService) {
		this.contactListService = contactListService;
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
