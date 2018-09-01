import com.jaxer.example.bean.Employee;
import com.jaxer.example.dao.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void test() {
        Employee employee = employeeMapper.getById(1);
        System.out.println(employee);
    }
}
