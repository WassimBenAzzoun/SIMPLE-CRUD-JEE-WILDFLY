<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entities.Product" %>

<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">TikTak</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="register.html">Register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login.html">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="dashboard.html">Dashboard</a>
                </li>
            </ul>
        </div>
    </nav>
<div class="container mt-3">
    <h2>Available Products</h2>
    <div class="row">
        <% List<Product> products = (List<Product>) request.getAttribute("products");
           for (Product product : products) { %>
               <div class="col-md-4 mt-3">
                   <div class="card">
                       <img class="card-img-top" src="<%= product.getImageurl() %>" alt="Product image">
                       <div class="card-body">
                           <h5 class="card-title"><%= product.getName() %></h5>
                           <p class="card-text"><%= product.getDescription() %></p>
                           <p class="card-text">Price: $<%= product.getPrice() %></p>
                           <!-- Button to Open the Modal -->
                           <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#orderModal<%= product.getId() %>">
                               Order
                           </button>
                       </div>
                   </div>
               </div>
               
               <div class="modal" id="orderModal<%= product.getId() %>">
                   <div class="modal-dialog">
                       <div class="modal-content">

                           
                           <div class="modal-header">
                               <h4 class="modal-title">Order Details for <%= product.getName() %></h4>
                               <button type="button" class="close" data-dismiss="modal">&times;</button>
                           </div>

                           
                           <div class="modal-body">
                               <form action="Controller" method="post">
                                   <input type="hidden" name="action" value="orderProduct">
                                   <input type="hidden" name="productId" value="<%= product.getId() %>">
                                   <div class="form-group">
                                       <label>Name:</label>
                                       <input type="text" name="name_cli" class="form-control" required>
                                   </div>
                                   <div class="form-group">
                                       <label>Address:</label>
                                       <input type="text" name="address_cli" class="form-control" required>
                                   </div>
                                   <div class="form-group">
                                       <label>Postal Code:</label>
                                       <input type="text" name="codepostal" class="form-control">
                                   </div>
                                   <div class="form-group">
                                       <label>Phone:</label>
                                       <input type="text" name="phone_cli" class="form-control">
                                   </div>
                                   <button type="submit" class="btn btn-success">Confirm Order</button>
                               </form>
                           </div>

                           
                           <div class="modal-footer">
                               <button type="button" class="close" data-dismiss="modal">Close</button>
                           </div>

                       </div>
                   </div>
               </div>
        <% } %>
    </div>
</div>
</body>
</html>
