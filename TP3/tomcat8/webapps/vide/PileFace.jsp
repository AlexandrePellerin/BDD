<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="metier.JeuPileOuFace"%>

<html>
<head>
<title> PileOuFace </title>
</head>
<body>
<center>

<h1> Pile ou Face </h1>

<%
   JeuPileOuFace jeu = (JeuPileOuFace) session.getAttribute("jeu");
   if(jeu==null){
   jeu = new JeuPileOuFace();
   jeu.init();
   session.setAttribute("jeu",jeu);
   }else{
   String temp = request.getParameter("coup");
   
   if(temp != null  && (temp.equals("P") || temp.equals("F"))){
   jeu.play(temp.charAt(0));
   }
   }
   %>


<p>Le premier arriv&eacute; &agrave; 10 a gagn&eacute;!<br>
Deux faces identiques je gagne, deux faces diff&eacute;rentes vous gagnez!</p>

<p>
  <% if(jeu.getPointsHumain() == 0 && jeu.getPointsOrdi() == 0 ){ %>
  Nous allons d&eacute;buter une nouvelle partie.
  <% }else{ %>
  Vous venez de jouer
  <%=jeu.getLastHumain()%>
  et l'ordi
  <%=jeu.getLastOrdi()%>
  <% } %>
</p>

<p> Scores <%=jeu.getPointsHumain()%> &agrave; <%=jeu.getPointsOrdi()%></p>

<p> <% if(!jeu.termine()){ %>
  Vous jouez
  <a href="PileFace.jsp?coup=P">Pile</a>
  ou
  <a href="PileFace.jsp?coup=F">Face</a>
  ?
  <% }else{ %>
  <h1>
    Vous avez
    <% if(jeu.getPointsHumain()==10){ %>
    gagn&eacute; !
    <% } else { %>
    perdu !
    <% } %>
    <a href="PileFace.jsp">Rejouer?</a>
  </h1>
  <% session.setAttribute("jeu",null);
     } %>
</p>



</center>
</body>
</html>
