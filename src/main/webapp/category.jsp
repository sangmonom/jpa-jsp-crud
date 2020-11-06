<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<jsp:include page="resources/common.jsp"></jsp:include>
</head>
<body>
	<div class="container">
	<jsp:include page="resources/menu.jsp"></jsp:include>
	<div class="row mt-2">
		<div class="col-4">
			<h3>All Categories</h3>
		</div>
		<div class="col">
			<c:url value="/category-add.jsp" var="add"></c:url>
			<a href="${add }" class="btn btn-secondary">Add New Category</a>
		</div>
	</div>
	<table class="table col-6">
		<thead>
			<tr>
				<th>ID</th>
				<th>Category Name</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<!-- Category List -->
			<c:forEach items="${categories }" var="c">
				<tr>
					<td>${c.id }</td>
					<td>${c.name }</td>
					<td>
						<c:url var="action" value="/category-edit">
							<c:param name="categoryid">${c.id }</c:param>
						</c:url>
						<a href="${action }" class="btn btn-outline-success">Edit</a>
					</td>
					<td>
						<a href="#" class="btn btn-outline-danger">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>