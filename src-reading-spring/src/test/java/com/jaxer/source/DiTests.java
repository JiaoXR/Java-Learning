package com.jaxer.source;

import com.jaxer.doc.di.ExampleBean;
import com.jaxer.doc.di.thing.ThingTwo;
import org.junit.Test;

/**
 * Created by jaxer on 2018/12/2
 */
public class DiTests extends BaseTests {
	@Test
	public void testDI() {
		ExampleBean exampleBean = context.getBean("exampleBean", ExampleBean.class);
		System.out.println(exampleBean);

//		ComplexObject complexObject = context.getBean("complexObject", ComplexObject.class);
//		System.out.println(complexObject);

		ThingTwo thingTwo = context.getBean("thingTwo", ThingTwo.class);
		System.out.println(thingTwo);
	}
}
