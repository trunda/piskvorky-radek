/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.radekzatec77.piskvorky;

/**
 *
 * @author trunda
 */
public class HraciPole {

    private final Integer sirka;
    private final Integer vyska;
    
    private final String[] kameny;
    
    HraciPole(Integer sirka, Integer vyska) {
        this.sirka = sirka;
        this.vyska = vyska;
        this.kameny = new String[sirka * vyska];
    }
    
    public Integer dejVelikost() {
        return this.sirka * this.vyska;
    }
    
    public Integer prepoctiSouradnice(Integer x, Integer y) {
        return x + (y * this.sirka);
    }
    
    public void umistiKamen(Integer x, Integer y, String barva) {
        Integer index = this.prepoctiSouradnice(x, y);
        this.kameny[index] = barva;
    }
    
    public String dejKamen(Integer x, Integer y) {
        Integer index = this.prepoctiSouradnice(x, y);
        return this.kameny[index];        
    }
}
