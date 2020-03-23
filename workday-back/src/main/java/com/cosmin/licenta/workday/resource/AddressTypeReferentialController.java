package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.AddressTypeReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/addressTypeReferential")
public class AddressTypeReferentialController {

    private AddressTypeReferentialService addressTypeReferentialService;

    public AddressTypeReferentialController(AddressTypeReferentialService addressTypeReferentialService) {
        this.addressTypeReferentialService = addressTypeReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getAddressTypeRefs() {
        return ResponseEntity.ok(addressTypeReferentialService.getAddressTypeReferentials());
    }
}
