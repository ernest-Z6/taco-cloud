package tacos.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tacos.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

	Customer findByFirstName(String name);
	
	Customer findByFirstNameAndLastNameAndMail(String firstName, String lastName, String mail);
	
	@Query("From Customer where firstName = :firstName and lastName = :lastName")
	Customer getByFirstNameAndLastName(String firstName, String lastName);
	
	@Query("From Customer where firstName = :first_name and lastName = :lastName")
	Customer getByFirstNameAndLastName2(@Param("first_name") String firstName, String lastName);
	
	@Query("From Customer where firstName = ?1 and lastName = ?2")
	Customer getByFirstNameAndLastName3(String firstName, String lastName);
	
	@Modifying
	@Transactional
	@Query("Update Customer set mail = :mail where firstName = :firstName and lastName = :lastName")
	Integer updateMail(String mail, String firstName, String lastName);
	
	List<Customer> findByFirstNameOrMail(String firstName, String mail);
	
	List<Customer> findByFirstNameIn(List<String> firstNames);
	
	List<Customer> findByFirstNameContains(String firstName);
	List<Customer> findByFirstNameContaining(String firstName);
	List<Customer> findByFirstNameIsContaining(String firstName);
	
	List<Customer> findByLastNameLike(String firstName);
	
	List<Customer> findByFirstNameStartsWith(String prefex);
	List<Customer> findByLastNameEndsWith(String suffix);
}
