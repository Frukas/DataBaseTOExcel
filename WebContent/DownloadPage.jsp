<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- Pode redirecionar para essa pagina ou colocar essa parte de código em outro JSP -->
<form action="DownloadExcel" method="get">
	<!--Funcionario   : <input type="date" name="uname"><br>-->
	Data de inicio: <input type="date" name="StData"><br>	
	Data de final : <input type="date" name="EnData"><br>	
	<input type="submit" value="Gerar">
</form>

</body>
</html>