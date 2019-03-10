
package klase;
import java.util.ArrayList;
import java.util.Date;

public class Dogadjaj {
    private int ID;
    private String naziv;
    private String mesto;
    private Date vremeOdrzavanja;
    private String opis;
    private String datoteka;
    private ArrayList<Kategorija> listaKat;
    private int preostale;
    private boolean mogucaRez;
    private boolean istekao;
    
    public Dogadjaj(){}

    public Dogadjaj(int ID, String naziv, String mesto, Date vremeOdrzavanja, String opis, String datoteka, ArrayList<Kategorija> listaKat, int preostale, boolean mogucaProdaja, boolean istekao) {
        this.ID = ID;
        this.naziv = naziv;
        this.mesto = mesto;
        this.vremeOdrzavanja = vremeOdrzavanja;
        this.opis = opis;
        this.datoteka = datoteka;
        this.listaKat = listaKat;
        this.preostale = preostale;
        this.mogucaRez = mogucaProdaja;
        this.istekao = istekao;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Date getVremeOdrzavanja() {
        return vremeOdrzavanja;
    }

    public void setVremeOdrzavanja(Date vremeOdrzavanja) {
        this.vremeOdrzavanja = vremeOdrzavanja;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getDatoteka() {
        return datoteka;
    }

    public void setDatoteka(String datoteka) {
        this.datoteka = datoteka;
    }

    public ArrayList<Kategorija> getListaKat() {
        return listaKat;
    }

    public void setListaKat(ArrayList<Kategorija> listaKat) {
        this.listaKat = listaKat;
    }

    public int getPreostale() {
        return preostale;
    }

    public void setPreostale(int preostale) {
        this.preostale = preostale;
    }

    public boolean isMogucaRez() {
        return mogucaRez;
    }

    public void setMogucaRez(boolean mogucaRez) {
        this.mogucaRez = mogucaRez;
    }

    public boolean isIstekao() {
        return istekao;
    }

    public void setIstekao(boolean istekao) {
        this.istekao = istekao;
    }

    @Override
    public String toString() {
        return "Dogadjaj{" + "ID=" + ID + ", naziv=" + naziv + ", mesto=" + mesto + ", vremeOdrzavanja=" + vremeOdrzavanja + ", opis=" + opis + ", datoteka=" + datoteka + ", listaKat=" + listaKat + ", preostale=" + preostale + ", mogucaProdaja=" + mogucaRez + ", istekao=" + istekao + '}';
    }
    
    
}
