package com.gmail.radekzatec77.piskvorky;

public class Main {
     
    public static void over(Boolean spravne) {
        if (spravne) {
            System.out.println("Super! Zelená!");
        } else {
            System.out.println("Spatné! Červená!");
        }
    }
    
    public static void main(String[] args) {
        
        String[] a = new String[5];
                
        HraciPole pole = new HraciPole(10, 10);
        
        over(pole.dejVelikost() == 100);
        
        
        over(pole.prepoctiSouradnice(0, 0) == 0);
        over(pole.prepoctiSouradnice(3, 4) == 43);                       
        
                
        pole.umistiKamen(0, 0, "M");
        over(pole.dejKamen(0, 0).equals("M"));
        
        over(pole.dejKamen(1, 0) == null);
        
        pole.umistiKamen(0, 3, "Č");
        over(pole.dejKamen(0, 3).equals("Č"));
                
    }
  
}
