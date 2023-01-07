package pl.nowogorski.shop.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderCustomerDto {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String street;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String city;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @NotNull
    private Long cartId;
    @NotNull
    private Long shipmentId;
    @NotNull
    private Long paymentId;
}
