package ulaoda.tony.http;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class getWebReturnXmlDoc {
	
	public static void main(String[] args) {
		String httpString ="http://passport.lenovo.com/interserver/authen/1.2/getaccountid";
		String param="lpsust=ZAgAAAAAAAGE9MTAwMTk0NDUxNDQmYj0yJmM9MSZkPTExNTUyJmU9QzM4MjJDOUZFNTRDNEVGRkY3Q0IzRDlFQTAwMzQxNkExJmg9MTM5MjYwNTEyOTAzOSZpPTQzMjAwJmo9MCZvPTgxNzU4YWI3ZTdjNTQzYzg5MTI1YTY3N2MzMWU3Yzg0JnA9bWFjJnE9MCZ1c2VybmFtZT0xMTA2MTg0NDUlNDBxcS5jb23E7W8NyQp0kNbylvwM8wA3&realm=10000630.realm.lenovoidapps.com";
		String httpString2="http://www.baidu.com";
		String param2="";
		String returnString = HttpRequest.sendGet(httpString, param);
		System.out.println(returnString);
		
		if (returnString.contains("<AccountID>")) {
			String UserId=returnString.substring(returnString.indexOf("<AccountID>")+11, returnString.indexOf("</AccountID>"));
			String userName=returnString.substring(returnString.indexOf("<Username>")+11,returnString.indexOf("</Username>"));
			System.out.println(UserId);
			System.out.println(userName);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public List xmlElements(String xmlDoc) {
		  // 创建一个新的字符串
		  StringReader read = new StringReader(xmlDoc);
		  // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		  InputSource source = new InputSource(read);
		  // 创建一个新的SAXBuilder
		  SAXBuilder sb = new SAXBuilder();
		  try {
		   // 通过输入源构造一个Document
		   Document doc = sb.build(source);
		   // 取的根元素
		   Element root = doc.getRootElement();
		   System.out.println(root.getName());// 输出根元素的名称（测试）
		   // 得到根元素所有子元素的集合
		   List jiedian = root.getChildren();
		   // 获得XML中的命名空间（XML中未定义可不写）
		   Namespace ns = root.getNamespace();
		   Element et = null;
		   for (int i = 0; i < jiedian.size(); i++) {
		    et = (Element) jiedian.get(i);// 循环依次得到子元素
		    // 无命名空间定义时
		     System.out.println(et.getChild("Ver", ns).getText());
		   }
		   /**//*
		     * 如要取<row>下的子元素的名称
		     */
		   et = (Element) jiedian.get(0);
		   List zjiedian = et.getChildren();
		   for (int j = 0; j < zjiedian.size(); j++) {
		    Element xet = (Element) zjiedian.get(j);
		    System.out.println(xet.getName());
		   }
		  } catch (JDOMException e) {
		   // TODO 自动生成 catch 块
		   e.printStackTrace();
		  } catch (IOException e) {
		   // TODO 自动生成 catch 块
		   e.printStackTrace();
		  }
		  return null;
		 }
	
	
	
	
}
