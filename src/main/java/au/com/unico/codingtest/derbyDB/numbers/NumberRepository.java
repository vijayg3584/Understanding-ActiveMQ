package au.com.unico.codingtest.derbyDB.numbers;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NumberRepository extends CrudRepository<NumberQueue, Integer> {
	
	@Query(nativeQuery=true,value="select number from number_queue order by inserted_date")
	List<Integer> getNumberInOrderOfInsert();

}
