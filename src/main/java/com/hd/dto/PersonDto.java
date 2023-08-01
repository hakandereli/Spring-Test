package com.hd.dto;

import com.hd.entity.Address;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
/**
 * @author hdereli
 * @since 24/7/2023
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class PersonDto {

    /**
     * Bu işlemleri arayüzden yapmak çok daha kolay ama örneğin başka bir firmaya açacaksınız bunu ve saçma veriler
     * girilmesini istemiyorsunuz.
     *
     * Sizin arayüzünüzden gelmeyen istekleri bu şekilde kontrol edebilirsiniz.
     * */

    private Long id;

    @NotNull(message = "Must be not null Name")
    @Size(min = 2, message = "name must be at least 2 characters")
    private String name;

    @NotNull(message = "Must be not null lastName")
    @Size(min = 2, message = "lastName must be at least 2 characters")
    private String lastName;

    @Min(value = 18 , message = "Minimum age 18")
    @Max(value = 200 , message = "Maximum age 200")
    @Positive(message = "age cannot be negative")
    @NotNull(message = "Must be not null Age")
    private Long age;

    @NotNull(message = "Salary field cannot be not null.")
    @Digits(integer = 6, fraction = 2, message = "The Salary field must have a maximum of 6 digits and contain 2 decimal places.")
    @DecimalMin(value = "0.00", inclusive = false, message = "Salary field must be greater than 0.")
    private Double salary;

    @Email(message = "Email Should be valid")
    @NotNull(message = "Salary field cannot be not null.")
    private String email;

    @Pattern(regexp = "^\\+\\d{12}$", message = "Example : +905552221133")
    @NotNull(message = "Salary field cannot be not null.")
    private String phone;

    @NotNull
    @Valid
    private List<AddressDto> addressList;
}
