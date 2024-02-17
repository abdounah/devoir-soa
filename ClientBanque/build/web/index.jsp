<%-- 
    Document   : index
    Created on : 19 janv. 2024, 22:30:15
    Author     : abdou
--%>

<%@page import="service.Compte"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="service.BanqueServiceService"%>
<%@page import="service.BanqueWS"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    double mt = 0;
    double res = 0;
    List<Compte> comptes = new ArrayList<>();

    if (request.getParameter("action") != null) {
        String action = request.getParameter("action");
        if ("converter".equals(action)) {
            if (request.getParameter("montant") != null) {
                mt = Double.parseDouble(request.getParameter("montant"));
                BanqueWS stub = new BanqueServiceService().getBanqueWSPort();
                res = stub.conversionDollarToOugiya(mt);
            }
        } else if ("comptes".equals(action)) {
            BanqueWS stub = new BanqueServiceService().getBanqueWSPort();
            comptes = stub.getComptes();
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="index.jsp">
            Montant : <input type="text" name="montant" value="<%=mt%>"/> 
            <input name="action" style="cursor: pointer;" type="submit" value="converter" />
            <input name="action" style="cursor: pointer;" type="submit" value="comptes" />
        </form>
        <%=mt %> en dollar = <%=res %> en ougiya
        <p>Liste des comptes</p>
        <table border="1" style="width: 100%;">
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Solde</th>
                </tr>
            </thead>
            <tbody>
                <% for(Compte compte : comptes) { %>
                    <tr>
                        <td><%= compte.getCode() %></td>
                        <td><%= compte.getSolde() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
