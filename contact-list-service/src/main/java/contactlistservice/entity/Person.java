package contactlistservice.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

	@Id
	private long id;
	
	
	private List<Contact> contacts;
	
}
