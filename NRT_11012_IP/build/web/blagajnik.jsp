
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BlagajnikPocetna</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <style type="text/css">
            #sadrzaj{
                text-align: center;
            }
            #divMeni{
                text-align: center;
               
            }
            table{
                text-align: center;
            }
            tr{
                height: 80px;
                
            }
            
            .btnMeni{
                
                background-color: black;
                border: 2px solid rgb(205,33,34);
                padding: 20px;
                font-size: large;
            }
            .tabeleMeni{
                text-align: center;
                margin-left: 28%;
            }
            .desni{
                padding-left: 30%;
            }
            .naslov{
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div id="heder" name="heder">
            <h3>Projekat iz predmeta Internet Programiranje</h3>
            <h3>BLAGAJNIK</h3>
            
            <a href="Profil.jsp" class="dugme"><button>Profil</button></a>
            <a href="promenaLozinke" class="dugme"><button>Promena lozinke</button></a>
            <a href="LogOutServlet" class="dugme"><button>LogOut</button></a>
        </div>
        
        <div id="sadrzaj" name="sadrzaj">
            <div id="divMeni">
                <h3 class="naslov">Prodaja ulaznica:</h3>
                <table id="tblMeniUlaznice" name="tblMeniUlaznice" class="tabeleMeni">
                    <tr>
                        <td> <!--izlistava dogadjaje sa opcijom prodaj -->
                            <form id="frmB1" name="frmB1" action="listaDogadjajaServlet" method="post">
                                <input type="hidden" id="tip" name="tip" value="2">
                                <button type="submit" id="direktno" class="btnMeni">DIREKTNO</button>
                            </form>
                        </td>
                        <td><!--pronalazi rezervaciju po unetom ID -->
                            <form id="frmRez" name="frmRez" action="PrikaziRezServlet" method="post">
                                <input type="hidden" id="faktorX" name="faktorX" value="2">
                                <label for="rezID">Unesite ID rezervacije</label>
                                <input type="text" id="rezID" name="rezID" >
                                <br>
                                <button type="submit" class="btnMeni" onclick="f()">Pronadji rezervaciju</button>
                            </form>
                        </td>
                    </tr>
                </table>
                <hr>
                <h3 class="naslov">Dogadjaj:</h3>
                <table id="tblMeniD" name="tblMeniD" class="tabeleMeni">
                    <tr>
                        <td><!-- dodavanje novog dogadjaja-->
                            <a href="B_citajKategorijeServlet"><button class="btnMeni">DODAJ NOVI</button></a>
                        </td>
                        <td class="desni"><!-- opcija za edit i delete dogadjaj -->
                            <form id="frmK1" name="frmK1" action="listaDogadjajaServlet" method="post">
                                <input type="hidden" id="tip" name="tip" value="3">
                                <button type="submit" class="btnMeni">IZMENI</button>
                            </form>
                        </td>
                    </tr>
                </table>
                <hr>
                <h3 class="naslov">UPDATE DATABASE:</h3>
                <table id="tblMeniU" name="tblMeniU" class="tabeleMeni">
                    <tr>
                        <td><!-- radi update baze podataka za dogadjaje  -->
                            <form id="frmUpdate1" name="frmUpdate1" action="B_updateDogadjajServlet" method="post">
                                <button type="submit" class="btnMeni">UPDATE DOGADjAJE</button>
                            </form>
                        </td>
                        <td class="desni"><!-- radi update baze podataka za rezervacije -->
                            <form id="frmUpdate2" name="frmUpdate2" action="B_updateRezervacijeServlet" method="post">
                                <button type="submit" class="btnMeni">UPDATE REZERVACIJE</button>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
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
            
            function f(){
                var x="";
                x+=proveraPrazno("rezID");
                x+=daLiJeBroj("rezID");
                
                if(x!==""){
                    alert(x);
                    location.reload();
                }
            }
        </script>
    </body>
</html>
