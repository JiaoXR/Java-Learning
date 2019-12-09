package com.jaxer.example;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * 代码千万行，注释第一行。
 * 测试XPath
 * <p>
 * Created by jaxer on 2019-07-18
 */
public class XPathTest {
	@Test
	public void read() throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setValidating(true);
		documentBuilderFactory.setNamespaceAware(false);
		documentBuilderFactory.setIgnoringComments(true);
		documentBuilderFactory.setIgnoringElementContentWhitespace(false);
		documentBuilderFactory.setCoalescing(false);
		documentBuilderFactory.setExpandEntityReferences(true);
		// 创建DocumentBuilder
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		documentBuilder.setErrorHandler(new ErrorHandler() {
			@Override
			public void warning(SAXParseException exception) throws SAXException {

			}

			@Override
			public void error(SAXParseException exception) throws SAXException {

			}

			@Override
			public void fatalError(SAXParseException exception) throws SAXException {

			}
		});
		// 将文档加载到一个 Document 对象中
		Document document = documentBuilder.parse("/Users/jaxer/GitHub-JiaoXR/Framework/hello-mybatis/src/main/resource/nodelet_test.xml");
		// 创建 XPathFactory
		XPathFactory xPathFactory = XPathFactory.newInstance();
		// 创建 XPath
		XPath xPath = xPathFactory.newXPath();
		XPathExpression expression = xPath.compile("/employee/birth_date/year");
//		String evaluate = (String) expression.evaluate(document, XPathConstants.STRING);
		Integer evaluate = (Integer) xPath.evaluate(String.valueOf(expression), document, XPathConstants.NUMBER);
		System.out.println(evaluate);
	}
}
