package contactlistservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contact {
	
	@Id
	private long Id;
	
	private String phone;
	
	private String email;
	
	private String whatsApp;
}
