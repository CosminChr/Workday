package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.AcademicStudyDTO;
import com.cosmin.licenta.workday.dto.AddressDTO;
import com.cosmin.licenta.workday.dto.CityDTO;
import com.cosmin.licenta.workday.entity.AcademicStudy;
import com.cosmin.licenta.workday.entity.Address;
import com.cosmin.licenta.workday.entity.StudyFieldReferential;
import com.cosmin.licenta.workday.entity.StudyLevelReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudyFieldReferential.class, StudyLevelReferential.class})
public interface AcademicStudyMapper {

    AcademicStudy domainToEntity(final AcademicStudyDTO source);

    AcademicStudyDTO entityToDomain(final AcademicStudy source);

    List<AcademicStudy> domainsToEntities(final List<AcademicStudyDTO> sourceList);

    List<AcademicStudyDTO> entitiesToDomains(final List<AcademicStudy> sourceList);

}
