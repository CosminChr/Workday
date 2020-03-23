package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.AddressDTO;
import com.cosmin.licenta.workday.entity.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressTypeReferentialMapper.class})
public interface AddressMapper {

    Address domainToEntity(final AddressDTO source);

    AddressDTO entityToDomain(final Address source);

    List<Address> domainsToEntities(final List<AddressDTO> sourceList);

    List<AddressDTO> entitiesToDomains(final List<Address> sourceList);
}
