package com.skylimit.Skylimit.dto.product;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
@Setter
@Getter
public class ProductPartialUpdate {
    private String name;
    private String slug;
    private Long categoryId;
    private String brandName;
    private BigDecimal price;
    private BigDecimal mrp;
    private String shortDescription;
    private String longDescription;
    private Boolean active;
    private Boolean featured;
}