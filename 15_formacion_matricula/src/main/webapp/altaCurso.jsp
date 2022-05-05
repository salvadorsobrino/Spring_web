<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta Curso</title>
<link rel='stylesheet' type='text/css' media='screen' href='login.css'>
</head>
<body>
	<div id="centrado">
		<form action="AltaCurso" method="POST">
			<fieldset>
				<legend>Datos Curso:</legend>
				Nombre:<input type="text" name="nombre"><br>
				Duracion:<input type="number" name="duracion"><br>
				Fecha:<input type="date" name="fechaInicio"><br> 
				Precio:<input type="number" name="precio"><br> 
				<input type="submit"value="Guardar"> 
				<input type="reset" value="Restablecer">
			</fieldset>
		</form>
		<br> <a href="menu.jsp">Ir al menú</a>
	</div>
</body>
</html>