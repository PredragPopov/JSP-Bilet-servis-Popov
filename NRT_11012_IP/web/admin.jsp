

<%@page import="klase.RadnoMesto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AdminPocetna</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <style type="text/css">
            #granica{
                width: 5px;
                background-color: rgb(205,33,34);
            }
            #leva{
                padding-right: 15px;
            }
            #desna{
                padding-left: 15px;
                text-align: center;
            }
            
        </style>
    </head>
    <body>
        <%
            HttpSession sesija=request.getSession(true);
            ArrayList<RadnoMesto> listaRM=new ArrayList<RadnoMesto>();
            listaRM=(ArrayList<RadnoMesto>)sesija.getAttribute("listaRadnaMesta");
            %>
        <div id="heder" name="heder">
            <h3>Projekat iz predmeta Internet Programiranje</h3>
            <h3>ADMINISTRATOR</h3>
            
            <a href="javascript: history.go(-1)" id="nazad" name="nazad"><button>Nazad</button></a>
            <a href="admin.jsp" id="nazad" name="nazad"><button>Pocetna</button></a>
            <a href="Profil.jsp" class="dugme"><button>Profil</button></a>
            <a href="promenaLozinke.jsp" class="dugme"><button>Promena lozinke</button></a>
            <a href="LogOutServlet" class="dugme"><button>LogOut</button></a>
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            <table>
                <tr>
                    <td id="leva" name="leva">
                        <form id="frmA3" name="frmA3" action="A_updateRegistracijeServlet" method="post">
                            
                            <button type="submit">NOVE REGISTRACIJE</button>
                        </form>
                        <br>
            <!-- dodaj korisnika  -->
            <h3>Dodaj korisnika:</h3>
            <form id="frmA1" name="frmA1" action="A_editKorisnikServlet" method="post">
                <input type="hidden" id="faktorZ" name="faktorZ" value="1">
                    
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
                    <label for="sifraMesta">Sifra radnog mesta</label>
                    <input type="text" id="sifraMesta" name="sifraMesta">
                    
                    <br>
                    <button type="submit" onclick="f1()">Dodaj</button>
            </form>
            <br><hr><br>
            <!-- obrisi korisnika -->
            <h3>Obrisi korisnika:</h3>
            <form id="frmA2" name="frmA2" action="A_editKorisnikServlet" method="post">
                <input type="hidden" id="faktorZ" name="faktorZ" value="2">
                    
                <label for="korisnicko_ime">Korisnicko ime</label>
                <input type="text" id="korisnicko_ime" name="korisnicko_ime">
                <br>
                <button type="submit" onclick="f2()">Obrisi</button>
            </form>
                    </td>
                    <td id="granica" name="granica"></td><!-- granica -->
                    <td id="desna" name="desna">
                        <!-- radno mesto dodaj/obrisi -->
            <h3>Radna mesta:</h3>
            <table>
                <thead>
                <th>Sifra mesta</th>
                <th>Mesto</th>
                <th>Adresa</th>
                <th>Grad</th>
                <th>Opcija</th>
                </thead>
                <tbody>
                    <% for(int i=0;i<listaRM.size();i++){%>
                    <tr>
                        <td><% out.println(listaRM.get(i).getSifraMesta());%></td>
                        <td><% out.println(listaRM.get(i).getMesto());%></td>
                        <td><% out.println(listaRM.get(i).getAdresa());%></td>
                        <td><% out.println(listaRM.get(i).getGrad());%></td>
                        <td>
                            <form id="frmA4" name="frmA4" action="A_editRadnoMestoServlet" method="post">
                                <input type="hidden" id="faktorZ" name="faktorZ" value="1">
                                <input type="hidden" id="rmID" name="rmID" value="<% out.println(listaRM.get(i).getSifraMesta());%>">
                                <button type="submit">Obrisi</button>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
                <br>
                <h3>Dodaj novo radno mesto:</h3>
            <form id="frmA4" name="frmA4" action="A_editRadnoMestoServlet" method="post">
                <input type="hidden" id="faktorZ" name="faktorZ" value="2">
                
                <input type="hidden" id="rmID" name="rmID">
                
                <label for="mesto">Mesto: </label>
                <input type="text" id="mesto" name="mesto">
                <br>
                <label for="adresa">Adresa: </label>
                <input type="text" id="adresa" name="adresa">
                <br>
                <label for="grad">Grad: </label>
                <input type="text" id="grad" name="grad">
                <br>
                <button type="submit" onclick="f3()">Dodaj</button>
            </form>
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
            
            function f1(){
                var x="";
                x+=proveraPrazno("korisnicko_ime");
                x+=proveraPrazno("lozinka");
                x+=proveraPrazno("lozinka2");
                x+=proveraLozinke("lozinka");
                x+=proveraLozinke("lozinka");
                x+=potvrdaLozinke("lozinka","lozinka2");
                x+=proveraPrazno("tel");
                x+=proveraPrazno("Eposta");
                x+=proveraPrazno("sifraMesta");
                x+=proveraTel();
                x+=proveriEPostu();
                if(x!==""){
                    alert(x);
                    location.reload();
                }
            }
            
            function f2(){
                var x="";
                x+=proveraPrazno("korisnicko_ime");
                
                
                if(x!==""){
                    alert(x);
                    location.reload();
                }
            }
            
            function f3(){
                var x="";
                x+=proveraPrazno("mesto");
                x+=proveraPrazno("adresa");
                x+=proveraPrazno("grad");
                if(x!==""){
                    alert(x);
                    location.reload();
                }
            }
        </script>
    </body>
</html>
