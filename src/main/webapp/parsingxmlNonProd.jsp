<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.w3c.dom.*, javax.xml.parsers.*" %>
<%@page import="java.io.*"
%>
<%
  /*DocumentBuilderFactory docFactory = 
  DocumentBuilderFactory.newInstance();
  DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
  Document doc = docBuilder.parse
("F:\\JavaProgram\\Dashboard\\src\\main\\webapp\\nonProd.xml"); */

File input =new File( getServletContext().getResource("/nonProd.xml").toURI());
DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
DocumentBuilder db=dbf.newDocumentBuilder();
Document doc = db.parse(input);
  
  
  
%>
<%!
  public boolean isTextNode(Node n){
  return n.getNodeName().equals("#text");
  }
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Parsing of xml using DOM Parser</title>


<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">



<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#Delete').click(function(){
		var value1 = prompt("Please enter the password","Password");
		if(value1=="nc51969"){
		var value=prompt("Please enter the URL name  to delete","ex-1");
		$.ajax({
			
			type:'Get',
			 data:{value:value},
			 url:'deleteNonProd',
			 success:function(result){
				 window.location.reload(true);
			
		}
		
	});
		}
		else{
			
			alert("Please enter the correct Password");
		}
		
		
});
});

</script>




<link rel="stylesheet" type="text/css" href="startingPage.css">
</head>
<body>
<div style="text-align: center;"><img src="JD.png.png" width="200"  height="80" alt="logo" /></div>
<h2>Non-Production System</h2>

<input type="submit" id="prod" value="Production"
		onclick="location.href='https://jddash.herokuapp.com/index.jsp'">
	<input type="submit" id="nonprod" value="Non-Production"
		onclick="location.href='https://jddash.herokuapp.com/parsingxmlNonProd.jsp'">

  <table class="table table-hover" align="center"
		style="width: 100%; border-collapse: collapse;">
  <tr bgcolor="#b3b3b3">
			<th>Host</th>
			<th>SID</th>
			<th>SCS</th>
			<th>Type</th>
			<th>URL</th>
			<th>SAP Components</th>
  </tr>
  <%
  Element  element = doc.getDocumentElement(); 
  NodeList personNodes = element.getChildNodes(); 
  for (int i=0; i<personNodes.getLength(); i++){
  Node emp = personNodes.item(i);
  if (isTextNode(emp))
  continue;
  NodeList NameDOBCity = emp.getChildNodes(); 
  %>
  <tr bgcolor="#e6e6e6">
  <%
  for (int j=0; j<NameDOBCity.getLength(); j++ ){
  Node node = NameDOBCity.item(j);
  if ( isTextNode(node)) 
  continue;
  %>
  <%if(!node.getFirstChild().getNodeValue().contains("http")){ %>
  <td><%= node.getFirstChild().getNodeValue() %></td>
  <%} 
  else{
  %>
  <td><a href='<%= node.getFirstChild().getNodeValue() %>'><%= node.getFirstChild().getNodeValue()%></a></td>
  <%} %>
  <%
  } 
  %>
  </tr>
  <%
  }
  %>
  </table>
 <div align="center">
		<form action="nonProdForm.jsp">
			<input type="submit" value="Add" id="add" class="btn btn-default">
		</form>
		<input type="submit" id="Delete" value="Delete"
			class="btn btn-default">
	</div>
</html>