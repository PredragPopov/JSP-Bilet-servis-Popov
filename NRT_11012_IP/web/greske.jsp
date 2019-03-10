<%-- 
    Document   : greske
    Created on : Jan 31, 2015, 12:04:06 AM
    Author     : POPOV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Greske</title>
        <link rel="stylesheet" type="text/css" href="css.css">
    </head>
    <body>
        <%
            HttpSession sesija=request.getSession(true);
            String greska=(String)sesija.getAttribute("greska");
            %>
        <div id="heder" name="heder">
            <h1>Projekat iz predmeta Internet Programiranje</h1>
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            <h3>Desila se greska:</h3>
            <h5><% out.println(greska);%></h5>
        </div>
        
        <div id="futer" name="futer">
            <h3><pre>Predrag Popov NRT 110/12      jan.2015.</pre></h3>
        </div>
    </body>
</html>
