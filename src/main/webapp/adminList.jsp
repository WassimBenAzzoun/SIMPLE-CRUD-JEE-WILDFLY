<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entities.Admin"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Users</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h1>Admin Users List</h1>
    <table class="table table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Username</th>
                
            </tr>
        </thead>
        <tbody>
            <% 
            List<Admin> admins = (List<Admin>) request.getAttribute("admins");
            if (admins != null && !admins.isEmpty()) {
                for (Admin admin : admins) {
            %>
                    <tr>
                        <td><%= admin.getId() %></td>
                        <td><%= admin.getUser() %></td>
                        
                    </tr>
            <% 
                }
            } else {
            %>
                <tr>
                    <td colspan="3" class="text-center">No admins found</td>
                </tr>
            <% 
            }
            %>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
