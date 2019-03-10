
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>

<%@page import="klase.Korisnik" %>
<%@page import="klase.Dogadjaj" %>
<%@page import="klase.Kategorija" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OdabranDogadjaj</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <style type="text.css">
            #sadrzaj{
                
                padding-left: 300px;
            }
        </style>
    </head>
    <body>
        <% 
            HttpSession sesija=request.getSession(true);
            Korisnik k=(Korisnik) sesija.getAttribute("korisnik2");
            
            String username=k.getKorisnickoIme();
            String name=k.getIme();
            int sifraMesta=k.getSifraMesta();
            
            //lista kategorija "listaKatZaDogadjaj"
            
            //citanje podataka iz forme sa request strane jsp
            String IDS=request.getParameter("dogadjajID");
            int ID=Integer.parseInt(IDS.trim());
            String korisnik=request.getParameter("korisnik");
            
            //lista dogadjaja iz sesije
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
            //ArrayList<Kategorija> listaKat2=new ArrayList<Kategorija>();
            
            String slika="images/"+ovajD.getDatoteka();
        %>
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
            <a href="promenaLozinke" class="dugme"><button>Promena lozinke</button></a>
            <a href="LogOutServlet" class="dugme"><button>LogOut</button></a>
        </div>
        
        <div id="sadrzaj" name="sadrzaj">

            <b>Naziv dogadjaja:   </b><% out.println(ovajD.getNaziv());%><br>
            <b>Mesto odrzavanja:   </b><% out.println(ovajD.getMesto());%><br>
            <b>Vreme odrzavanja:   </b><% out.println(ovajD.getVremeOdrzavanja());%><br>
            <b>Opis dogadjaja:   </b><% out.println(ovajD.getOpis());%><br>
           <br>
            <img src="<% out.println(slika);%>"  style="width:304px;height:228px">
            <br><br>
            
            <% if(preostale>0 && moze==true){ %>
            <form id="frmRez" name="frmRez" action="rezervacija.jsp" method="post">
                <input type="hidden" id="korisnik" name="korisnik" value="<%out.println(username);%>">
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
                <button type="submit" onclick="f()">Rezervisi</button>
            </form>
            <%} else{%>
            <h3>Nije moguca rezervacija karata!</h3><br>
            <%}%>
            <a href="javascript: history.go(-1)" id="nazad" name="nazad"><button>Nazad</button></a><br>
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
