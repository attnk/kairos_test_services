package contactlistservice.template.entity;

import static java.time.LocalDateTime.now;

import java.util.ArrayList;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import contactlistservice.entity.Contact;
import contactlistservice.entity.Person;

public class PersonTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Person.class).addTemplate("default", new Rule() {{
			add("id", 0L);
			add("name", "");
			add("createdAt", now());
			add("modifiedAt", now());
			add("contacts",  new ArrayList<>());
		}});

		Fixture.of(Person.class).addTemplate("person-1", new Rule() {{
			add("id", 1L);
			add("name", "Teste");
			add("createdAt", now());
			add("modifiedAt", now());
			add("contacts", has(3).of(Contact.class, 
					"person-1-contact-EMAIL", 
					"person-1-contact-PHONE", 
					"person-1-contact-WHATSAPP"));
		}});
		
		Fixture.of(Person.class).addTemplate("person-1-repo", new Rule() {{
			add("id", 1L);
			add("name", "Teste");
		}});
		
		Fixture.of(Person.class).addTemplate("person-1-new", new Rule() {{
			add("name", "Teste");
			add("createdAt", now());
			add("modifiedAt", now());
			add("contacts", has(3).of(Contact.class, 
					"person-1-contact-EMAIL", 
					"person-1-contact-PHONE", 
					"person-1-contact-WHATSAPP"));
		}});
		
		Fixture.of(Person.class).addTemplate("person-1-update", new Rule() {{
			add("id", 1L);
			add("name", "Teste123");
			add("createdAt", now());
			add("modifiedAt", now());
			add("contacts", has(3).of(Contact.class, 
					"person-1-contact-EMAIL", 
					"person-1-contact-PHONE", 
					"person-1-contact-WHATSAPP"));
		}});
	}
}
