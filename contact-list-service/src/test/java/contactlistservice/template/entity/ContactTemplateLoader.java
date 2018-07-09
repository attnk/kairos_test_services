package contactlistservice.template.entity;

import static java.time.LocalDateTime.now;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import contactlistservice.entity.Contact;
import contactlistservice.entity.Person;
import contactlistservice.enums.ContactType;

public class ContactTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Contact.class).addTemplate("default", new Rule() {{
			add("id", 0L);
			add("type", null);
			add("value", "");
			add("createdAt", now());
			add("modifiedAt", now());
			add("person", new Person());
		}});

		Fixture.of(Contact.class).addTemplate("person-1-contact-EMAIL", new Rule() {{
			add("id", 1L);
			add("type", ContactType.EMAIL);
			add("value", "testes@testes.com");
			add("createdAt", now());
			add("modifiedAt", now());
			add("person", one(Person.class, "person-1-repo"));
		}});
		
		Fixture.of(Contact.class).addTemplate("person-1-contact-PHONE", new Rule() {{
			add("id", 2L);
			add("type", ContactType.PHONE);
			add("value", "011 5689-5314");
			add("createdAt", now());
			add("modifiedAt", now());
			add("person", one(Person.class, "person-1-repo"));
		}});
		
		Fixture.of(Contact.class).addTemplate("person-1-contact-WHATSAPP", new Rule() {{
			add("id", 3L);
			add("type", ContactType.WHATS_APP);
			add("value", "011 99631-4627");
			add("createdAt", now());
			add("modifiedAt", now());
			add("person", one(Person.class, "person-1-repo"));
		}});
	}

}
