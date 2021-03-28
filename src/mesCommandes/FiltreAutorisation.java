package mesCommandes;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class FiltreAutorisation implements Filter {
	
	private FilterConfig filterConfig = null;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.filterConfig = filterConfig;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String nom = null;
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		Cookie[] cookies = hrequest.getCookies();
		
		// test s'il existe un cookie dont l'attribut est "nom"
		nom = Identification.chercheNom(cookies);
		System.out.println("filtrer "+nom);
		if (nom.equals("inconnu")){ 
			// cas ou il n'existe pas appel de la servlet inscrire
			hresponse.sendRedirect("sinscrire");
		}
		else {
			chain.doFilter(request, response); 
		}
	}
	public void destroy() {
	this.filterConfig = null;
	}
}