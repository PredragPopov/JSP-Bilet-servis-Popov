
<%@page import="java.util.ArrayList"%>
<%@page import="klase.Korisnik" %>
<%@page import="klase.Dogadjaj" %>
<%@page import="klase.Kategorija" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rezervacija</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <style type="text/css">
            #frmRPIDkat{
                visibility: hidden;
            }
            #dogadjajID{
                visibility: hidden;
            }
        </style>
    </head>
    <body>
        <% 
            HttpSession sesija=request.getSession(true);
            
            String username=request.getParameter("korisnik");
            
            Dogadjaj d=(Dogadjaj)sesija.getAttribute("ovajD");
            
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
            <h3>REZERVACIJA</h3>
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
            <h3>Vasa rezervacija: </h3>
                <form id="frmRezPotvrda" name="frmRezPotvrda" action="RezervisiServlet" method="post">
                    <label for="frmRPnazivD">Rezervacija za dogadjaj: </label>
                    <input type="text" id="frmRPnazivD" name="frmRPnazivD"   value="<%out.println(d.getNaziv()); %>" readonly="true">
                    <br>
                    <label for="frmRPmesto">Mesto odrzavanja: </label>
                    <input type="text" id="frmRPmesto" name="frmRPmesto"   value="<%out.println(d.getMesto()); %>" readonly="true">
                    <br>
                    <label for="frmRPvreme">Vreme odrzavanja: </label>
                    <input type="text" id="frmRPvreme" name="frmRPvreme"   value="<%out.println(d.getVremeOdrzavanja()); %>" readonly="true">
                    <br>
                    <label for="frmRPopis">Opis: </label>
                    <input type="text" id="frmRPopis" name="frmRPopis"   value="<%out.println(d.getOpis()); %>" readonly="true">
                    <br>
                    <label for="frmRPtipKat">Tip sedista:</label>
                    <input type="text" id="frmRPtipKat" name="frmRPtipKat"   value="<%out.println(tipSedista); %>" readonly="true">
                    <br>
                    <label for="frmRPbrUlaznica">Broj ulaznica: </label>
                    <input type="text" id="frmRPbrUlaznica" name="frmRPbrUlaznica"   value="<%out.println(brUlS); %>" readonly="true">
                    <br>
                    <label for="frmRPukupnaCena">Ukupna cena: </label>
                    <input type="text" id="frmRPukupnaCena" name="frmRPukupnaCena"   value="<%out.println(ukupnaCena); %>" readonly="true">
                    <br>
                    <label for="frmRPkorisnik">Rezervisao korisnik: </label>
                    <input type="text" id="frmRPkorisnik" name="frmRPkorisnik"   value="<%out.println(username); %>" readonly="true">
                    <br>
                    <input type="text" id="frmRPIDkat" name="frmRPIDkat"   value="<%out.println(katIDint); %>">
                    <input type="text" id="dogadjajID" name="dogadjajID"   value="<%out.println(d.getID()); %>">
                    <br>
                    <button type="submit">Potvrdi</button>
                    <br>
                    
                </form>
                
        </div>
        
        <div id="futer" name="futer">
            <h3><pre>Predrag Popov NRT 110/12      jan.2015.</pre></h3>
        </div>
    </body>
</html>
