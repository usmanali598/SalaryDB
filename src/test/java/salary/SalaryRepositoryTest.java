package salary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import salary.domain.Salary;
import salary.domain.SalaryRepository;
import salary.domain.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class SalaryRepositoryTest {

    @Autowired
    private SalaryRepository repository;
    
    @Autowired
    private UserRepository repository2;
    
    @Test
    public void findByNameShouldReturnSalary() {
        List<Salary> salaries = repository.findByName("Ruman");
        
        assertThat(salaries).hasSize(1);
        assertThat(salaries.get(0).getUsername()).isEqualTo("ruman123");
    }
    
    @Test
    public void addNewSalary() {
    	Salary salary = new Salary("Luigi", "luigi123", "a156899" , "frankfurt" , 123658 , 750);
    	repository.save(salary);
    	assertThat(salary.getId()).isNotNull();
    }    
    
    @Test
	public void deleteSalary() {
		List<Salary> salaries = repository.findByName("ryabov");
		repository.delete(salaries.get(0).getId());
		
		assertFalse(repository.exists(salaries.get(0).getId()));
	}

    
    }
   