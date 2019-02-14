<%@ page import="utils.Animal" %>
<%@ page import="utils.Farm" %>
<%@ page import="utils.State" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
  <title>Farm Clicker</title>
</head>
<body>

<%Integer id = (Integer)request.getAttribute("id");%>
<%State state = (State)request.getAttribute("state");%>
<%ArrayList<Animal> animals = (ArrayList<Animal>)request.getAttribute("animals");%>
<%ArrayList<Farm> farms = (ArrayList<Farm>)request.getAttribute("farms");%>

<h1 align="center">Farm Clicker</h1>
<h3>Day: <%out.print(state.day);%></h3>

<table style="margin-left:10%; width: 80%">
  <tr>
    <td style="vertical-align:text-top">
      <h3 align="center">Animals</h3>
      <form action = "main" method = "POST">
        <table style="width: 100%">
          <colgroup><col><col><col><col></colgroup>
          <%for (Animal animal : animals){ %>
          <tr>
            <td style="text-align:left; width: 20%"><%out.print(animal.count);%></td>
            <td style="text-align:left; width: 20%"><%out.print(animal.name);%></td>
            <td>
              <input style="text-align:left; width: 100%" type = "submit"
                     name = <%out.print("\"buy"+animal.name+"\"");%>; value = <%out.print("\"Buy ("+animal.cost+")\"");%> />
            </td>
            <td>
              <input style="text-align:left; width: 100%" type = "submit"
                     name = <%out.print("\"sell"+animal.name+"\"");%>; value = <%out.print("\"Sell ("+animal.value+")\"");%> />
            </td>
          </tr>
          <%}%>
        </table>
        <input type = "hidden" name = "id" value = "<%out.print(id);%>" />
      </form>
    </td>
    <td style="vertical-align:text-top;">
      <h3 align="center">Farms</h3>
      <form action = "main" method = "POST">
        <table style="margin-left:20%; width: 60%">
          <colgroup><col><col><col><col><col></colgroup>
          <%for (Farm farm : farms){ %>
          <tr>
            <td style="text-align:left; width: 10%"><%out.print(farm.count);%></td>
            <td style="text-align:left; width: 20%"><%out.print(farm.name);%></td>
            <td style="text-align:left; width: 30%"><%out.print("capacity +" + farm.capacity);%></td>
            <td>
              <input style="text-align:left; width: 100%" type = "submit"
                     name = <%out.print("\"buy"+farm.name+"\"");%>; value = <%out.print("\"Buy ("+farm.cost+")\"");%> />
            </td>
            <td>
              <input style="text-align:left; width: 100%;" type = "submit"
                     name = <%out.print("\"sell"+farm.name+"\"");%>; value = <%out.print("\"Sell ("+farm.value+")\"");%> />
            </td>
          </tr>
          <%}%>
        </table>
        <input type = "hidden" name = "id" value = "<%out.print(id);%>" />
      </form>
    </td>
  </tr>
</table>

<div style="margin-left: 10%">
  Budget: <%out.print(state.budget);%>
  Capacity:
  <%if(state.population==state.capacity) {%>
  <font color="red"><%out.print(state.population+"/"+state.capacity);%></font>
  <%} else out.print(state.population+"/"+state.capacity);%>
  <br /><br />

  <form action = "main" method = "POST">
    <input type = "submit" name = "end turn" value = "End day" />
    <input type = "submit" name = "restart" value = "Try again" />
    <input type = "submit" name = "guide" value = "Guide" />
    <input type = "hidden" name = "id" value = "<%out.print(id);%>" />
  </form>
</div>
<h5>
  You are logged as: user<%out.print(id);%> <br />
  active users: <%out.print((Integer)request.getAttribute("userNumber"));%>
</h5>

</body>
</html>
