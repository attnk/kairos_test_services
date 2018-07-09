package contactlistservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import contactlistservice.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
