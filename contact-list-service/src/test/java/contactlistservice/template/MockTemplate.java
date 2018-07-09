package contactlistservice.template;

import br.com.six2six.fixturefactory.Fixture;
import contactlistservice.entity.Contact;
import contactlistservice.entity.Person;

public class MockTemplate {

	// GET
	public static Person getPersonOne() {
		return Fixture.from(Person.class).gimme("person-1");
	}

	public static Contact getContactEmailPersonOne() {
		return Fixture.from(Contact.class).gimme("person-1-contact-EMAIL");
	}

	// ADD
	public static Person getNewPersonOne() {
		return Fixture.from(Person.class).gimme("person-1-new");
	}
	
	public static Contact getNewContactEmailPersonOne() {
		return Fixture.from(Contact.class).gimme("person-1-NEW-contact-EMAIL");
	}
	
	// UPDATE
	public static Person getUpdatePersonOne() {
		return Fixture.from(Person.class).gimme("person-1-update");
	}
	
	public static Contact getUpdateConnntactEmailPersonOne() {
		return Fixture.from(Contact.class).gimme("person-1-UPDATE-contact-EMAIL");
	}
}
