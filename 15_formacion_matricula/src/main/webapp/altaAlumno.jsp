<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta Alumno</title>
<link rel='stylesheet' type='text/css' media='screen' href='login.css'>
</head>
<body>
	<div id="centrado">
		<form action="AltaAlumno" method="POST">
			<fieldset>
				<legend>Datos Personales:</legend>
				Usuario:<input type="text" name="usuario"><br>
				Password:<input type="password" name="password"><br>
				Nombre:<input type="text" name="nombre"><br> 
				Edad:<input type="number" name="edad"><br> 
				Email:<input type="text" name="email"><br> 
				<input type="submit"value="Guardar"> 
				<input type="reset" value="Restablecer">
			</fieldset>
		</form>
		<br> <a href="menu.jsp">Ir al menú</a>
	</div>
</body>
</html>