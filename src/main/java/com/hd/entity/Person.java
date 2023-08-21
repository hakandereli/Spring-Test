package com.hd.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;


/**
 * @author hdereli
 * @since 24/7/2023
 */

/**
 * A person can have more than one address.
 *
 * An address can belong to a person.
 *
 * This is how the design was made.
 * */

@Entity
@Table(name = "PERSON")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Person {

    @Id
    @SequenceGenerator(name = "PERSON_ID_GENERATOR", sequenceName = "PERSON_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "PERSON_ID_GENERATOR", strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, name = "NAME")
    private String name;

    @Column(length = 100, name = "LAST_NAME")
    private String lastName;

    @Column(columnDefinition = "NUMERIC(3)", name = "AGE")
    private Long age;

    //Toplam 8 basamak 2 basamak ise virgulden sonrası demek
    @Column(columnDefinition = "DECIMAL(8,2)", name = "SALARY")
    private Double salary;

    @Column(length = 300, name = "EMAIL")
    private String email;

    @Column(length = 15, name = "PHONE")
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ADDRESS_ID")
    @ForeignKey(name = "FK_PERSON_ADDRESS")
    private List<Address> adressList;
}
