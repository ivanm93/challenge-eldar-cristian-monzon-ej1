/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.CardEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cristian
 */
public class CardService {
        Scanner leer = new Scanner(System.in);

public void menu (ArrayList<CardEntity> cardlist) {

    CardEntity mycard = new CardEntity();
    String option;

       System.out.println("Bienvenido al sistema para procesar operaciones con tarjeta de credito."); 
          
        do {
        
        System.out.println("------ELDAR LINK------");
       
        System.out.println("    Indique que tarea desea realizar:");
        System.out.println("1- Seleccionar una tarjeta");
        System.out.println("2- Agregar una tarjeta nueva");
        System.out.println("3- Informacion de mi tarjeta");
        System.out.println("4- Calcular tasa de interes");
        System.out.println("5- Generar compra");
        System.out.println("0-Salir");
        
        option = leer.next();     
       
        try {
            this.menuvalidate(option);
        } catch (Exception ex) {
                System.out.println(ex.getMessage());
                this.menu(cardlist);
        }
         
          switch (option) {
            case "1":
                mycard =  this.showCards(cardlist, mycard);
                break;
            case "2":
        {
            try {
                this.newCard(cardlist);
            } catch (Exception ex) {
                Logger.getLogger(CardService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case "3":
                this.infoCard(mycard, cardlist);
                break;
            case "4":
               
             this.interestRate(mycard, cardlist);
                break;
            case "5":
               this.toBuy(mycard, cardlist);
                
                break;
            case "0":
                System.out.println("Gracias por utilizar nuestros servicios :)");
                break;
            default:
                System.out.println("Opcion no valida, intente de nuevo");
        }
        } while (!"0".equals(option));        
    }

public void menuvalidate (String option) throws Exception{
    if (option == null || option.trim().isEmpty()|| !(option.matches("^[0-5]{1}$" ))){
                    throw new Exception("Opcion invalida, seleccione una opcion del 0 al 5");
    }
}

public void newCard (ArrayList<CardEntity> cardlist) throws Exception{
    Integer year, month;
    String cardnumber;
    String cardholder, brand;
    CardEntity cardentity = new CardEntity();
    
    System.out.println("Ingrese nombre y apellido: ");
    cardholder =(leer.next());
    System.out.println("Ingrese numero de tarjeta: ");
    cardnumber = (leer.next());
    System.out.println("Ingrese marca: ");
    brand = (leer.next().toUpperCase());
    System.out.println("Ingrese año de vencimiento: (YY)");
    year = leer.nextInt();
    System.out.println("Ingrese mes de vencimiento: (MM)");
    month = leer.nextInt();
  
        try {
             this.validate(cardlist, cardentity, cardholder, cardnumber, brand, year, month);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                this.menu(cardlist);
            }
         cardlist.add(cardentity);
     System.out.println(" Tarjeta agregada exitosamente");
    
}

public void validate (ArrayList<CardEntity> cardlist, CardEntity cardentity, String cardholder,String cardnumber,String brand,Integer year,Integer month) throws Exception{
         Date nowdate = new Date();

         if (year==null || year < (nowdate.getYear()-100)|| year>99) {
            throw new Exception(" El año no puede ser nulo, debe ser mayor o igual al año actual y debe tener un maximo de dos digitos ");
        }
    if (month==null  || month>12) {
            throw new Exception("EL mes no puede ser nulo, debe ser mayor al mes actual y menor igual a 12");
        } else {
          Date expirationdate = new Date(year+100,month-1,1);
          cardentity.setExpirationdate(expirationdate);
    }
        if (brand == null || brand.trim().isEmpty()) {
            throw new Exception("Marca no puede quedar vacia ni ser nula");
        } else {
            cardentity.setBrand(brand);
        }
        if (cardholder == null || cardholder.trim().isEmpty()) {
            throw new Exception("El campo de nombre y apellido no debe ser nulo o vacio");
        } else {
            cardentity.setCardholder(cardholder.toUpperCase());
        }
        if (cardnumber == null || cardnumber.trim().isEmpty()|| !(cardnumber.matches("^[0-9]{16}$"))) {
            throw new Exception("El numero de tarjeta no debe ser nulo o vacio y debe tener 16 digitos sin letras ni signos");
        } else {
            cardentity.setCardnumber(cardnumber);
        }
        for (CardEntity cardEntity : cardlist) {
            if(cardEntity.getCardnumber() == null ? cardnumber == null : cardEntity.getCardnumber().equals(cardnumber) ){
                            throw new Exception("Ya existe uns tarjeta con este numero, no se puede cargar intente nuevamente");
            }
    }
}
public void newCard (ArrayList<CardEntity> cardlist, String name, String cardnumber, String brand, Date date){
    CardEntity cardentity = new CardEntity();
    
    cardentity.setCardholder(name);
    cardentity.setCardnumber(cardnumber);
    cardentity.setBrand(brand.toUpperCase());
    cardentity.setExpirationdate(date);
    cardlist.add(cardentity);  
}

public CardEntity showCards (ArrayList<CardEntity> cardlist, CardEntity mycard) {
        
    String option;  
    boolean exit = false;
    System.out.println("Escriba el nombre de la tarjeta que desea usar");
    
    do {  
        cardlist.forEach((card) -> {
            System.out.println(card.getBrand());
        });
      
        option = leer.next();
     for (CardEntity card : cardlist) {
            if(card.getBrand() == null ? option.toUpperCase() == null : card.getBrand().equals(option.toUpperCase())){
                        mycard = card;
                        exit = true;
                        System.out.println("Usted a elegido la tarjeta " + card.getBrand() + " correctamente");
                        System.out.println("---------------------------------------");
                        break;
                    }  
                    }  
          if (exit==false){
               System.out.println("Opcion invalida");
           }
    
      } while (exit == false);
     
       return mycard;
}

public void infoCard (CardEntity mycard, ArrayList<CardEntity> cardlist) {
    
            try {
                this.infoValidate(mycard.getBrand(), cardlist);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                this.menu(cardlist);
            }
    
    System.out.println( "Datos de Tarjeta:\n "
           + "brand = " + mycard.getBrand() + "\n"
           + " cardnumber = " + mycard.getCardnumber() + "\n"
           + " cardholder = " + mycard.getCardholder() + "\n"
           + " expirationdate = " + formatDate(mycard.getExpirationdate()) + '}');

}

public void infoValidate (String brand, ArrayList<CardEntity> cardlist) throws Exception{
     if (brand==null || brand.isEmpty()){
            throw new Exception("Primero debe elegir una tarjeta");
        }
}
        
public void interestRate (CardEntity mycard, ArrayList<CardEntity> cardlist) {
    
    Double tasa = 0.00;
    Double interestamount = 0.00;
    Date nowdate = new Date();
    int year = nowdate.getYear();
    int month = nowdate.getMonth();
    
     System.out.println("Ingrese el importe:");
     Integer amount = leer.nextInt();
    
            try {
                this.validateAmount(amount, mycard.getBrand());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                this.menu(cardlist);
            }
            
     
switch (mycard.getBrand()){
    case "VISA":
        tasa = tasa + (year -100)/(month + 1);
        interestamount =(tasa * amount)/100;
       
        System.out.println("Para la tarjeta "+ mycard.getBrand() + " la tasa de interes es de: " + tasa + " % "
                + ", por lo tanto tendra un monto adicional de " + interestamount + " pesos");
        break;
    case "AMEX": 
        tasa = tasa + (nowdate.getMonth() + 1)*0.1;
        interestamount =(tasa * amount)/100;
 
        System.out.println("Para la tarjeta "+ mycard.getBrand() + " la tasa de interes es de: " + tasa + " % "
                + ", por lo tanto tendra un monto adicional de " + interestamount + " pesos");
        break;
    case "NARA":
        tasa = tasa + (nowdate.getDate() )*0.5;
        interestamount =(tasa * amount)/100;
 
        System.out.println("Para la tarjeta "+ mycard.getBrand() + " la tasa de interes es de: " + tasa + " % "
                + ", por lo tanto tendra un monto adicional de " + interestamount + " pesos");
        break;
}    
}

public void validateAmount (Integer amount, String brand) throws Exception {
   
    if (amount>1000 || amount<0 || amount== null) {
                throw new Exception("EL monto tiene que se mayor a 0 y menor a 1000, ademas de que no puede ser nulo");
    }
      if (brand==null || brand.isEmpty()){
            throw new Exception("Primero debe elegir una tarjeta");
        }
}
public void toBuy (CardEntity cardentity, ArrayList<CardEntity> cardlist) {

        System.out.println("Ingrese monto: ");
        Integer amount = leer.nextInt();
      
            try {
                this.validatePurchase(cardentity.getExpirationdate(), amount);
                         System.out.println("Su compra se realizo correctamente");  
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                this.menu(cardlist);            }

   
}

    public void validatePurchase (Date expirationdate, Integer amount) throws Exception {
    
           Date nowdate = new Date();
           
    if (amount>1000 || amount<0 || amount== null) {
                throw new Exception("EL monto tiene que se mayor a 0 y menor a 1000, ademas de que no puede ser nulo");
    }
  
    if (expirationdate == null ){
            throw new Exception("Primero debe elegir una tarjeta");
      } 
    if (expirationdate.compareTo(nowdate) < 0 ){
            throw new Exception("Su tarjeta expiro, no puede realizar la compra ");
      }
}
        

public String formatDate (Date date){
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
    String formatdate = sdf.format(date);
    return formatdate;
}
}