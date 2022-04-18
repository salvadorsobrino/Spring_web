<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="Insertar" method="post"> <!-- Por defecto es get -->
		Dirección:<input type="text" name="direccion"><br> 
		Temática:<input type="text" name="tematica"><br> <!-- cambiamos tema por tematica -->
		Descripción:<input type="text" name="descripcion"><br> 
		<input type="submit" value="Agregar">
	</form>
</body>
</html>