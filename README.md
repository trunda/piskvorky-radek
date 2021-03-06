# Jazyk

## Java

Java (výslovnost [ˈdžaːvə] IPA) je objektově orientovaný programovací jazyk, který vyvinula firma Sun Microsystems a představila 23. května 1995.

Jde o jeden z nejpoužívanějších programovacích jazyků na světě. Podle TIOBE indexu je Java nejpopulárnější programovací jazyk.[2] Díky své přenositelnosti je používán pro programy, které mají pracovat na různých systémech počínaje čipovými kartami (platforma JavaCard), přes mobilní telefony a různá zabudovaná zařízení (platforma Java ME), aplikace pro desktopové počítače (platforma Java SE) až po rozsáhlé distribuované systémy pracující na řadě spolupracujících počítačů rozprostřené po celém světě (platforma Java EE). Tyto technologie se jako celek nazývají platforma Java. Dne 8. května 2007 Sun uvolnil zdrojové kódy Javy (cca 2,5 miliónů řádků kódu) a Java bude dále vyvíjena jako open source.

### Výhody

- Multiplatformnost
- Silně typový jazyk
- Objektovost

### Nevýhody

- Ukecanost
- XML konfigurace

## NodeJS (JavaScript - ECMAScript)

### Výhody

- Rychlé prototypování
- Snadné sestavení

### Nevýhody

- UI pude složitě
- Výsledkem nebude binárka
- Interpret

## Ruby

- Rychlé prototypování
- Hezký jazyk (snadno pochopitlný)

### Nevýhody

- UI pude složitě
- Výsledkem nebude binárka
- Interpret


## Python

- Rychlé prototypování
- Hezký jazyk (snadno pochopitlný)

### Nevýhody

- UI pude složitě
- Výsledkem nebude binárka
- Interpret


## Object Pascal

Nevim o něm skoro nic.

## PHP

- Rychlé prototypování
- Well known jazyk

### Nevýhody

- UI pude složitě
- Výsledkem nebude binárka
- Interpret

# Přepočet souřadnic

<pre>
                                                                                                                                
   0                                                                                                 14                         
                                                                                                                                
+------+------+------+------+------+------+------+------+------+------+------+------+------+------+------+                      
|      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |                      
|      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |                      
|      |      |      |      |      |      |      |      |      |      |      |      |      |      |      |                      
+------+------+------+------+------+------+------+------+------+------+------+------+------+------+------+                      
                                                                                                                                
                                                                                                                                
                                                   ^                                                                            
                                                   |                                                                            
                                                   |                                                                            
                                                   |                                                                            
                                                   |                           prepocitejIndex(x,y):int -> x + (y * sirka)      
                                                   |                                                                            
                                                                                                                                
                            0, 0                                                                                                
                                 +------+------+------+------+------+          prepocitejIndex(0,0) -> 0                        
                                 |      |      |      |      |      |          prepocitejIndex(1,0) -> 1                        
                                 |  0   |  1   |  2   |  3   |  4   |          prepocitejIndex(3,0) -> 3                        
                                 |      |      |      |      |      |          prepocitejIndex(0,1) -> 5                        
                                 +------+------+------+------+------+          prepocitejIndex(4,2) -> 14                       
                                 |      |      |      |      |      |                                                           
                                 |  5   |  6   |  7   |  8   |  9   |                                                           
                             ^   |      |      |      |      |      |                                                           
                             |   +------+------+------+------+------+                                                           
                             |   |      |      |      |      |      |                                                           
                         y   |   |  10  |  11  |  12  |  13  |  14  |                                                           
                             |   |      |      |      |      |      |                                                           
                             |   +------+------+------+------+------+                                                           
                                                                                                                                
                                                                                                                                
                                  ------------->                                                                                
                                                                                                                                
                                        x                                                                                       
                                                                                                                                
</pre>
