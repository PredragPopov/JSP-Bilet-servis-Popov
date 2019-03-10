<%@page import="java.util.ArrayList"%>
<%@page import="klase.Korisnik" %>
<%@page import="klase.Kategorija" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DodajDogadjaj</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <style type="text/css">
            #granica{
                width: 5px;
                background-color: rgb(205,33,34);
                
            }
            #leva{
                padding-right: 10px;
            }
            #desna{
                padding-left: 20px;
            }
        </style>
    </head>
    <body>
        <% 
            HttpSession sesija=request.getSession(true);
            Korisnik korisnik=(Korisnik) sesija.getAttribute("korisnik");
            int sifraMesta=korisnik.getSifraMesta();
            
            ArrayList<Kategorija> listaK=new ArrayList<Kategorija>();
            listaK=(ArrayList<Kategorija>) sesija.getAttribute("listaKat");
            
            String mesto=(String)sesija.getAttribute("mesto");
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
            
            <table>
                <tr>
                    <td id="leva" name="leva"> <!-- forma za novi dogadjaj -->
                        <h3>Popunite formu:</h3>
                        <form id="frmBdodajD" name="frmBdodajD" action="B_dodajDServlet" method="post">
                            <label for="naziv">Unesite naziv dogadjaja: </label>
                            <input type="text" id="naziv" name="naziv">
                            <br>
                
                            <input type="hidden" id="mesto" name="mesto" value="<%out.println(mesto);%>">
                            
                            <b>Vreme odrzavanja unesite u formatu: 2015-01-17 08:19:32</b><br>
                            <label for="vremeOdrzavanja">Vreme odrzavanja: </label>
                            <input type="text" id="vremeOdrzavanja" name="vremeOdrzavanja">
                            <br>
                            
                            <label for="opis">Opis: </label>
                            <input type="text" id="opis" name="opis">
                            <br>
                            <label for="datoteka">Datoteka</label>
                            <input type="text" id="datoteka" name="datoteka">
                            <br>
                            
                            <p>Kategorije:</p>
                            <% for(int i=0;i<listaK.size();i++){%>
                            <input type="checkbox" id="check" name="check" value="<% out.println(listaK.get(i).getID());%>"><% out.println(listaK.get(i).getTipSedista());%>
                                <br>
                            <%}%>
                            <br>
                            <button type="submit" onclick="f1()">Dodaj</button>
            </form>
                    </td>
                    <td id="granica" name="granica"></td> <!-- granica-->
                    
                    <td id="desna" name="desna"> <!-- prikaz postojecih kategorija-->
                        <table>
                            <thead>
                            <th>ID</th>
                            <th>Mesto</th>
                            <th>Tip sedista</th>
                            <th>Max ulaznica</th>
                            <th>Cena jedne ul</th>
                            </thead>
                            <tbody>
                                <% for(int i=0;i<listaK.size();i++){ %>
                                    <tr>
                                        <td><% out.println(listaK.get(i).getID());%></td>
                                        <td><% out.println(listaK.get(i).getMesto());%></td>
                                        <td><% out.println(listaK.get(i).getTipSedista());%></td>
                                        <td><% out.println(listaK.get(i).getMaxUl());%></td>
                                        <td><% out.println(listaK.get(i).getCenaUl());%></td>
                                    </tr>
                                <%}%>
                            </tbody>
                        </table>
                            <br><br>
                            <form id="BfrmK" action="B_dodajKatServlet" method="post">
                                <input type="hidden" id="noviKmesto" name="noviKmesto" value="<%out.println(mesto);%>">
                                
                                <label for="noviKtipS">Tip sedista: </label>
                                <input text="text" id="noviKtipS" name="noviKtipS">
                                <br>
                                <label for="noviKmaxUl">Max ulaznica: </label>
                                <input text="text" id="noviKmaxUl" name="noviKmaxUl">
                                <br>
                                <label for="noviKcenaUl">Cena jedne: </label>
                                <input text="text" id="noviKcenaUl" name="noviKcenaUl">
                                <br>
                                <button type="submit" onclick="f2()">Dodaj kategoriju</button>
                            </form>            
                        <!--
                            <a href="B_dodajKatServlet"><button>Dodaj kategorije</button></a>
                        -->
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

            function f1(){
                var x="";
                x+=proveraPrazno("naziv");
                x+=proveraPrazno("vremeOdrzavanja");
                //x+=proveraLozinke("lozinka");
                if(x!==""){
                    alert(x);
                    location.reload();
                }
            }
            function daLiJeBroj(id){
		var text="";
                var el=document.getElementById(id);
                var x=el.value;
                var rez=isNaN(x);
                if(rez===true){
                    text="morate uneti broj!!!\n";
                    
                }
		return text;
            }
            
            function f2(){
                var x="";
                x+=proveraPrazno("noviKtipS");
                x+=proveraPrazno("noviKmaxUl");
                x+=proveraPrazno("noviKcenaUl");
                x+=daLiJeBroj("noviKmaxUl");
                x+=daLiJeBroj("noviKcenaUl");
                if(x!==""){
                    alert(x);
                    location.reload();
                }
            }
        </script>
    </body>
</html>
