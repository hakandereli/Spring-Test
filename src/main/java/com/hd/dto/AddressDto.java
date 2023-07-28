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

    @Column(length = 500, name = "ADDRESS")
    private String address;

    @Enumerated
    @Column(length = 30, name = "ADRESS_TYPE")
    private EnumAddressType adressType;

    private Boolean active;
    private Person person;
}
