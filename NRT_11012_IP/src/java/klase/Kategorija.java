
package klase;

public class Kategorija {
    private int ID;
    private String mesto;
    private String tipSedista;
    private int maxUl;
    private int prodate;
    private int rezervisane;
    private int cenaUl;
    
    public Kategorija(){}

    public Kategorija(int ID, String mesto, String tipSedista, int maxUl, int prodate, int rezervisane, int cenaUl) {
        this.ID = ID;
        this.mesto = mesto;
        this.tipSedista = tipSedista;
        this.maxUl = maxUl;
        this.prodate = prodate;
        this.rezervisane = rezervisane;
        this.cenaUl = cenaUl;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getTipSedista() {
        return tipSedista;
    }

    public void setTipSedista(String tipSedista) {
        this.tipSedista = tipSedista;
    }

    public int getMaxUl() {
        return maxUl;
    }

    public void setMaxUl(int maxUl) {
        this.maxUl = maxUl;
    }

    public int getProdate() {
        return prodate;
    }

    public void setProdate(int prodate) {
        this.prodate = prodate;
    }

    public int getRezervisane() {
        return rezervisane;
    }

    public void setRezervisane(int rezervisane) {
        this.rezervisane = rezervisane;
    }

    public int getCenaUl() {
        return cenaUl;
    }

    public void setCenaUl(int cenaUl) {
        this.cenaUl = cenaUl;
    }

    @Override
    public String toString() {
        return "Kategorija{" + "ID=" + ID + ", mesto=" + mesto + ", tipSedista=" + tipSedista + ", maxUl=" + maxUl + ", prodate=" + prodate + ", rezervisane=" + rezervisane + ", cenaUl=" + cenaUl + '}';
    }
    
    
}
