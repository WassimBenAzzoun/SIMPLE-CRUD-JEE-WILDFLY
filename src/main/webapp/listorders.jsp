<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entities.Order" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-3">
    <h2>List of Orders</h2>
    <table class="table table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Client Name</th>
                <th>Address</th>
                <th>Postal Code</th>
                <th>Phone</th>
                <th>Product ID</th>
                <th>Product Name</th>
            </tr>
        </thead>
        <tbody>
            <% List<Order> orders = (List<Order>) request.getAttribute("orders");
               for (Order order : orders) { %>
                   <tr>
                       <td><%= order.getId() %></td>
                       <td><%= order.getNameCli() %></td>
                       <td><%= order.getAddressCli() %></td>
                       <td><%= order.getCodepostal() %></td>
                       <td><%= order.getPhoneCli() %></td>
                       <td><%= order.getProduct().getId() %></td>
                       <td><%= order.getProduct().getName() %></td>
                   </tr>
            <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
