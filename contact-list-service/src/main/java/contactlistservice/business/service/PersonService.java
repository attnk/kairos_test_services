package contactlistservice.business.service;

import static org.springframework.util.Assert.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contactlistservice.entity.Person;
import contactlistservice.exception.RepositoryException;
import contactlistservice.exception.ServiceException;
import contactlistservice.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepo;
	
	@Autowired
	public PersonService(PersonRepository personRepo) {
		this.personRepo = personRepo;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RepositoryException
	 */
	public Person getPerson(long id) throws RepositoryException {
		try {
			return personRepo.findById(id).get();
		} catch (Exception e) {
			throw new RepositoryException(
					"Problemas ao buscar um Person por id: "+ id,
					e);
		}
	}
	
	/**
	 * 
	 * @param person
	 * @return
	 * @throws RepositoryException
	 * @throws ServiceException
	 */
	public Person saveNewPerson(Person person) 
			throws RepositoryException, ServiceException {
		try {
			notNull(person, "Person must not to be null!");
			return personRepo.save(person);
		} catch (IllegalArgumentException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			throw new RepositoryException("", e);
		}
	}
	
	// UPDATE - person by id 
	
	// DELETE - person by id
	
}
