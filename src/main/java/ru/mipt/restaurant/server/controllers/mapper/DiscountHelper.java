package ru.mipt.restaurant.server.controllers.mapper;

import ru.mipt.restaurant.server.controllers.dto.DiscountDto;
import ru.mipt.restaurant.server.domain.Discount;

public class DiscountHelper {

    private DiscountHelper() {
        throw new AssertionError("This constructor hasn;t to bec called.");
    }

    public static DiscountDto toDto(Discount discount) {
        DiscountDto discountDto = new DiscountDto();

        discountDto.setCoordinate(discount.getCoordinate());
        discountDto.setDescription(discount.getDescription());
        discountDto.setLocationName(discount.getLocationName());
        discountDto.setSale(discount.getSale());
        discountDto.setOwnerLogin(discount.getOwnerLogin());

        return discountDto;
    }

    public static Discount toDiscount(DiscountDto discountDto) {
        Discount discount = new Discount();

        discount.setCoordinate(discountDto.getCoordinate());
        discount.setDescription(discountDto.getDescription());
        discount.setLocationName(discountDto.getLocationName());
        discount.setSale(discountDto.getSale());
        discount.setOwnerLogin(discountDto.getOwnerLogin());

        return discount;
    }

}
