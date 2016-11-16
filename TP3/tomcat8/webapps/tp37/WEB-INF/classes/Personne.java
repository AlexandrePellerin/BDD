public class Personne
{
    public String login;
    public String mdp;
    public String nom;
    public String prenom;
    public String role;
    public String adresse;

    public Personne(String login, String mdp,String nom, String prenom, String role,String adresse)
    {
	maj(login,mdp,nom,prenom,role,adresse);
    }

    public void maj(String login, String mdp,String nom, String prenom, String role,String adresse)
    {
	this.login = login;
	this.mdp = mdp;
	this.nom = nom;
	this.prenom = prenom;
	this.role = role;
	this.adresse = adresse;
    }

    public void maj(String login, String mdp,String nom, String prenom, String adresse)
    {
	this.login = login;
	this.mdp = mdp;
	this.nom = nom;
	this.prenom = prenom;
	// On n'autorise pas a changer le role
	this.adresse = adresse;
    }

    
}
