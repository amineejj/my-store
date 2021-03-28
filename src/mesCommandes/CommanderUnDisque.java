package mesCommandes;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class CommanderUnDisque extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		
		String nom = null;
		int nbreProduit = 0;
		Cookie[] cookies = request.getCookies();
		nom = Identification.chercheNom(cookies);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title> votre commande </title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<h3>" + "Bonjour "+ nom + " voici votre commande" + "</h3>");
		
		// affichage de tous les disques présents dans le panier (éléments de la session)
		HttpSession session = request.getSession();
		Enumeration names = session.getAttributeNames();
		String value = null;
		String code = null;
		out.println( "<h4>Commande : </h4> ");
		while (names.hasMoreElements()) {
			nbreProduit++;
			code = (String) names.nextElement();
			value = (String) session.getAttribute(code);
			
			out.println( code + " | " + value + " Euros </td>");
			out.println("<br>---------------------------------------<br>");
		}
		
		
		if(request.getParameter("ordre").equals("ajouter")) {
			// si parametre ordre == ajouter affichage du disque à ajouter au panier
			out.println(request.getParameter("code") + " | " + request.getParameter("prix") + " Euros </td>");
			out.println("<br>---------------------------------------<br>");
			
			// ajout du nouveau disque dans le panier
			session.setAttribute(request.getParameter("code"),
					request.getParameter("prix"));
		}
		
		
		out.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> ");
		out.println("<A HREF=enregistre> Vous pouvez enregistrer votre commande </A><br> ");
		out.println("</body>");
		out.println("</html>");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		doGet(request, response);
	}
}
