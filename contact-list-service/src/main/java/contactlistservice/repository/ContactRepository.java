package contactlistservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import contactlistservice.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

}
