<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dojos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="js/app.js"></script>
</head>
<body>
	<div class="container justify-center">
		<h1 class="text-danger">All Dojos</h1>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dojo" items="${dojos}">
					<tr>
						<td><a href="/dojos/${dojo.getId()}"><c:out
									value="${dojo.getName()}" /></a></td>
						<td>
							<form class="d-inline" action="/dojos/${dojo.getId()}"
								method="post">
								<input type="hidden" name="_method" value="delete">     <input
									class="btn btn-sm btn-danger" type="submit" value="Delete">
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<h2 class="text-center mb-3">Create a Dojo!</h2>
		<div style="width: 50%;" class="mx-auto">
			<form:form
				class="row g-2 justify-content-center bg-dark text-light py-4 px-3 rounded"
				action="/dojos" method="post" modelAttribute="dojo">

				<form:label for="name" path="name" class="form-label">Name:</form:label>
				<form:errors path="name" class="text-danger" />
				<form:input type="text" path="name" class="form-control" id="name" />
				
				
				<button class="btn btn-primary mt-4" type="submit">Save</button>
			</form:form>
		</div>
	</div>
</body>
</html>