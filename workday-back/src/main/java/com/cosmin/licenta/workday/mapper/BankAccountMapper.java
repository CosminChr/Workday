package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.BankAccountDTO;
import com.cosmin.licenta.workday.entity.BankAccount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CurrencyReferentialMapper.class, EmployeeMapper.class})
public interface BankAccountMapper {

    BankAccount domainToEntity(final BankAccountDTO source);

    BankAccountDTO entityToDomain(final BankAccount source);

    List<BankAccount> domainsToEntities(final List<BankAccountDTO> sourceList);

    List<BankAccountDTO> entitiesToDomains(final List<BankAccount> sourceList);
}
