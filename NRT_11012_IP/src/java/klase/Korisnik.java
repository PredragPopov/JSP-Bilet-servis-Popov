
package klase;

/**
 *
 * @author POPOV
 */
public class Korisnik {
    private String korisnickoIme;
    private String lozinka;
    private String tip;
    private int sifraMesta;
    private String ime;
    private String prezime;
    private String adresa;
    private String telefon;
    private String ePosta;
    private int istekleRez;
    
    public Korisnik(){}

    public Korisnik(String korisnickoIme, String lozinka, String tip, int sifraMesta, String ime, String prezime, String adresa, String telefon, String ePosta, int istekleRez) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.tip = tip;
        this.sifraMesta = sifraMesta;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.telefon = telefon;
        this.ePosta = ePosta;
        this.istekleRez = istekleRez;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getSifraMesta() {
        return sifraMesta;
    }

    public void setSifraMesta(int sifraMesta) {
        this.sifraMesta = sifraMesta;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getePosta() {
        return ePosta;
    }

    public void setePosta(String ePosta) {
        this.ePosta = ePosta;
    }

    public int getIstekleRez() {
        return istekleRez;
    }

    public void setIstekleRez(int istekleRez) {
        this.istekleRez = istekleRez;
    }

    @Override
    public String toString() {
        return "Korisnik{" + "korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", tip=" + tip + ", sifraMesta=" + sifraMesta + ", ime=" + ime + ", prezime=" + prezime + ", adresa=" + adresa + ", telefon=" + telefon + ", ePosta=" + ePosta + ", istekleRez=" + istekleRez + '}';
    }

    
    
    
}
