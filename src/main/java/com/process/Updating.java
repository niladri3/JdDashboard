package com.process;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Updating extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		PrintWriter out=response.getWriter();
		System.out.println("Welcome");
		
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			//String uid=request.getParameter("uid");
			String uhost=request.getParameter("uhost");
			String usid=request.getParameter("usid");
			String uscs=request.getParameter("uscs");
			String utype=request.getParameter("utype");
			String uurl=request.getParameter("uurl");
			String uscomps=request.getParameter("uscomp");
			//System.out.println(uhost+"Hello");
			docBuilder = docFactory.newDocumentBuilder();
			File input =new File( getServletContext().getResource("/Prod.xml").toURI());
			Document doc = docBuilder.parse(input);
			//Node earth = doc.getFirstChild();//roseindia
			//NamedNodeMap earthAttributes = earth.getAttributes();
			//Attr galaxy = doc.createAttribute("galaxy");
			//galaxy.setValue("milky way");
			//earthAttributes.setNamedItem(galaxy);
			//Element child = (Element) doc.getFirstChild();//roseindia
			Node newChild=doc.getFirstChild();//roseindia
			
			Node employee=doc.createElement("employee");
			newChild.appendChild(employee);
			
			/*Node userID=doc.createElement("uid");
			employee.appendChild(userID);
			Text text=doc.createTextNode(uid);
			userID.appendChild(text);*/
			
			
			Node userhost=doc.createElement("uhost");
			employee.appendChild(userhost);
			Text text1=doc.createTextNode(uhost);
			userhost.appendChild(text1);
			
			
			Node userID1=doc.createElement("usID");
			employee.appendChild(userID1);
			Text text2=doc.createTextNode(usid);
			userID1.appendChild(text2);
			
			Node userSCS=doc.createElement("uscs");
			employee.appendChild(userSCS);
			Text text3=doc.createTextNode(uscs);
			userSCS.appendChild(text3);
			
			
			Node userType=doc.createElement("uType");
			employee.appendChild(userType);
			Text text4=doc.createTextNode(utype);
			userType.appendChild(text4);
			
			
			Node userURL=doc.createElement("uURL");
			employee.appendChild(userURL);
			Text text5=doc.createTextNode(uurl);
			userURL.appendChild(text5);
			
			
			Node usComps=doc.createElement("uComps");
			employee.appendChild(usComps);
			Text text6=doc.createTextNode(uscomps);
			usComps.appendChild(text6);
			
			/*Node dob=doc.createElement("date-of-birth");
			employee.appendChild(dob);
			Text text1=doc.createTextNode(uhost);
			dob.appendChild(text1);
			
			Element city=doc.createElement("city");
			employee.appendChild(city);
			Text text2=doc.createTextNode(usid);
			city.appendChild(text2);*/
			
			
			//Node canada = doc.createElement("employee");
			//canada.setTextContent("ca");
			//earth.appendChild(canada);
			
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			//initialize StreamResult with File object to save to file
			StreamResult result1= new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);
			
			
			//File folder = new File("F:\\JavaProgram\\Dashboard\\src\\main\\webapp");
			File folder=new File(getServletContext().getResource("/Prod.xml").toURI());
			//StreamResult result = new StreamResult(new File(folder, "roseindia.xml"));
			StreamResult result = new StreamResult(folder);
		    //File outputFile = new File("roseindia.xml");
		    //StreamResult result1 = new StreamResult(outputFile );
		    // creating output stream
		    transformer.transform(source, result);
			

			//String xmlString = result.getWriter().toString();
			//System.out.println(xmlString);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("index.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
