package contactlistservice.template;

import br.com.six2six.fixturefactory.Fixture;
import contactlistservice.entity.Contact;
import contactlistservice.entity.Person;

public class MockTemplate {

	public static Person getPersonOne() {
		return Fixture.from(Person.class).gimme("person-1");
	}

	public static Contact getContactEmailPersonOne() {
		return Fixture.from(Contact.class).gimme("person-1-contact-EMAIL");
	}

	public static Person getNewPersonOne() {
		return Fixture.from(Person.class).gimme("person-1-new");
	}
	
}
