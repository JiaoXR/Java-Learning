import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.junit.Test;

/**
 * Created by jaxer on 2019-05-05
 */
public class TomcatTest {
	@Test
	public void test() throws LifecycleException {
		Tomcat tomcat = new Tomcat();

		tomcat.start();
	}
}
