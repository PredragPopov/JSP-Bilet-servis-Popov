/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klase;

import java.util.Date;

/**
 *
 * @author POPOV
 */
public class Rezervacija {
    private int ID;
    private String korisnik;
    private int dogadjajID;
    private String dogadjajNaziv;
    private String mesto;
    private Date vremeOdrzavanja;
    private int katID;
    private String tipKat;
    private int cenaUl;
    private int brojUl;
    private int ukupnaCena;
    private Date vremeRez;
    private boolean kupljena;
    private boolean istekla;
    
    public Rezervacija() {}

    public Rezervacija(int ID, String korisnik, int dogadjajID, String dogadjajNaziv, String mesto, Date vremeOdrzavanja, int katID, String tipKat, int cenaUl, int brojUl, int ukupnaCena, Date vremeRez, boolean kupljena, boolean istekla) {
        this.ID = ID;
        this.korisnik = korisnik;
        this.dogadjajID = dogadjajID;
        this.dogadjajNaziv = dogadjajNaziv;
        this.mesto = mesto;
        this.vremeOdrzavanja = vremeOdrzavanja;
        this.katID = katID;
        this.tipKat = tipKat;
        this.cenaUl = cenaUl;
        this.brojUl = brojUl;
        this.ukupnaCena = ukupnaCena;
        this.vremeRez = vremeRez;
        this.kupljena = kupljena;
        this.istekla = istekla;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public int getDogadjajID() {
        return dogadjajID;
    }

    public void setDogadjajID(int dogadjajID) {
        this.dogadjajID = dogadjajID;
    }

    public String getDogadjajNaziv() {
        return dogadjajNaziv;
    }

    public void setDogadjajNaziv(String dogadjajNaziv) {
        this.dogadjajNaziv = dogadjajNaziv;
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

    public int getKatID() {
        return katID;
    }

    public void setKatID(int katID) {
        this.katID = katID;
    }

    public String getTipKat() {
        return tipKat;
    }

    public void setTipKat(String tipKat) {
        this.tipKat = tipKat;
    }

    public int getCenaUl() {
        return cenaUl;
    }

    public void setCenaUl(int cenaUl) {
        this.cenaUl = cenaUl;
    }

    public int getBrojUl() {
        return brojUl;
    }

    public void setBrojUl(int brojUl) {
        this.brojUl = brojUl;
    }

    public int getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(int ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Date getVremeRez() {
        return vremeRez;
    }

    public void setVremeRez(Date vremeRez) {
        this.vremeRez = vremeRez;
    }

    public boolean isKupljena() {
        return kupljena;
    }

    public void setKupljena(boolean kupljena) {
        this.kupljena = kupljena;
    }

    public boolean isIstekla() {
        return istekla;
    }

    public void setIstekla(boolean istekla) {
        this.istekla = istekla;
    }

    @Override
    public String toString() {
        return "Rezervacija{" + "ID=" + ID + ", korisnik=" + korisnik + ", dogadjajID=" + dogadjajID + ", dogadjajNaziv=" + dogadjajNaziv + ", mesto=" + mesto + ", vremeOdrzavanja=" + vremeOdrzavanja + ", katID=" + katID + ", tipKat=" + tipKat + ", cenaUl=" + cenaUl + ", brojUl=" + brojUl + ", ukupnaCena=" + ukupnaCena + ", vremeRez=" + vremeRez + ", kupljena=" + kupljena + ", istekla=" + istekla + '}';
    }
    
    
}
