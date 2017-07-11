package com.process;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class deleteNonProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Document document;
    Document doc;
    String nNode,deleteValue;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out=response.getWriter();
		try {
			
			File input =new File( getServletContext().getResource("/nonProd.xml").toURI());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			doc = db.parse(input);
			deleteValue=request.getParameter("value");
			System.out.println("checking the value"+deleteValue);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		out.println(doc.getDocumentElement().getNodeName()+"Hello");
		NodeList nList = doc.getElementsByTagName("uURL");
		out.println(nList.getLength());
		
		for(int temp=0; temp<nList.getLength();temp++){
			Node n=nList.item(temp);
			nNode=nList.item(temp).getTextContent();
			out.println("<br>");
			out.println(nNode);
			
			if(nNode.equals(deleteValue)){
				Node n1=n.getParentNode();
				out.println("<br>inside");
				Node n2=n1.getParentNode();
				n2.removeChild(n1);
				break;
			}
			out.println("<br>outside");
			out.println("<br>nNode");
		}
		
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			File folder=new File(getServletContext().getResource("/nonProd.xml").toURI());
			StreamResult result = new StreamResult(folder);
			 transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		response.sendRedirect("parsingxmlNonProd.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
