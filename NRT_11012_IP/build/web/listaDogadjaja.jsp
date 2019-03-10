

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="klase.*" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ListaDogadjaja</title>
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
        
        <% 
            HttpSession sesija=request.getSession(true);

            
            int tip=(Integer) sesija.getAttribute("tip");
            //int tip=Integer.parseInt(tipS.trim());
            
            Korisnik k=(Korisnik) sesija.getAttribute("korisnik");
            String username=k.getKorisnickoIme();
            sesija.setAttribute("korisnik2",k);
            
            
            
            
        %>
        
        <div id="heder" name="heder">
            <h3>Projekat iz predmeta Internet Programiranje</h3>
            
            <% if(tip==1){%>
            <h3>KORISNIK</h3>
            <a href="korisnik.jsp" class="dugme"><button>Pocetna</button></a>
            <form id="frmK1" name="frmK1" action="listaDogadjajaServlet" method="post">
                <input type="hidden" id="tip" name="tip" value="1">
                <button type="submit">DOGADjAJI</button>
            </form>
            <form id="frmRez" name="frmRez" action="PrikaziRezServlet" method="post">
                <input type="hidden" id="faktorX" name="faktorX" value="1">
                <button type="submit">Moje rezervacije</button>
            </form>
            <%}else if(tip==2 || tip==3){%>
            <h3>BLAGAJNIK</h3>
            <a href="blagajnik.jsp" class="dugme"><button>Pocetna</button></a>
            <%}%>
            <a href="Profil.jsp" class="dugme"><button>Profil</button></a>
            <a href="PromenaLozinkeServlet" class="dugme"><button>Promena lozinke</button></a>
            <a href="LogOutServlet" class="dugme"><button>LogOut</button></a>
            
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            
            <div id="divPretraga" name="divPretraga">
            <form id="formaPretraga1" name="formaPretraga1" action="PretragaDogadjajaServlet" method="post">
                <label for="FPnaziv">Unesite tacan naziv dogadjaja</label>
                <input type="text" name="FPnaziv" id="FPnaziv">
                <input type="hidden" id="izbor" name="izbor" value="poNazivu">
                
                <% if(tip==1){%>
                <input type="hidden" id="faktorX" name="faktorX" value="1">
                <%} else if(tip==2){%>
                <input type="hidden" id="faktorX" name="faktorX" value="2">
                <%} else if(tip==3){%>
                <input type="hidden" id="faktorX" name="faktorX" value="3">
                <%}%>
                <button type="submit" onclick="proveraPrazno('FPnaziv')">Pretrazi</button>
            </form>
            <br>
            <form id="formaPretraga2" name="formaPretraga2" action="PretragaDogadjajaServlet" method="post">
                <label for="FPdatum">Unesite datum odrzavanja dogadjaja</label>
                <input type="text" name="FPdatum" id="FPdatum">
                <input type="hidden" id="izbor" name="izbor" value="poDatumu">
                
                <% if(tip==1){%>
                <input type="hidden" id="faktorX" name="faktorX" value="1">
                <%} else if(tip==2){%>
                <input type="hidden" id="faktorX" name="faktorX" value="2">
                <%} else if(tip==3){%>
                <input type="hidden" id="faktorX" name="faktorX" value="3">
                <%}%>
                <button type="submit" onclick="proveraPrazno('FPdatum')">Pretrazi</button>
            </form>
            <br>
            <form id="formaPretraga3" name="formaPretraga3" action="PretragaDogadjajaServlet" method="post">
                <label for="FPmesto">Unesite mesto odrzavanja dogadjaja</label>
                <input type="text" name="FPmesto" id="FPmesto">
                <input type="hidden" id="izbor" name="izbor" value="poMestu">
                
                <% if(tip==1){%>
                <input type="hidden" id="faktorX" name="faktorX" value="1">
                <%} else if(tip==2){%>
                <input type="hidden" id="faktorX" name="faktorX" value="2">
                <%} else if(tip==3){%>
                <input type="hidden" id="faktorX" name="faktorX" value="3">
                <%}%>
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
                        <td>
                        <% if(tip==1){ %>
                        
                            <form id="frmDetaljiD" name="frmDetaljiD" action="odabranDogadjaj.jsp" method="post">
                                <input type="hidden" id="korisnik" name="korisnik" value="<%out.println(username);%>">
                                <input type="hidden" id="dogadjajID" name="dogadjajID" value="${d.ID}">
                                
                                <button type="submit">Detalji</button>
                            </form>
                        
                        <%} else if(tip==2){%>
                        <form id="B_frmProdaj" name="B_frmProdaj" action="B_odabranDogadjaj.jsp" method="post">
                            <input type="hidden" id="dogadjajID" name="dogadjajID" value="${d.ID}">
                            <input type="hidden" id="faktorX" name="faktorX" value="2">
                            <button type="submit">Prodaj karte</button>
                        </form>
                        <%} else if(tip==3){%>
                        <form id="B_frmProdaj" name="B_frmProdaj" action="B_odabranDogadjaj.jsp" method="post">
                            <input type="hidden" id="dogadjajID" name="dogadjajID" value="${d.ID}">
                            <input type="hidden" id="faktorX" name="faktorX" value="3">
                            <button type="submit">Izmeni</button>
                        </form>
                        <%}%>
                        </td>
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
