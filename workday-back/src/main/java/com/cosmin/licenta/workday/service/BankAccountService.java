package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.BankAccountDTO;
import com.cosmin.licenta.workday.entity.BankAccount;
import com.cosmin.licenta.workday.entity.CurrencyReferential;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.mapper.BankAccountMapper;
import com.cosmin.licenta.workday.repository.BankAccountRepository;
import com.cosmin.licenta.workday.repository.CurrencyReferentialRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final EmployeeRepository employeeRepository;

    private final BankAccountRepository bankAccountRepository;

    private final BankAccountMapper bankAccountMapper;

    private final CurrencyReferentialRepository currencyReferentialRepository;

    public BankAccountService(EmployeeRepository employeeRepository, BankAccountRepository bankAccountRepository, BankAccountMapper bankAccountMapper, CurrencyReferentialRepository currencyReferentialRepository) {
        this.employeeRepository = employeeRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
        this.currencyReferentialRepository = currencyReferentialRepository;
    }

    @Transactional
    public List<BankAccountDTO> getBankAccounts(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<BankAccount>> bankAccountsOptionalList;
            bankAccountsOptionalList = this.bankAccountRepository.findByEmployee(employeeOptional.get());
            if (bankAccountsOptionalList.isPresent()) {
                return bankAccountMapper.entitiesToDomains(bankAccountsOptionalList.get());
            }
        }
        return null;
    }

    public BankAccountDTO putBankAccount(final BankAccountDTO bankAccount, final  MultipartFile bankStatement) throws IOException {
        bankAccount.setAttestingDocument(bankStatement.getBytes());
        Optional<CurrencyReferential> currencyOptional = currencyReferentialRepository.findByLabel(bankAccount.getCurrency().getLabel());
        bankAccount.getCurrency().setId(currencyOptional.get().getId());
        bankAccountRepository.save(bankAccountMapper.domainToEntity(bankAccount));
        return bankAccount;
    }
}
