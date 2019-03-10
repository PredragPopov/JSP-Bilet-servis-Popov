
<%@ page import="klase.Korisnik"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css">
        <title>Profil</title>
        <style type="text/css">
            #sadrzaj{
                text-align: center;
            }
            #tbl1{
                padding-left: 15%;
            }
            #podaci{
                padding-top: 20px;
            }
            #slika{
                width: 256px;
                height: 256px;
                background: url(images/userProfil.png);
                background-repeat: no-repeat;
                background-size: 256px 256px;
                padding-top: 0px;
            }
        </style>
    </head>
    <body>
        <% 
            HttpSession sesija=request.getSession(true);
            Korisnik k=(Korisnik) sesija.getAttribute("korisnik");
           
        %>
        <div id="heder" name="heder">
            <h3>Projekat iz predmeta Internet Programiranje</h3>
            <h3>Moj profil</h3>
            <a href="javascript: history.go(-1)" id="nazad" name="nazad"><button>Nazad</button></a>
            <a href="promenaLozinke" class="dugme"><button>Promena lozinke</button></a>
            <a href="LogOutServlet" class="dugme"><button>LogOut</button></a>
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            <table id="tbl1" name="tbl1">
                <tr>
                    <td>
                        <div id="podaci" name="podaci">
                            <h3>Vas profil:</h3>
                <form id="frmProfil" name="frmProfil" action="EditKorisnikServlet" method="post">
                    <label for="korisnicko_ime">Korisnicko ime</label>
                    <input type="text" id="korisnicko_ime" name="korisnicko_ime" value="<%out.println(k.getKorisnickoIme());%>" readonly="true">
                    <br>
                    
                    <label for="ime">Ime</label>
                    <input type="text" id="ime" name="ime" value="<%out.println(k.getIme());%>">
                    <br>
                    
                    <label for="prezime">Prezime</label>
                    <input type="text" id="prezime" name="prezime" value="<%out.println(k.getPrezime());%>">
                    <br>
                    
                    <label for="adresa">Adresa</label>
                    <input type="text" id="adresa" name="adresa" value="<%out.println(k.getAdresa());%>">
                    <br>
                    
                    <label for="tel">Kontakt telefon</label>
                    <input type="text" id="tel" name="tel" value="<%out.println(k.getTelefon());%>">
                    <br>
                    
                    <label for="Eposta">E posta</label>
                    <input type="text" id="Eposta" name="Eposta" value="<%out.println(k.getePosta());%>">
                    
                    <input type="hidden" id="faktorX" name="faktorX" value="12">
                    <br>
                    <br>
                    <br>
                    <h4>Ako zelite da promenite podatke, izmenite sadrzaj i kliknite na:</h4>
                    <button type="submit" name="regInOk" onclick="f()">Promeni</button>
                </form>
            </div>
                    </td>
                    <td>
                        <div id="slika" name="slika"></div>
                    </td>
                </tr>
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
                    text="Niste popunili polje: "+id+"\n";  
                }
                return text;
            }
            function proveraTel(){
                var text="";
                var el=document.getElementById("tel");
                var tel=el.value;
                var x=tel.length;
                if(x>10){
                    text="telefon NE SME da ima vise od 10 brojeva!\n";
                }
                return text;
            }
            function proveriEPostu(){
                var text="";
                var el=document.getElementById("Eposta");
                var mail=el.value;
                
                filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                if (filter.test(mail)) {
                
                } else{
                    text="MAIL JE LOS!\n";
                    
                }
                return text;
            }
            
             function f(){
                var x="";
                x+=proveraPrazno("korisnicko_ime");
                x+=proveraPrazno("ime");
                x+=proveraLozinke("prezime");
                x+=proveraPrazno("tel");
                x+=proveraLozinke("Eposta");
                x+=proveraTel();
                x+=proveriEPostu());
                if(x!==""){
                    alert(x);
                    location.reload();
                }
            }           
        </script>
    </body>
</html>
