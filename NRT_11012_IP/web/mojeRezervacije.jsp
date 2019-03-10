<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="klase.*"%>
<%@ page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MojeRezervacije</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <style type="text/css">
            table{
                border-collapse: collapse;
                text-align: center;
            }
            th{
                border: 2px solid white;
                
                height: 30px;
            }
            #uskoTD{
                width: 10px;
            }
            
            
            #opcija{
                width: 20px;
            }
        </style>
    </head>
    <body>
        <div id="heder" name="heder">
            <h3>Projekat iz predmeta Internet Programiranje</h3>
            <h3>MOJE REZERVACIJE</h3>
            <a href="korisnik.jsp" class="dugme"><button>Pocetna</button></a>
            <form id="frmK1" name="frmK1" action="listaDogadjajaServlet" method="post">
                <input type="hidden" id="tip" name="tip" value="1">
                <button type="submit">DOGADjAJI</button>
            </form>
            <form id="frmRez" name="frmRez" action="PrikaziRezServlet" method="post">
                <input type="hidden" id="faktorX" name="faktorX" value="1">
                <button type="submit">Moje rezervacije</button>
            </form>
            <a href="Profil.jsp" class="dugme"><button>Profil</button></a>
            <a href="promenaLozinke.jsp" class="dugme"><button>Promena lozinke</button></a>
            <a href="LogOutServlet" class="dugme"><button>LogOut</button></a>
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            <table id="mojeRez" name="mojeRez">
                <thead>
                <th class="uskoTD">ID</th>
                <th>Dogadjaj naziv</th>
                <th>Mesto</th>
                <th>Vreme odrzavanja</th>
                <th>Tip sedista</th>
                <th class="uskoTD">Cena jedne</th>
                <th class="uskoTD">Broj ul</th>
                <th class="uskoTD">Ukupna cena</th>
                <th>Vreme Rez</th>
                <th class="uskoTD">Kupljena</th>
                <th class="uskoTD">Istekla</th>
                <th class="opcija">Opcija</th>
                </thead>
                <tbody>
                    <c:forEach var="r" items="${listaRez}">
                        <tr>
                            <td class="uskoTD"><c:out value="${r.ID}"/></td>
                            <td><c:out value="${r.dogadjajNaziv}"/></td>
                            <td><c:out value="${r.mesto}"/></td>
                            <td><c:out value="${r.vremeOdrzavanja}"/></td>
                            <td><c:out value="${r.tipKat}"/></td>
                            <td class="uskoTD"><c:out value="${r.cenaUl}"/></td>
                            <td class="uskoTD"><c:out value="${r.brojUl}"/></td>
                            <td class="uskoTD"><c:out value="${r.ukupnaCena}"/></td>
                            <td><c:out value="${r.vremeRez}"/></td>
                            <td class="uskoTD"><c:out value="${r.kupljena}"/></td>
                            <td class="uskoTD"><c:out value="${r.istekla}"/></td>
                            <td class="opcija">
                            <!-- ako REZ nije istekla ni kupljena korisnik moze da je ponisti-->
                            <c:if test="${r.istekla == false}">
                                <c:if test="${r.kupljena == false}">
                                        <form id="frmPonistiRez" name="frmPonistiRez" action="PonistiRezServlet" method="post">
                                            <input type="hidden" id="rezID" name="rezID" value="<c:out value='${r.ID}'/>">
                                            <input type="hidden" id="dogadjajID" name="dogadjajID" value="<c:out value='${r.dogadjajID}'/>">
                                            <input type="hidden" id="katID" name="katID" value="<c:out value='${r.katID}'/>">
                                            <input type="hidden" id="brojUl" name="brojUl" value="<c:out value='${r.brojUl}'/>">
                                            <button type="submit"id="dugmeTBL">Ponisti rezervaciju</button>
                                        </form>
                                </c:if>
                            </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
        <div id="futer" name="futer">
            <h3><pre>Predrag Popov NRT 110/12      jan.2015.</pre></h3>
        </div>
    </body>
</html>
