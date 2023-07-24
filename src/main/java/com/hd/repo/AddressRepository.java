package com.hd.repo;

import com.hd.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hdereli
 * @since 24/7/2023
 */

public interface AddressRepository extends JpaRepository<Address, Long> {

}
