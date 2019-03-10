<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="klase.*" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GOST</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <style type="text/css">
            table{
                width: 100%;
                border-collapse: collapse;
                text-align: center;
            }
            th{
                border-bottom: 2px solid white;
                height: 30px;
            }
            label{
                width: 300px;
            }
        </style>
    </head>
    <body>
        <div id="heder" name="heder">
            <h3>Projekat iz predmeta Internet Programiranje</h3>
            <h3>GOST</h3>
            <a href="POCETNA.jsp" id="nazad" name="nazad"><button>Log in</button></a>
            <a href="javascript: history.go(-1)" id="nazad" name="nazad"><button>Nazad</button></a>
           
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            
            <div id="divPretraga" name="divPretraga">
            <form id="formaPretraga1" name="formaPretraga1" action="PretragaDogadjajaServlet" method="post">
                <label for="FPnaziv">Unesite tacan naziv dogadjaja</label>
                <input type="text" name="FPnaziv" id="FPnaziv">
                <input type="hidden" id="izbor" name="izbor" value="poNazivu">
                <input type="hidden" id="faktorX" name="faktorX" value="0">
                
                <button type="submit" onclick="proveraPrazno('FPnaziv')">Pretrazi</button>
            </form>
            <br>
            <form id="formaPretraga2" name="formaPretraga2" action="PretragaDogadjajaServlet" method="post">
                <label for="FPnaziv">Unesite datum odrzavanja dogadjaja</label>
                <input type="text" name="FPdatum" id="FPdatum">
                <input type="hidden" id="izbor" name="izbor" value="poDatumu">
                
                <input type="hidden" id="faktorX" name="faktorX" value="0">
                
                <button type="submit" onclick="proveraPrazno('FPdatum')">Pretrazi</button>
            </form>
            <br>
            <form id="formaPretraga3" name="formaPretraga3" action="PretragaDogadjajaServlet" method="post">
                <label for="FPnaziv">Unesite mesto odrzavanja dogadjaja</label>
                <input type="text" name="FPmesto" id="FPmesto">
                <input type="hidden" id="izbor" name="izbor" value="poMestu">
                
                <input type="hidden" id="faktorX" name="faktorX" value="0">
                
                <button type="submit" onclick="proveraPrazno('FPmesto')">Pretrazi</button>
            </form>
        </div>
            <br><br>
            
            <table>
                <thead>
                <th>Naziv dogadjaja</th>
                <th>Mesto odrzavanja</th>
                <th>Vreme odrzavanja</th>
                <th>Opis</th>
                <th>Vise</th>
                </thead>
                
                <tbody>  
                  <c:forEach var="d" items="${lista_dogadjaja}">
                    <tr>
                        <td><c:out value="${d.naziv}"/></td>
                        <td><c:out value="${d.mesto}"/></td>
                        <td><c:out value="${d.vremeOdrzavanja}"/></td>
                        <td><c:out value="${d.opis}"/></td>
                        
                        <td>Karte kupite na blagajni</td>
                        
                    </tr>
                  </c:forEach>
                </tbody>
            </table>
        </div>
        
        <div id="futer" name="futer">
            <h3><pre>Predrag Popov NRT 110/12      jan.2015.</pre></h3>
        </div>
        <script type="text/javascript">
            function proveraPrazno(id){
                var text="";
                var el=document.getElementById(id);
                var x=el.value;
                if(x===null || x==="" ){
                    alert("Niste popunili polje  "+id); 
                    location.reload();
                }
                
            }   
        </script>
    </body>
</html>
