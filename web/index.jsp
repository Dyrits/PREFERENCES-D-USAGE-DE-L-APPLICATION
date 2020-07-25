<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Initialisation des variables via récupération des attributs:
    String counter = (String) request.getAttribute("counter");
    String color = (String) session.getAttribute("color");
    Map<String, String> colors = (Map<String, String>) request.getAttribute("colors");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Préférences d’usage de l’application</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Ranchers&display=swap');
        body {
            font-family: 'Ranchers', cursive;
            text-align: center;
            color : <%=color%>;
        }
    </style>
</head>
<body>
    <h1>Acceuil</h1>
    <form method="POST" action="<%=request.getContextPath()%>/ServletPreferences">
        <label>
            <select name="color">
                <% for (Map.Entry<String,String> option : colors.entrySet()) { %>
                <option
                        value="<%=option.getValue()%>"
                        <% if (option.getValue().equals(color)) { %>
                        selected
                        <% } %>
                ><%=option.getKey()%></option>
                <% } %>
            </select>
        </label>
        <input type="submit" value="Changer la couleur !">
    </form>
    <p>La page a été affichée <%=counter%> fois.</p>
</body>
</html>
