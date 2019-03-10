<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page import="java.util.ArrayList"%>
        <%@ page import="klase.Registracija"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AdminRegistracije</title>
         <link rel="stylesheet" type="text/css" href="css.css">
    </head>
    <body>
        <div id="heder" name="heder">
            <h3>Projekat iz predmeta Internet Programiranje</h3>
            <h3>ADMIN</h3>
            <a href="admin.jsp" id="nazad" name="nazad"><button>Pocetna</button></a>
            <a href="javascript: history.go(-1)" id="nazad" name="nazad"><button>Nazad</button></a>
            <a href="Profil.jsp" class="dugme"><button>Profil</button></a>
            <a href="promenaLozinke" class="dugme"><button>Promena lozinke</button></a>
            <a href="LogOutServlet" class="dugme"><button>LogOut</button></a>
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            <h3>Lista registracija</h3>
            <ul>
                <c:forEach var="r" items="${listaReg}">
                    <li>
                        <b>Korisnicko ime  </b><c:out value="${r.korisnickoIme}"/>
                        <br>
                        <b>Lozinka  </b><c:out value="${r.lozinka}"/>
                        <br>
                        <b>Tip korisnika  </b><c:out value="${r.tip}"/>
                        <br>
                        <b>Adresa  </b><c:out value="${r.adresa}"/>
                        <br>
                        <b>Ime  </b><c:out value="${r.ime}"/>
                        <br>
                        <b>Prezime  </b><c:out value="${r.prezime}"/>
                        <br>
                        <b>Telefon  </b><c:out value="${r.telefon}"/>
                        <br>
                        <b>ePosta  </b><c:out value="${r.ePosta}"/>
                        <br>
                        <form id="Afrm3" name="Afrm3" action="A_reg2Servlet" method="post">
                            <input type="hidden" id="korisnickoIme" name="korisnickoIme" value="${r.korisnickoIme}">
                            <input type="hidden" id="lozinka" name="lozinka" value="${r.lozinka}">
                            <input type="hidden" id="tip" name="tip" value="${r.tip}">
                            <label for="sifraMesta">Radno mesto: </label>
                            <select id="sifraMesta" name="sifraMesta">
                                    <option value="0">Korisnik</option>
                                    <option value="1">Pionir</option>
                                    <option value="2">Fudbalski stadion Partizan</option>
                                    <option value="3">Dom kulture</option>
                                    <option value="4">SavaCentar</option>
                                    <option value="5">Arena</option>
                            </select>
                            <input type="hidden" id="ime" name="ime" value="${r.ime}">
                            <input type="hidden" id="prezime" name="prezime" value="${r.prezime}">
                            <input type="hidden" id="adresa" name="adresa" value="${r.adresa}">
                            <input type="hidden" id="telefon" name="telefon" value="${r.telefon}">
                            <input type="hidden" id="ePosta" name="ePosta" value="${r.ePosta}">
                            <br><br>
                            <label for="izbor">Zelim da:</label>
                            <select id="izbor" name="izbor">
                                    <option value="1">Prihvatim korisnika</option>
                                    <option value="0">Odbijem korisnika</option>
                            </select>
                            <br><br>
                            <button type="submit">Potvrdi</button>
                        </form>
                            <br>
                        <hr>
                    </li>
                </c:forEach>
            </ul>
        </div>
        
        <div id="futer" name="futer">
            <h3><pre>Predrag Popov NRT 110/12      jan.2015.</pre></h3>
        </div>
    </body>
</html>
