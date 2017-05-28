package au.com.unico.codingtest.derbyDB.gcd;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GcdNumberRespository extends CrudRepository<GcdNumbers, Long> {
	
	@Query(nativeQuery=true, value = "select sum(gcd) from gcd_numbers")
	Integer getSumOfGcdNumbers();

	@Query(nativeQuery=true, value = "select gcd from gcd_numbers")
	List<Integer> getAllGcdNumbers();

}
