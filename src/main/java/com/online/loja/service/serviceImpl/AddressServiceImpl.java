package com.online.loja.service.serviceImpl;

import com.online.loja.repository.AddressRepository;
import com.online.loja.repository.entity.Address;
import com.online.loja.repository.entity.Category;
import com.online.loja.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Optional<Address> updateAddress(Address address) {
        return addressRepository.findById(address.getId())
                .map(a -> address)
                .map(addressRepository::save);
    }

    @Override
    public Optional<Boolean> deleteAddressById(Long id) {
        return addressRepository.findById(id)
                .map(a -> {
                    addressRepository.delete(a);
                    return true;
        });
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
}
