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
	<h1>Home Page</h1>
	<div class="container">
	<jsp:include page="resources/menu.jsp"></jsp:include>
	<div class="row">
		<h4 class="col-6">Add New Item</h4>
	</div>
	<c:url var="save" value="/item-add"></c:url>
	<form action="${save }" class="form col-6" method="post">
	<input type="hidden" name="itemid" value="${ item.id}">
		<div class="form-group">
			<label for="">Category Name</label>
			<select name="categoryid" id="" class="form-control">
				<c:forEach items="${categories }" var="c">
					<option value="${c.id }" ${item.category.id ==c.id ? 'selected' : '' }>${c.name }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="">Item Name</label>
			<input type="text" name="itemname" value="${item.name }" class="form-control" required="required" placeholder="Enter item name"/>
		</div>
		<div class="form-group">
			<label for="">Price</label>
			<input type="number" name="price" value="${item.price }" class="form-control" required="required" placeholder="Enter item price"/>
		</div>
		<button type="submit" class="btn btn-success">Save</button>
		<button type="reset" class="btn btn-danger">Clear</button>
	</form>
	</div>
</body>
</html>