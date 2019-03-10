<%@page import="java.util.ArrayList"%>
<%@page import="klase.Korisnik" %>
<%@page import="klase.Dogadjaj" %>
<%@page import="klase.Kategorija" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PotvrdaProdaje</title>
        <link rel="stylesheet" type="text/css" href="css.css">
    </head>
    <body>
        <%
            HttpSession sesija=request.getSession(true);
            //uzimam dogadjaj iz sesije
            Dogadjaj d=(Dogadjaj)sesija.getAttribute("ovajD");
            
            //iz forme uzimam string koji sadrzi IDkategorija
            String katIDS=request.getParameter("selectTipKat");
            //value="out.println(KatID+"/"+KatCena);" 
            String[] nizS=katIDS.split("/");
            String katID=nizS[0];
            int katIDint=Integer.parseInt(katID.trim());
            String katCena=nizS[1];
            String tipSedista=nizS[2];
            int cenaUl=Integer.parseInt(katCena.trim());
            
            String brUlS=request.getParameter("brUl");
            int brUl=Integer.parseInt(brUlS);
            
            int ukupnaCena=cenaUl*brUl;
            
            %>
        <div id="heder" name="heder">
            <h3>Projekat iz predmeta Internet Programiranje</h3>
            <h3>BLAGAJNIK</h3>
            <a href="blagajnik.jsp" id="nazad" name="nazad"><button>Pocetna</button></a>
            <a href="javascript: history.go(-1)" id="nazad" name="nazad"><button>Nazad</button></a>
            <a href="Profil.jsp" class="dugme"><button>Profil</button></a>
            <a href="promenaLozinke" class="dugme"><button>Promena lozinke</button></a>
            <a href="LogOutServlet" class="dugme"><button>LogOut</button></a>
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            <h3>Kupovina: </h3>
            <b>Ulaznice za dogadjaj: </b><%out.println(d.getNaziv()); %>
            <br>
            <b>Mesto odrzavanja: </b><%out.println(d.getMesto()); %>
            <br>
            <b>Vreme odrzavanja: </b><%out.println(d.getVremeOdrzavanja()); %>
            <br>
            <b>Opis: </b><%out.println(d.getOpis()); %>
            <br>
            <b>Tip sedista: </b><%out.println(tipSedista); %>
            <br>
            <b>Broj ulaznica: </b><%out.println(brUlS); %>
            <br>
            <b>Ukupna cena: </b><%out.println(ukupnaCena); %>
            <br>
            
                <form id="frmBpotvrdaProdaje" name="frmBpotvrdaProdaje" action="B_prodajaUlServlet" method="post">
                    
                    <input type="hidden" id="dogadjajID" name="dogadjajID"   value="<%out.println(d.getID()); %>">
                    <input type="hidden" id="IDkat" name="IDkat"   value="<%out.println(katIDint); %>">
                    <input type="hidden" id="brUl" name="brUl"   value="<%out.println(brUlS); %>">
                    <button type="submit">Potvrdi prodaju</button>
                    
                    
                </form>
        </div>
        
        <div id="futer" name="futer">
            <h3><pre>Predrag Popov NRT 110/12      jan.2015.</pre></h3>
        </div>
    </body>
</html>
