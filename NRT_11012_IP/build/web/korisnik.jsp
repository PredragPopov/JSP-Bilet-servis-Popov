

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KorisnikPocetna</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <style type="text/css">
            #divKP{
                text-align: center;
            }
            #kupi{
                background: url(images/kupi.png);
                background-repeat: no-repeat;
                background-size: 400px 300px;
                width: 500px;
                height: 350px;
                border: 0px;
            }
        </style>
    </head>
    <body>
        <div id="heder" name="heder">
            <h3>Projekat iz predmeta Internet Programiranje</h3>
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
            <a href="Profil.jsp" class="dugme"><button>Profil</button></a>
            <a href="promenaLozinke.jsp" class="dugme"><button>Promena lozinke</button></a>
            <a href="LogOutServlet" class="dugme"><button>LogOut</button></a>
            
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            <div id="divKP" name="divKP">
                <form id="frmK1" name="frmK1" action="listaDogadjajaServlet" method="post">
                <input type="hidden" id="tip" name="tip" value="1">
                <button type="submit" id="kupi"></button>
                </form>
            </div>
        </div>
        
        <div id="futer" name="futer">
            <h3><pre>Predrag Popov NRT 110/12      jan.2015.</pre></h3>
        </div>
    </body>
</html>
