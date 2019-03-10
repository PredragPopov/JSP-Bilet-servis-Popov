
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RegistrujSe</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <style type="text/css">
            body{
                text-align: center;
            }
            
        </style>
    </head>
    <body>
        <div id="heder" name="heder">
            <h3>Projekat iz predmeta Internet Programiranje</h3>
            <h3>REGISTRACIJA</h3>
            <a href="javascript: history.go(-1)" id="nazad" name="nazad"><button>Nazad</button></a>
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            <h3>Popunite formu:</h3>
            <form name="frmRegistracija" id="femRegistracija" action="RegistracijaServlet" method="post">
                    <label for="korisnicko_ime">Korisnicko ime</label>
                    <input type="text" id="korisnicko_ime" name="korisnicko_ime">
                    <br>
                    
                    <label for="lozinka">Lozinka</label>
                    <input type="password" id="lozinka" name="lozinka">
                    <br>
                    <label for="lozinka2">Ponovi lozinku</label>
                    <input type="password" id="lozinka2" name="lozinka2">
                    <br>
                    
                    
                    
                    <label for="ime">Ime</label>
                    <input type="text" id="ime" name="ime">
                    <br>
                    
                    <label for="prezime">Prezime</label>
                    <input type="text" id="prezime" name="prezime">
                    <br>
                    
                    <label for="adresa">Ulica i grad</label>
                    <input type="text" id="adresa" name="adresa">
                    <br>
                    
                    <label for="tel">Kontakt telefon</label>
                    <input type="text" id="tel" name="tel">
                    <br>
                    
                    <label for="Eposta">E posta</label>
                    <input type="text" id="Eposta" name="Eposta">
                    <br>
                    <label for="tip">Tip korisnika</label>
                    <select id="tip" name="tip">
                        <option value="korisnik">Korisnik</option>
                        <option value="blagajnik">blagajnik</option>
                        <option value="admin">admin</option>
                    </select>
                    <br><br>
                    <button type="submit" name="regInOk" onclick="f()">Registruj me</button>
                </form>
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
            function proveraLozinke(id){
                var text="";
                var el2=document.getElementById(id);
                var y=el2.value;
                var n=y.length;
                if(n<5){
                    text="Lozinka mora da ima vise od 5 karaktera!!!\n";   
                }
                return text;
            }

            function potvrdaLozinke(id1,id2){
                var text="";
                var el1=document.getElementById(id1);
                var loz1=el1.value;
                
                var el2=document.getElementById(id2);
                var loz2=el2.value;
                
                //var n = str1.localeCompare(str2);
                var x=loz1.localeCompare(loz2);
                if(x===0){
                    
                }else{
                    text="lozinke se NE poklapaju!\n";
                    
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
                x+=proveraPrazno("lozinka");
                x+=proveraPrazno("lozinka2");
                x+=proveraLozinke("lozinka");
                x+=proveraLozinke("lozinka");
                x+=potvrdaLozinke("lozinka","lozinka2");
                x+=proveraPrazno("tel");
                x+=proveraPrazno("Eposta");
                x+=proveraTel();
                x+=proveriEPostu();
                if(x!==""){
                    alert(x);
                    location.reload();
                }
            }
        </script>
    </body>
</html>
