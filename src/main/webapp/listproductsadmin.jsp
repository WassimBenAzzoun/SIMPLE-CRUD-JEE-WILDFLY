<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entities.Product" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Product List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-3">
    <h2>Admin View of Products</h2>
    <div class="row">
        <% List<Product> products = (List<Product>) request.getAttribute("products");
           for (Product product : products) { %>
               <div class="col-md-4 mt-3">
                   <div class="card">
                       <img class="card-img-top" src="<%= product.getImageurl() %>" alt="Product Image">
                       <div class="card-body">
                           <h5 class="card-title"><%= product.getName() %></h5>
                           <p class="card-text"><%= product.getDescription() %></p>
                           <p class="card-text">Price: $<%= product.getPrice().toString() %></p>
                           <form action="Controller" method="post">
                               <input type="hidden" name="action" value="editProduct">
                               <input type="hidden" name="productId" value="<%= product.getId() %>">
                               <button type="submit" class="btn btn-primary">Edit</button>
                           </form>
                           <form action="Controller" method="post" style="margin-top: 5px;">
                               <input type="hidden" name="action" value="deleteProduct">
                               <input type="hidden" name="productId" value="<%= product.getId() %>">
                               <button type="submit" class="btn btn-danger">Delete</button>
                           </form>
                       </div>
                   </div>
               </div>
        <% } %>
    </div>
</div>
</body>
</html>
