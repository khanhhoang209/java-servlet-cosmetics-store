/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosmetics;

/**
 *
 * @author khanhhoang
 */
public class ProductError {
    private String productIDError;
    private String nameError;
    private String categoryError;
    private String priceError;
    private String quantityError;
    private String imageError;
    private String describeError;
    private String error;

    public ProductError() {
    }

    public ProductError(String productIDError, String nameError, String categoryError, String priceError, String quantityError, String imageError, String describeError, String error) {
        this.productIDError = productIDError;
        this.nameError = nameError;
        this.categoryError = categoryError;
        this.priceError = priceError;
        this.quantityError = quantityError;
        this.imageError = imageError;
        this.describeError = describeError;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String getProductIDError() {
        return productIDError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getCategoryError() {
        return categoryError;
    }

    public void setCategoryError(String categoryError) {
        this.categoryError = categoryError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }

    public String getDescribeError() {
        return describeError;
    }

    public void setDescribeError(String DescribeError) {
        this.describeError = DescribeError;
    }
    
    
}
