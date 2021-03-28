package mesCommandes;
import javax.servlet.http.*;
public class Identification {
	
	static String chercheNom (Cookie [] cookies) {
		
		// cherche dans les cookies la valeur de celui qui se nomme "nom"
		// retourne la valeur de ce nom au lieu de inconnu
		if(cookies != null)
		for(Cookie c: cookies) {
			if(c.getName().equals("nom")) {
				System.out.println(c.getValue());
				return c.getValue();
			}
		}
		
		return "inconnu";
	}
}