
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="klase.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>odabranDogadjaj</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <style type="text/css">
            #btnRed{
                background-color: red;
                color:ghostwhite;
                padding: 5px;
            }
        </style>
    </head>
    <body>
        <%
            HttpSession sesija=request.getSession(true);
            
            String IDS=request.getParameter("dogadjajID");
            int ID=Integer.parseInt(IDS.trim());
            
            String tipS=request.getParameter("faktorX");
            int tip=Integer.parseInt(tipS.trim());
            
            
            ArrayList<Dogadjaj> listaD=new ArrayList<Dogadjaj>();
            listaD=(ArrayList<Dogadjaj>)sesija.getAttribute("lista_dogadjaja");
            
            //iz listeDogadjaja uzimam samo izabran dogadjaj uporedjujuci ID
            Dogadjaj ovajD=new Dogadjaj();
            for(int i=0;i<listaD.size();i++){
                int idX=listaD.get(i).getID();
                if(idX==ID){
                    ovajD=listaD.get(i);
                }
            }
            
            int preostale=ovajD.getPreostale();
            boolean moze=ovajD.isMogucaRez();
            
            ArrayList<Kategorija> listaKat=new ArrayList<Kategorija>();
            listaKat=ovajD.getListaKat();
            String slika="images/"+ovajD.getDatoteka();
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

            <%if(tip==2){%>
            <b>Naziv dogadjaja:   </b><% out.println(ovajD.getNaziv());%><br>
            <b>Mesto odrzavanja:   </b><% out.println(ovajD.getMesto());%><br>
            <b>Vreme odrzavanja:   </b><% out.println(ovajD.getVremeOdrzavanja());%><br>
            <b>Opis dogadjaja:   </b><% out.println(ovajD.getOpis());%><br>
            <br>
            <img src="<% out.println(slika);%>"  style="width:304px;height:228px">
            <br>
            <b>Preostale karte:   </b><% out.println(ovajD.getPreostale());%><br>
            <b>Moguca rezervacija:   </b><% out.println(ovajD.isMogucaRez());%><br>
            <b>Istekao:   </b><% out.println(ovajD.isIstekao());%><br>
                <% if(preostale>0 && moze==true){ %>
                <form id="frmRez" name="frmRez" action="B_prodajaPotvrda.jsp" method="post">
                
                    <% sesija.setAttribute("ovajD",ovajD);%>
                
                    <b>Lista tip sedista [cena jedna karte]</b>
                    <select id="selectTipKat" name="selectTipKat">
                        <%for(int i=0;i<listaKat.size();i++){
                            int KatID=listaKat.get(i).getID();
                            String KatTipS=listaKat.get(i).getTipSedista();
                            int KatCena=listaKat.get(i).getCenaUl();

                            int max=listaKat.get(i).getMaxUl();
                            int pro=listaKat.get(i).getProdate();
                            int rez=listaKat.get(i).getRezervisane();
                            int pre=max-(pro+rez);
                            if(pre>0){%>
                                <option value="<%out.println(KatID+"/"+KatCena+"/"+KatTipS);%>"><%out.println(KatTipS);%>[<%out.println(KatCena);%>]</option>
                            <%}%>
                        <%}%>
                    
                    
                    </select>
                
                    <br>
                    <label for="brUl">Unesite broj ulaznica:</label>
                    <input type="text" id="brUl" name="brUl">
                    <br>
                    <button type="submit" onclick="f()">Prodaj</button>
                </form>
                <%} else{%>
                <h3>Nije moguca prodaja karata!</h3>
                <%}%>
            <%} else if(tip==3){%>
                            
                <form id="frmIzmeni" name="frmIzmeni" action="B_izmeniDogadjajServlet" method="post">
                    <input type="hidden" id="faktorY" name="faktorY" value="1">
                    <label for="dID">ID: </label>
                    <input type="text" id="dID" name="dID" value="<% out.println(ovajD.getID());%>">
                    <br>
                    <label for="dnaziv">naziv </label>
                    <input type="text" id="dnaziv" name="dnaziv" value="<% out.println(ovajD.getNaziv());%>">
                    <br>
                    <label for="dmesto">mesto: </label>
                    <input type="text" id="dmesto" name="dmesto" value="<% out.println(ovajD.getMesto());%>">
                    <br>
                    <label for="dvremeOdrzavanja">vremeOdrzavanja: </label>
                    <input type="text" id="dvremeOdrzavanja" name="dvremeOdrzavanja" value="<% out.println(ovajD.getVremeOdrzavanja());%>">
                    <br>
                    <label for="dopis">opis: </label>
                    <input type="text" id="dopis" name="dopis" value="<% out.println(ovajD.getOpis());%>">
                    <br>
                    <label for="dID">datoteka: </label>
                    <input type="text" id="ddatoteka" name="ddatoteka" value="<% out.println(ovajD.getDatoteka());%>">
                    <br>
                    
                    <label for="distekao">istekao [true/false]: </label>
                    <input type="text" id="distekao" name="distekao" value="<% out.println(ovajD.isIstekao());%>">
                    <br>
                    <button type="submit">Izmeni</button>
                </form>
                <br><br>
                <form id="frmIzmeni" name="frmIzmeni" action="B_izmeniDogadjajServlet" method="post">
                    <input type="hidden" id="faktorY" name="faktorY" value="2">
                    <input type="hidden" id="dID" name="dID" value="<% out.println(ovajD.getID());%>">
                    <button type="submit" id="btnRed">Obrisi</button>
                </form>
               
            <%}%>
            <a href="javascript: history.go(-1)" id="nazad" name="nazad"><button>Nazad</button></a>

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
            function brojUl(id){
                var text="";
                var el=document.getElementById(id);
                var x=el.value;
                
                if(x>6){
                    text="Ne mozete rezervisati vise od 6 ulaznica!!!\n";
                    
                }
                return text;
            }
            function f(){
                var x="";
                
                x+=proveraPrazno("brUl");
                x+=daLiJeBroj("brUl");
                x+=brojUl("brUl");
                if(x!==""){
                    alert(x);
                    location.reload();
                }
            }
        </script>
    </body>
</html>
