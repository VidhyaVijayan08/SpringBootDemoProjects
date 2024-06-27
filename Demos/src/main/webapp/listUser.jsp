<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.chainsys.demos.model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <style>
        /* Reset default margin and padding */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            background-color: #f0f0f0;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            background-color: #fff;
            border-radius: 8px;
            border: 1px solid #ccc; /* Added border */
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            border-right: 1px solid #ddd; /* Added border */
        }

        th {
            background-color: #007bff; /* Blue */
            color: #fff;
            font-weight: bold;
            text-transform: uppercase;
            border-top: 1px solid #ddd; /* Added border */
        }

        tbody tr:last-child td {
            border-bottom: none; /* Remove bottom border for last row */
        }

        tbody tr:nth-child(even) {
            background-color: #f8f9fa; /* Light Gray */
        }

        tbody tr:hover {
            background-color: #e9ecef; /* Light Blue */
        }

        button {
            padding: 8px 16px;
            cursor: pointer;
            background-color: #007bff; /* Blue */
            color: #fff;
            border: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3; /* Darker Blue */
        }

        .actions {
            display: flex;
            gap: 8px;
        }

        .actions a {
            text-decoration: none;
        }

        .actions button {
            flex: 1;
        }

    </style>
</head>
<body>
    <div class="container">
        <h2>User List</h2>
        <table>
            <thead>
                <tr>
                    <th>User Id</th>
                    <th>User Name</th>
                    <th>Password</th>
                    <th colspan="2">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<User> users = (List<User>) request.getAttribute("USER_LIST");
                for (User user : users) {
                %>
                <tr>
                    <td><%=user.getId() %></td>
                    <td><%=user.getUsername() %></td>
                    <td><%= user.getPassword() %></td>
                    <td class="actions">
                        <a href="edit.jsp"><button>Edit</button></a>
                        <form action="/delete" method="get">
                            <input type="hidden" name="id" value="<%=user.getId() %>">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
