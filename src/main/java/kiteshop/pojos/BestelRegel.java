package kiteshop.pojos;

import java.math.BigDecimal;


import javax.persistence.*;


@Entity
public class BestelRegel {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    private int bestelRegelID;
    
	@ManyToOne
	private Product product;
    private int aantal;
    
    public BestelRegel(){
        product = new Product();
  
    }
    
    public BestelRegel(Product product, int aantal) {
  
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
        return "BestelRegel: "+ bestelRegelID +" productID = " + product.getProductID()+ " aantal = " + aantal;
    }
}

