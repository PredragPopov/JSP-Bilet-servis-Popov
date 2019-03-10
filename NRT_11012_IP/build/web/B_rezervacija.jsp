
<%@page import="klase.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rezervacija</title>
         <link rel="stylesheet" type="text/css" href="css.css">
    </head>
    <body>
        <% 
            HttpSession sesija=request.getSession(true);
            //Dogadjaj d=(Dogadjaj)sesija.getAttribute("ovajD");
            Rezervacija r=(Rezervacija)sesija.getAttribute("rezervacija");
            
            int x=r.getID();
            boolean kupljena=r.isKupljena();
            boolean istekla=r.isIstekla();
            
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
            
            <%if(x!=0){%>
            
            <%if(kupljena==false){%>
                <%if(istekla==false){%>
                    <h3>Trazena rezervacija: </h3>
                    <b>Rezervisao korisnik: </b><%out.println(r.getKorisnik());%>
                    <br>
                    <b>Za dogadjaj: </b><%out.println(r.getDogadjajNaziv());%>
                    <br>
                    <b>Mesto odrzavanja: </b><%out.println(r.getMesto());%>
                    <br>
                    <b>Vreme odrzavanja: </b><%out.println(r.getVremeOdrzavanja());%>
                    <br>
                    <b>Kategorija sedista: </b><%out.println(r.getTipKat());%>
                    <br>
                    <b>Cena jedne karte: </b><%out.println(r.getCenaUl());%>
                    <br>
                    <b>Broj ulaznica: </b><%out.println(r.getBrojUl());%>
                    <br>
                    <b>Ukupna cena: </b><%out.println(r.getUkupnaCena());%>
                    <br>
                    <b>Vreme rezervacije: </b><%out.println(r.getVremeRez());%>
                    <br>
                    
                    <form id="frmBpotvrdaProdajeRez" name="frmBpotvrdaProdajeRez" action="B_prodajRezServlet" method="post">
                        <input type="hidden" id="rezID" name="rezID" value="<%out.println(r.getID());%>">
                        <input type="hidden" id="dogadjajID" name="dogadjajID" value="<%out.println(r.getDogadjajID());%>">
                        <input type="hidden" id="katID" name="katID" value="<%out.println(r.getKatID());%>">
                        <input type="hidden" id="brUl" name="brUl" value="<%out.println(r.getBrojUl());%>">
                        <button type="submit">Prodaj rezervaciju</button>
                    </form>
                    
                    
                <%}else{%>
                    <h3>Vasa rezervacija je istekla!</h3>
                <%}%>
            <%}else{%>
                <h3>Vasa rezervacija je vec kupljena!</h3>
            <%}%>
            
            <%}else{%>
                <h3>Ne postoji rezervacija sa unetom sifrom!!!</h3>
            <%}%>
            
            
                

        </div>
        
        <div id="futer" name="futer">
            <h3><pre>Predrag Popov NRT 110/12      jan.2015.</pre></h3>
        </div>
    </body>
</html>
