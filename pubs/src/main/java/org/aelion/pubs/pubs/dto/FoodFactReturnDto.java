package org.aelion.pubs.pubs.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FoodFactReturnDto {
    private List<OpenFoodProductDto> products;
}
