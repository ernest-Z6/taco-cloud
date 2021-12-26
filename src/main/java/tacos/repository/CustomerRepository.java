package tacos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tacos.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

	Customer findByName(String name);
	
	Customer findByNameAndMail(String name, String mail);
	
	List<Customer> findByNameOrMail(String name, String mail);
	
	List<Customer> findByNameIn(List<String> names);
}
