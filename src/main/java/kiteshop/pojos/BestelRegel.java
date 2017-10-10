package kiteshop.pojos;

import java.math.BigDecimal;

public class BestelRegel {

    private int bestelRegelID;
    private Product product;
    private int aantal;
    private Bestelling bestelling;
    

    /**
     *
     */
    
    public BestelRegel(){
        product = new Product();
        bestelling = new Bestelling();
    }
    
    public BestelRegel(Bestelling bestelling, Product product, int aantal) {
        this.bestelling = bestelling;
        this.product = product;
        this.aantal = aantal;
    }
    
    public int getBestelRegelID(){
        return this.bestelRegelID;
    }
    
    public void setBestelRegelID(int id){
        this.bestelRegelID = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public int getBestellingID() {
        return getBestelling().getBestellingID(); 
    }
    
    public BigDecimal pricePerLine(){
        BigDecimal prijsPerRegel = null;
        return prijsPerRegel = BigDecimal.valueOf(this.aantal).multiply(this.product.getPrijs());
        
    }
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + aantal;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BestelRegel other = (BestelRegel) obj;
        if (aantal != other.aantal) {
            return false;
        }
        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "BestelRegel: product = " + product.toString()+ " aantal = " + aantal;
    }

}
