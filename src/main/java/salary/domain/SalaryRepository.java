package salary.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

	public interface SalaryRepository extends CrudRepository<Salary, Long> {

		List<Salary> findByName(String name);

		List<Salary> findByUsername(String username);
		
}
