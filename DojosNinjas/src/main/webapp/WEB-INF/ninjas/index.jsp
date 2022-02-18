<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ninjas</title>
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
		<h1 class="text-danger">All Ninjas</h1>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Age</th>
					<th scope="col">Dojo Name</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ninja" items="${ninjas}">
					<tr>
						<td><c:out value="${ninja.getFirstName()}" /></td>
						<td><c:out value="${ninja.getLastName()}" /></td>
						<td><c:out value="${ninja.getAge()}" /></td>
						<td><c:out value="${ninja.getDojo().getName()}" /></td>
						<td>
							<form class="d-inline" action="/dojos/${dojo.getId()}"
								method="post">
								<input type="hidden" name="_method" value="delete">     <input
									class="btn btn-sm btn-danger" type="submit" value="Delete">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<h2 class="text-center mb-3">Create a Dojo!</h2>
		<div style="width: 50%;" class="mx-auto">
			<form:form
				class="row g-2 justify-content-center bg-dark text-light py-4 px-3 rounded"
				action="/ninjas" method="post" modelAttribute="ninja">

				<form:label for="firstName" path="firstName" class="form-label">First Name:</form:label>
				<form:errors path="firstName" class="text-danger" />
				<form:input type="text" path="firstName" class="form-control"
					id="firstName" />

				<form:label for="lastName" path="lastName" class="form-label">Last Name:</form:label>
				<form:errors path="lastName" class="text-danger" />
				<form:input type="text" path="lastName" class="form-control"
					id="lastName" />

				<form:label for="age" path="age" class="form-label">Age:</form:label>
				<form:errors path="age" class="text-danger" />
				<form:input type="number" path="age" class="form-control" id="age" />

				<form:label for="dojo" path="dojo" class="form-label">Dojo:</form:label>
				<form:errors path="dojo" class="text-danger" />
				<form:select path="dojo">
					<c:forEach var="oneDojo" items="${dojos}">
						<!--- Cada opción VALUE es el id de la persona --->
						<form:option value="${oneDojo.id}">
							<!--- Esto es lo que se muestra al usuario como opción --->
							<c:out value="${oneDojo.name}" />
						</form:option>
					</c:forEach>
				</form:select>

				<button class="btn btn-primary mt-4" type="submit">Save</button>
			</form:form>
		</div>
	</div>
</body>
</html>