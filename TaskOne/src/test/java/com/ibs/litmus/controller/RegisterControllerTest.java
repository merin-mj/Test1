import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ibs.litmus.model.Person;
import com.ibs.litmus.myexceptions.PasswordException;
import com.ibs.litmus.repository.PersonRepo;

@ExtendWith(MockitoExtension.class)//annotn instd of openMocks
class RegisterControllerTest {
	@InjectMocks
	RegisterController rc;
	@Mock
    	PersonRepo repo;
	Person p ;
	@BeforeEach
	public void setup() {
		//MockitoAnnotations.initMocks(this);//jupiter 2-deprecated in jupiter 3 use openMocks or annotn
		//MockitoAnnotations.openMocks(this);
		p = new Person("testUserName", 50, "testName", "1971", "male", "password");
		System.out.println("Inside setup");
	}
	@AfterEach
	public void cleanup() {
		System.out.println("Inside cleanup");
	}
	@Test
	@DisplayName("save to db-pw lenth>6")
	void passwordTest1() throws PasswordException {
		p.setPassword("password");
		doReturn(p).when(repo).save(any(Person.class));
		rc.details(p);
		//ModelAndView mv = rc.details(p);
		assertEquals(8, p.getPassword().length(), "password length is greater than 6::criteria satisfied");
	}
	@Test
	@DisplayName("save to db-pw length=6")
	void passwordTest2() throws PasswordException {
		p.setPassword("passwo");
		doReturn(p).when(repo).save(any(Person.class));
		rc.details(p);
		assertEquals(6, p.getPassword().length(), "password length is 6::criteria satisfied");
	}
	@Test
	@DisplayName("dnt save to db,throw exceptn-pw length<6")
	void passwordTest3() {
		p.setPassword("pass");
		assertThrows(Exception.class, () -> rc.details(p),
				"password length is less than 6::criteria violation,throws exception");
	}
}
/*
class RegisterControllerTest {
	Person person=new Person("testUserName", 50, "testName", "1971", "male", "password");
	@InjectMocks
	RegisterController controller;
	
	@Mock
	PersonRepo repo;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@DisplayName("save to db-pw lenth>6")
	public void shouldSaveValidPassword1() throws PasswordException {
		//ModelAndView mv = new ModelAndView();
		doReturn(person).when(repo).save(any(Person.class));
		controller.details(person);
		assertEquals(8, person.getPassword().length(), "password length is greater than 6::criteria satisfied");
		verify(repo,times(1)).save(any(Person.class));
	}
}
*/