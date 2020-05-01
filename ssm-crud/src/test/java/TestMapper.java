import com.jaxer.example.bean.Employee;
import com.jaxer.example.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * Spring 单元测试
 *
 * @author jaxer
 * date 26/07/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMapper {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private SqlSession sqlSession;

	@Test
	public void test() {
		Employee employee = employeeMapper.selectByPrimaryKey(3);
		System.out.println(employee);
	}

	@Test
	public void batchInsert() {
		EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 10; i++) {
			Employee employee = new Employee();
			employee.setName("赵四" + i);
			employee.setAge(50 - i * 2);
			employee.setCreateTime(LocalDateTime.now());
			employeeMapper.insert(employee);
		}
	}
}
