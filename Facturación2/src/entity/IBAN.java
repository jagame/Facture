/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IBAN implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIBAN;
    private String IBAN1;
    private String IBAN2;
    private String IBAN3;
    private String IBAN4;
    private String IBAN5;
    private String IBAN6;

    public IBAN(){
        
    }
    
    public IBAN(int idIBAN, String iban){
        this.idIBAN = idIBAN;
        setIBAN(iban);
    }
    
    public IBAN(int idIBAN, String IBAN1, String IBAN2, String IBAN3, String IBAN4, String IBAN5, String IBAN6) {
        this.idIBAN = idIBAN;
        this.IBAN1 = IBAN1;
        this.IBAN2 = IBAN2;
        this.IBAN3 = IBAN3;
        this.IBAN4 = IBAN4;
        this.IBAN5 = IBAN5;
        this.IBAN6 = IBAN6;
    }

    public final void setIBAN( String iban ){
        if( iban.length() != 24 ) throw new RuntimeException("Incorrect IBAN length");
        this.IBAN1 = iban.substring(0, 4);
        this.IBAN2 = iban.substring(4, 8);
        this.IBAN3 = iban.substring(8, 12);
        this.IBAN4 = iban.substring(12, 16);
        this.IBAN5 = iban.substring(16, 20);
        this.IBAN6 = iban.substring(20, 24);
    }
    
    public int getIdIBAN() {
        return idIBAN;
    }

    public void setIdIBAN(int idIBAN) {
        this.idIBAN = idIBAN;
    }

    public String getIBAN1() {
        return IBAN1;
    }

    public void setIBAN1(String IBAN1) {
        this.IBAN1 = IBAN1;
    }

    public String getIBAN2() {
        return IBAN2;
    }

    public void setIBAN2(String IBAN2) {
        this.IBAN2 = IBAN2;
    }

    public String getIBAN3() {
        return IBAN3;
    }

    public void setIBAN3(String IBAN3) {
        this.IBAN3 = IBAN3;
    }

    public String getIBAN4() {
        return IBAN4;
    }

    public void setIBAN4(String IBAN4) {
        this.IBAN4 = IBAN4;
    }

    public String getIBAN5() {
        return IBAN5;
    }

    public void setIBAN5(String IBAN5) {
        this.IBAN5 = IBAN5;
    }

    public String getIBAN6() {
        return IBAN6;
    }

    public void setIBAN6(String IBAN6) {
        this.IBAN6 = IBAN6;
    }
    
    
}
