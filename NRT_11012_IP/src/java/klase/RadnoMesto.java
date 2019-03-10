
package klase;

/**
 *
 * @author POPOV
 */
public class RadnoMesto {
    private int sifraMesta;
    private String mesto;
    private String adresa;
    private String grad;
    
    public RadnoMesto(){}

    public RadnoMesto(int sifraMesta, String mesto, String adresa, String grad) {
        this.sifraMesta = sifraMesta;
        this.mesto = mesto;
        this.adresa = adresa;
        this.grad = grad;
    }

    public int getSifraMesta() {
        return sifraMesta;
    }

    public void setSifraMesta(int sifraMesta) {
        this.sifraMesta = sifraMesta;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    @Override
    public String toString() {
        return "RadnoMesto{" + "sifraMesta=" + sifraMesta + ", mesto=" + mesto + ", adresa=" + adresa + ", grad=" + grad + '}';
    }
    
    
}
