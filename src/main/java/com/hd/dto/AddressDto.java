package com.hd.dto;

import com.hd.entity.Address;
import com.hd.entity.Person;
import com.hd.enums.EnumAddressType;
import lombok.*;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
