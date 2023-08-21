package com.hd.dto;

import com.hd.entity.Address;
import com.hd.entity.Person;
import com.hd.enums.EnumAddressType;
import lombok.*;

import java.util.List;
import jakarta.validation.constraints.*;

/**
 * @author hdereli
 * @since 7/28/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class AddressDto {

    private Long id;

    @NotNull(message = "Must be not null address")
    @Size(min = 25, message = "lastName must be at least 25 characters")
    private String address;

    @NotNull(message = "Must be not null adressType")
    private EnumAddressType adressType;

    @NotNull(message = "Must be not null active")
    private Boolean active;
//    private Person person;
}
