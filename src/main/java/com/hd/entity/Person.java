package com.hd.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "PERSON_ADDRESS_ID")
    private List<Address> adressList;
}
