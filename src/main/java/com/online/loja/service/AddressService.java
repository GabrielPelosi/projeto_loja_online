package com.online.loja.service;

import com.online.loja.repository.entity.Address;
import com.online.loja.repository.entity.Category;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> getAllAddresses();

    Optional<Address> getAddressById(Long id);

    Optional<Address> updateAddress(Address address);

    Optional<Boolean> deleteAddressById(Long id);

    Address createAddress(Address address);


}
