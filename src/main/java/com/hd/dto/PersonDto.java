package com.hd.dto;

import com.hd.entity.Address;
import lombok.*;

import javax.validation.constraints.NotNull;
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

    private Long id;

    @NotNull
    private String name;

    private String lastName;

    private List<String> addressList;
}
