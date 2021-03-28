package mesCommandes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViderPanier extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		eraseSession(req, resp);
		resp.sendRedirect("achat");
		Cookie[] cookies = req.getCookies();
		
		for(Cookie c: cookies) {
			if(c.getName().equals("nom"))
				System.out.println(c.getValue());
		}
	}
	
	protected void eraseSession(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession session = req.getSession();
		session.invalidate();
		System.out.println("vider session");
	}
	
	protected void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null)
	        for (Cookie cookie : cookies) {
	            cookie.setMaxAge(0);
	            resp.addCookie(cookie);
	        }
	    System.out.println("vider cookies");
	}
}
