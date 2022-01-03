package tacos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tacos.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

	Customer findByFirstName(String name);
	
	Customer findByFirstNameAndLastNameAndMail(String firstName, String lastName, String mail);
	
	List<Customer> findByFirstNameOrMail(String firstName, String mail);
	
	List<Customer> findByFirstNameIn(List<String> firstNames);
	
	List<Customer> findByFirstNameContains(String firstName);
	List<Customer> findByFirstNameContaining(String firstName);
	List<Customer> findByFirstNameIsContaining(String firstName);
	
	List<Customer> findByLastNameLike(String firstName);
}
