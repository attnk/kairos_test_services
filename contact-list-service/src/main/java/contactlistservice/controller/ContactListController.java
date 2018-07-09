package contactlistservice.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import contactlistservice.business.ContactListBusiness;
import contactlistservice.entity.Contact;
import contactlistservice.entity.Person;
import contactlistservice.exception.BusinessException;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_UTF8_VALUE)
public class ContactListController {

	@Autowired
	private ContactListBusiness business;
	
	@ResponseBody
	@RequestMapping(value = "persons/{personId}", method = GET)
	public Person getPerson(@PathVariable(value = "personId")long id) 
			throws BusinessException {
		return business.getPerson(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "persons/{personId}/contacts", method = GET)
	public List<Contact> getContacts(@PathVariable(value = "personId")long id) 
			throws BusinessException {
		return business.getContacts(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "contacts/{contactId}", method = GET)
	public Contact getContact(@PathVariable(value = "contactId")long id) 
			throws BusinessException {
		return business.getContact(id);
	}
	
	@ResponseBody
	@ResponseStatus(CREATED)
	@RequestMapping(value = "persons/", method = POST)
	public Person addPerson(@RequestBody Person person) 
			throws BusinessException {
		return business.addPerson(person);
	}
	
	@ResponseBody
	@ResponseStatus(CREATED)
	@RequestMapping(value = "persons/{personId}/contacts", method = POST)
	public Contact addPerson(
			@PathVariable(value = "personId")long personId, 
			@RequestBody Contact contact) 
			throws BusinessException {
		return business.addConntact(personId, contact);
	}
	
	@ResponseBody
	@RequestMapping(value = "persons/", method = PUT)
	public Person updatePerson(@RequestBody Person person) 
			throws BusinessException {
		return business.updatePerson(person);
	}
	
	@ResponseBody
	@RequestMapping(value = "contacts/", method = PUT)
	public Contact updatePerson(@RequestBody Contact contact) 
			throws BusinessException {
		return business.updateContact(contact);
	}
	
	@ResponseStatus(OK)
	@RequestMapping(value = "persons/{personId}", method = DELETE)
	public void deletePerson(@PathVariable(value = "personId") long id) 
			throws BusinessException {
		business.deletePerson(id);
	}
	
	@ResponseStatus(OK)
	@RequestMapping(value = "contacts/{conntactId}", method = DELETE)
	public void deleteContact(@PathVariable(value = "conntactId") long id) 
			throws BusinessException {
		business.deleteContact(id);
	}
}
