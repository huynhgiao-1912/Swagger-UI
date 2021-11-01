package com.example.Swagger.UI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "product", createIndex = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @Id
   // @NotNull(message = "Name can't be null")
    private String ProductID;
   // @NotNull(message = "Name can't be null")
    private String ProductName;
   // @NotNull(message = "Name can't be null")
    private String CategoryID;
   // @Min(value = 0, message = "Min Price can't be positive")
    private long Price;

    Product(String productID, String productName, long Price, String categoryID)
    {
        this.ProductID = productID;
        this.ProductName = productName;
        this.CategoryID = categoryID;
        this.Price = Price;
    }
    public long getPrice() {
        return this.Price;
    }

    public void setProductID(String productID) {
        this.ProductID = productID;
    }

    public void setPrice(long price) {
        this.Price = price;
    }

    public void setCategoryID(String categoryID) {
        this.CategoryID = categoryID;
    }

    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public String getProductName() {
        return this.ProductName;
    }

    public String getProductID() {
        return this.ProductID;
    }

    public String getCategoryID() {
        return this.CategoryID;
    }

}
