package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.CityDTO;
import com.cosmin.licenta.workday.entity.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City domainToEntity(final CityDTO source);

    CityDTO entityToDomain(final City source);

    List<City> domainsToEntities(final List<CityDTO> sourceList);

    List<CityDTO> entitiesToDomains(final List<City> sourceList);
}
