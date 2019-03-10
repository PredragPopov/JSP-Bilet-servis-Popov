
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pocetna</title>
        <link rel="stylesheet" type="text/css" href="css.css">
    </head>
    <body>
        
        <div id="heder" name="heder">
            <h1>Projekat iz predmeta Internet Programiranje</h1>
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            <form id="frmLogIn" name="frmLogIn" action="LogInServlet" method="post">
                <label for="korisnickoIme">Unesite korisnicko ime</label>
                <input type="text" id="korisnickoIme" name="korisnickoIme">
                <br>
                <label for="lozinka">Unesite lozinku</label>
                <input type="password" id="lozinka" name="lozinka">
                <br>
                <button type="submit" onclick="f()">Uloguj me</button>
            </form>
            <br><hr><br>
            <form id="frmGost" name="frmGost" action="listaDogadjajaServlet" method="post">
                <input type="hidden" id="tip" name="tip" value="0">
                <button type="submit"> GOST </button>
            </form>
            <br><hr><br>
            
            <a href="registrujSe.jsp"><button>Registruj se</button></a>
            
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
            
            function f(){
                var x="";
                x+=proveraPrazno("korisnickoIme");
                x+=proveraPrazno("lozinka");
                x+=proveraLozinke("lozinka");
                if(x!==""){
                    alert(x);
                    location.reload();
                }
            }
        </script>
    </body>
</html>
