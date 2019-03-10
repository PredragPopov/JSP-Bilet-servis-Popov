<%-- 
    Document   : promenaLozinke
    Created on : Jan 31, 2015, 2:44:58 AM
    Author     : POPOV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PromenaLozinke</title>
        <link rel="stylesheet" type="text/css" href="css.css">
    </head>
    <body>
        <div id="heder" name="heder">
            <h3>Projekat iz predmeta Internet Programiranje</h3>
            <h3>Promena lozinke</h3>
            <a href="javascript: history.go(-1)" id="nazad" name="nazad"><button>Nazad</button></a>
            <a href="LogOutServlet" class="dugme"><button>LogOut</button></a>
            
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            <form id="formaPromenaLozinke" name="formaPromenaLozinke" action="PromenaLozinkeServlet" method="post">
                <label for="staraLozinka">Unesite staru lozinku</label>
                <input type="password" id="staraLozinka" name="staraLozinka">
                <br>
                <label for="novaLozinka">Unesite novu lozinku</label>
                <input type="password" id="novaLozinka" name="novaLozinka">
                <br>
                <label for="novaLozinka2">Potvrdi novu lozinku</label>
                <input type="password" id="novaLozinka2" name="novaLozink2a">
                <br>
                <button type="submit" onclick="f()">Promeni</button>
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
            
            function f(){
                var x="";
                x+=proveraPrazno("staraLozinka");
                x+=proveraPrazno("novaLozinka");
                x+=proveraPrazno("novaLozinka2");
                x+=proveraLozinke("novaLozinka");
                x+=proveraLozinke("novaLozinka2");
                x+=potvrdaLozinke("novaLozinka","novaLozinka2");
                if(x!==""){
                    alert(x);
                    location.reload();
                }
            }
        </script>
    </body>
</html>
