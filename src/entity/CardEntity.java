/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author cristian
 */
public class CardEntity {
  
    private Long id;
    private String brand;
    private String cardnumber;
    private String cardholder;
    private Date expirationdate;

    public CardEntity() {
    }

    public CardEntity(String brand, String cardnumber, String cardholder, Date expirationdate) {
        this.brand = brand;
        this.cardnumber = cardnumber;
        this.cardholder = cardholder;
        this.expirationdate = expirationdate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    @Override
    public String toString() {
        return "Datos de Tarjeta:\n brand = " + brand + "\n cardnumber = " + cardnumber + "\n cardholder = " + cardholder + "\n expirationdate = " +  expirationdate ;
    }
    
    
    
    
    
}
