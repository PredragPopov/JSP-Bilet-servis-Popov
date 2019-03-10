
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import klase.*;
/**
 *
 * @author POPOV
 */
public class B_citajKategorijeServlet extends HttpServlet {

    String odgovor="<h3>nista</h3>";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet B_citajKategorijeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet B_citajKategorijeServlet at " + request.getContextPath() + "</h1>");
            out.println(odgovor);
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession sesija=request.getSession(true);

        Korisnik korisnik=(Korisnik) sesija.getAttribute("korisnik");
        int sifraMesta=korisnik.getSifraMesta();

        ArrayList<Kategorija> listaKat=new ArrayList<Kategorija>();
        
        //prvo za sifruMesta blagajnika citam iz DB koje je to mesto
        String mesto="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
            Statement upit=con.createStatement();
            ResultSet rez=upit.executeQuery("SELECT * FROM radnomesto WHERE sifraMesta="+sifraMesta+"");
            
            while(rez.next()){
                mesto=rez.getString("mesto");
                odgovor="<p>uzeo sam mesto"+mesto+"</p>";

            }
        }catch(SQLException e){
            //out.println("Greska: " + e);
            sesija.setAttribute("greska",e.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("greske.jsp");
            dispatcher.forward(request, response);
        }catch(Exception e){
            //out.println("Greska: " + e);
            sesija.setAttribute("greska",e.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("greske.jsp");
            dispatcher.forward(request, response);
        }
        
        //za to "mesto" citam kategorije
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/ipdb", "root", "");
            Statement upit2=con2.createStatement();
            ResultSet rez2=upit2.executeQuery("SELECT * FROM kategorije WHERE mesto='"+mesto+"'");
            
            while(rez2.next()){
                int ID=rez2.getInt("ID");
                String tipSedista=rez2.getString("tipSedista");
                int maxUl=rez2.getInt("maxUl");
                int cenaUl=rez2.getInt("cenaUl");
                
                Kategorija k=new Kategorija();
                k.setID(ID);
                k.setMesto(mesto);
                k.setTipSedista(tipSedista);
                k.setMaxUl(maxUl);
                k.setCenaUl(cenaUl);
                odgovor+="<br><p>procitao kategoriju: "+k+"</p>";
                listaKat.add(k);

            }
        }catch(SQLException e){
            //out.println("Greska: " + e);
            sesija.setAttribute("greska",e.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("greske.jsp");
            dispatcher.forward(request, response);
        }catch(Exception e){
            //out.println("Greska: " + e);
            sesija.setAttribute("greska",e.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("greske.jsp");
            dispatcher.forward(request, response);
        }
        odgovor+="<hr><p>sesija mesto "+mesto+"</p>";
        odgovor+="<hr><p>sesija lista kategorija "+listaKat+"</p>";
        //prosledjujem mesto u sesiju
        sesija.setAttribute("mesto",mesto);
        //prosledjujem listu kategorija u sesiju
        sesija.setAttribute("listaKat", listaKat);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("B_dodajDogadjaj.jsp");
        dispatcher.forward(request, response);
        
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
