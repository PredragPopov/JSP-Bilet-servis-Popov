package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class POCETNA_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Pocetna</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        <div id=\"heder\" name=\"heder\">\n");
      out.write("            <h1>Projekat iz predmeta Internet Programiranje</h1>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div id=\"sadrzaj\" name=\"sadrzaj\">\n");
      out.write("            <form id=\"frmLogIn\" name=\"frmLogIn\" action=\"LogInServlet\" method=\"post\">\n");
      out.write("                <label for=\"korisnickoIme\">Unesite korisnicko ime</label>\n");
      out.write("                <input type=\"text\" id=\"korisnickoIme\" name=\"korisnickoIme\">\n");
      out.write("                <br>\n");
      out.write("                <label for=\"lozinka\">Unesite lozinku</label>\n");
      out.write("                <input type=\"password\" id=\"lozinka\" name=\"lozinka\">\n");
      out.write("                <br>\n");
      out.write("                <button type=\"submit\" onclick=\"f()\">Uloguj me</button>\n");
      out.write("            </form>\n");
      out.write("            <br><hr><br>\n");
      out.write("            <form id=\"frmGost\" name=\"frmGost\" action=\"listaDogadjajaServlet\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" id=\"tip\" name=\"tip\" value=\"0\">\n");
      out.write("                <button type=\"submit\"> GOST </button>\n");
      out.write("            </form>\n");
      out.write("            <br><hr><br>\n");
      out.write("            \n");
      out.write("            <a href=\"registrujSe.jsp\"><button>Registruj se</button></a>\n");
      out.write("            \n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div id=\"futer\" name=\"futer\">\n");
      out.write("            <h3><pre>Predrag Popov NRT 110/12      jan.2015.</pre></h3>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            function proveraPrazno(id){\n");
      out.write("                var text=\"\";\n");
      out.write("                var el=document.getElementById(id);\n");
      out.write("                var x=el.value;\n");
      out.write("                if(x===null || x===\"\" ){\n");
      out.write("                    text=\"Niste popunili polje: \"+id+\"\\n\";  \n");
      out.write("                }\n");
      out.write("                return text;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function proveraLozinke(id){\n");
      out.write("                var text=\"\";\n");
      out.write("                var el2=document.getElementById(id);\n");
      out.write("                var y=el2.value;\n");
      out.write("                var n=y.length;\n");
      out.write("                if(n<5){\n");
      out.write("                    text=\"Lozinka mora da ima vise od 5 karaktera!!!\\n\";\n");
      out.write("                    \n");
      out.write("                }\n");
      out.write("                return text;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function f(){\n");
      out.write("                var x=\"\";\n");
      out.write("                x+=proveraPrazno(\"korisnickoIme\");\n");
      out.write("                x+=proveraPrazno(\"lozinka\");\n");
      out.write("                x+=proveraLozinke(\"lozinka\");\n");
      out.write("                if(x!==\"\"){\n");
      out.write("                    alert(x);\n");
      out.write("                    location.reload();\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
