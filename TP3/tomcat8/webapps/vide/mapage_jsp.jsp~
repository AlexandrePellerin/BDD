<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.sql.*, java.io.*, metier.Personne" %>

<html>
<head>
<title> MaPage </title>
</head>
<body>
  <h1>Hello World</h1>
  <%
    Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection("jdbc:postgresql://psqlserv/n3p1","pelleria","moi");

    Statement stmt = con.createStatement();
	    String query = "select * from users";
	    ResultSet rs = stmt.executeQuery(query);
	    Personne pers = (Personne) session.getAttribute("personne");
	    if(pers==null){
		pers = new Personne();
		session.setAttribute("personne",pers);
	    }
	    
	%>

	<table>
	<tr>
	<th>login</th><th>mdp</th><th>mdp</th><th>nom</th>
	<th>prenom</th><th>adresse</th></tr>

	<% while(rs.next())
	{
	%>
	<tr>
	<% for(int i = 1 ; i <= 6 ; i++){
	%>
	<td> <%= rs.getString(i) %> </td>
	<% } %>
	</tr>
	<% } %>
	</table><br>
avec quelques accents à é è ù<br>
<%=pers%>
<% con.close(); %>
</body>
</html>
