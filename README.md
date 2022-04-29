2.- Buscador de páginas. Se trata de realizar una aplicación buscador, que solicite 
la temática de búsqueda y nos muestre todas las páginas asociadas a dicha temática. Cada p´gina registrada
se caracteriza por una dirección, tematica y descripción. En una segunda versión se podrán dar de alta nuevas
páginas

3.- Realizar una aplicación que permita gestionar una lista de productos. Cada producto tiene
un nombre, seccion, precio y stock. La aplicación permitirá buscar productos por sección, dar de alta
nuevos productos, eliminar productos por nombre y modificar precio de productos a partir de
su nombre.

7.- Realizar una aplicación para gestión de alumnos. Dos operaciones: Añadir nuevos alumnos y
consultar alumnos por curso. 

Añadir alumnos: En una página, se solicitan los datos del alumno 
y al pulsar el botón se graba el nuevo alumno en la base de datos. Habrá que comprobar que el 
alumno no exista, porque si ya existe no se grabará. 

Consultar alumnos por curso: Aparece una página con una lista desplegable en el que aparecen los cursos, al seleccionar un curso, se mostrará en la misma
página una tabla con los datos de los alumnos de dicho curso

11.- Partiendo de la capa de persistencia de formacion, queremos incorporar las siguiente funcionalidades:
- Validar usuarios. Dada usuario y contraseña, obtener los datos del alumno
- Cursos de un alumno. A partir del usuario, obtener la lista de cursos en donde está matriculado en alumno
- Lista de cursos
- Alumnos por curso. A partir de un nombre de curso, se devuelve los alumnos matriculados en dicho curso
- Matricular alumno. A partir del usuario e idCurso, el alumno se matriula en dicho curso

13.-Aplicación formación completa:
- Alta de alumnos. Debe permitir grabar nuevos alumnos, pero sin repetir usuario
- Alta de cursos. Permite grabar nuevos cursos, pero no debe permitir repetir nombre de curso
- Matricular alumnos. Aparece la lista de alumnos para elegir el alumno a matricular. Una vez elegido
en la misma página se le pide mediate otra lista que elija el curso donde se le va a matricualar. En esta
lista, solo deben aparecer los cursos en los que el alumno no está matriculado
- Consulta de matriculas. Selecionamos rango de fechas y se muestra las matriculas de todos los cursos
comenzados entre ese rango de fechas. Se muestra nombre del curso, nombre del alumno y fecha de inicio de ese
curso
