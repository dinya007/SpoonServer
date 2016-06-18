package ru.mipt.restaurant.server.controllers.mapper;

import ru.mipt.restaurant.server.controllers.dto.DiscountDto;
import ru.mipt.restaurant.server.domain.Discount;

public class DiscountMapper {

    private DiscountMapper() {
        throw new AssertionError("This constructor hasn;t to bec called.");
    }

    public static DiscountDto mapToDto(Discount discount) {
        DiscountDto discountDto = new DiscountDto();

        discountDto.setCoordinate(discount.getCoordinate());
        discountDto.setDescription(discount.getDescription());
        discountDto.setLocationName(discount.getLocationName());
        discountDto.setSale(discount.getSale());

        return discountDto;
    }

}
