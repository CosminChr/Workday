package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.ContractTypeReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contactTypeReferential")
public class ContractTypeReferentialController {

    private final ContractTypeReferentialService contractTypeReferentialService;

    public ContractTypeReferentialController(ContractTypeReferentialService contractTypeReferentialService) {
        this.contractTypeReferentialService = contractTypeReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getContractTypeRefs() {
        return ResponseEntity.ok(contractTypeReferentialService.getContractTypeReferentials());
    }
}
