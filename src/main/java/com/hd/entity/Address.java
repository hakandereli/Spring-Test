package com.hd.entity;

import com.hd.enums.EnumAddressType;
import lombok.*;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @author hdereli
 * @since 24/7/2023
 */

@Entity
@Table(name = "PERSON_ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Address implements Serializable {

    @Id
    @SequenceGenerator(name = "PERSON_ADDRESS_ID_GENERATOR", sequenceName = "PERSON_ADDRESS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "PERSON_ADDRESS_ID_GENERATOR", strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 500, name = "ADDRESS")
    private String address;

    @Enumerated
    @Column(length = 30, name = "ADRESS_TYPE")
    private EnumAddressType adressType;

    @Column(name = "ACTIVE")
    private Boolean active;

//    @ManyToOne
//    @JoinColumn(name = "PERSON_ADDRESS_ID")
//    @ForeignKey(name = "FK_ADDRESS_PERSON")
//    private Person person;

}
