<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <h2>Edit Product</h2>
    <form action="Controller" method="post">
        <input type="hidden" name="action" value="updateProduct">
        <input type="hidden" name="productId" value="<%= ((Product)request.getAttribute("product")).getId() %>">
        
        <div class="form-group">
            <label for="productName">Name:</label>
            <input type="text" class="form-control" id="productName" name="name" value="<%= ((Product)request.getAttribute("product")).getName() %>" required>
        </div>
        
        <div class="form-group">
            <label for="productDescription">Description:</label>
            <textarea class="form-control" id="productDescription" name="description" required><%= ((Product)request.getAttribute("product")).getDescription() %></textarea>
        </div>
        
        <div class="form-group">
            <label for="productPrice">Price:</label>
            <input type="text" class="form-control" id="productPrice" name="price" value="<%= ((Product)request.getAttribute("product")).getPrice().toString() %>" required>
        </div>
        
        <div class="form-group">
            <label for="productImageUrl">Image URL:</label>
            <input type="text" class="form-control" id="productImageUrl" name="imageurl" value="<%= ((Product)request.getAttribute("product")).getImageurl() %>">
        </div>
        
        <button type="submit" class="btn btn-primary">Update Product</button>
    </form>
</div>
</body>
</html>
