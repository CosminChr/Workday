package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.AddressDTO;
import com.cosmin.licenta.workday.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/address")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<AddressDTO>> getAddress(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(addressService.getAddresses(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<AddressDTO> putAddress(@RequestBody final AddressDTO addressDTO) {
        return ResponseEntity.ok(addressService.putAddress(addressDTO));
    }
}
