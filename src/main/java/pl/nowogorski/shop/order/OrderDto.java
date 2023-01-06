package pl.nowogorski.shop.order;

import lombok.Getter;

@Getter
class OrderDto {
    private String firstName;
    private String lastName;
    private String street;
    private String zipCode;
    private String city;
    private String email;
    private String phone;
    private Long cartId;
}
