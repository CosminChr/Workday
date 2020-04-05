package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.AddressTypeReferential;
import com.cosmin.licenta.workday.mapper.AddressTypeReferentialMapper;
import com.cosmin.licenta.workday.repository.AddressTypeReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressTypeReferentialService {

    private AddressTypeReferentialRepository addressTypeReferentialRepository;

    private AddressTypeReferentialMapper addressTypeReferentialMapper;

    public AddressTypeReferentialService(AddressTypeReferentialRepository addressTypeReferentialRepository, AddressTypeReferentialMapper addressTypeReferentialMapper) {
        this.addressTypeReferentialRepository = addressTypeReferentialRepository;
        this.addressTypeReferentialMapper = addressTypeReferentialMapper;
    }

    public List<ReferentialDTO> getAddressTypeReferentials() {
        List<AddressTypeReferential> addressTypeReferentials = addressTypeReferentialRepository.findAll();
        return addressTypeReferentialMapper.entitiesToDomains(addressTypeReferentials);
    }
}
