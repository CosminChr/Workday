package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.BankAccountDTO;
import com.cosmin.licenta.workday.dto.JobApplicationDTO;
import com.cosmin.licenta.workday.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bankAccount")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<BankAccountDTO>> getBankAccounts(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(bankAccountService.getBankAccounts(employeeId));
    }

    @PutMapping("/multipart/bankStatement")
    public ResponseEntity<BankAccountDTO> putAddress(@RequestPart (value = "bankAccount") final BankAccountDTO bankAccountDTO,
                                                     @RequestPart (value = "bankStatement") final MultipartFile bankStatement) throws IOException {
        bankAccountService.putBankAccount(bankAccountDTO, bankStatement);
        return ResponseEntity.ok(bankAccountDTO);
    }
}
