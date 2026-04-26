package com.skylimit.Skylimit.dto.product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductUpdateRequest {
@NotBlank(message="Product name should not be empty")
private String name;
@NotBlank(message="Product slug should not be empty")
private String slug;
@NotNull(message="Product category-id should not be empty")
@Positive(message="Product category-id should be positive")
  Long categoryId;
private String brandName;
@NotNull(message="Product price should not be empty")
@Positive(message ="Product price should be positive")
private BigDecimal price;
private BigDecimal mrp;
private String shortDescription;
private String longDescription;
private Boolean active;
private Boolean featured;
}