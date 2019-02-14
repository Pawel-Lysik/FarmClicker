<%@ page import="java.util.ArrayList" %>
<html>
<head>
  <title>Fight</title>
</head>
<body>

<%ArrayList<Integer> animals = (ArrayList<Integer>)request.getAttribute("attack");%>

<div align="center" style="margin-top: 5%">
  <h1>Fight</h1>
  A pack of <%out.print(animals.get(8));%> wolves and <%out.print(animals.get(7));%> foxes attacked you! <br />
  They killed <%out.print(animals.get(0));%> rabbit, <%out.print(animals.get(1));%> sheep,
  <%out.print(animals.get(2));%> pig, <%out.print(animals.get(3));%> cow, <%out.print(animals.get(4));%> horse,
  <%out.print(animals.get(5));%> small dog, <%out.print(animals.get(6));%> big dog.<br /><br />
  <form align="center" action = "main" method = "POST">
    <input type = "submit" name = "continue" value = "Continue" />
    <input type = "hidden" name = "id" value = "<%out.print((Integer)request.getAttribute("id"));%>" />
  </form>
</div>
</body>
</html>
