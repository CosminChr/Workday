package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.config.security.JwtUtils;
import com.cosmin.licenta.workday.dto.request.LoginRequestDTO;
import com.cosmin.licenta.workday.dto.request.SignupRequestDTO;
import com.cosmin.licenta.workday.dto.response.JwtResponseDTO;
import com.cosmin.licenta.workday.dto.response.MessageResponseDTO;
import com.cosmin.licenta.workday.entity.RoleReferential;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.repository.RoleReferentialRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.service.UserDetailsImpl;
import com.cosmin.licenta.workday.util.RoleTypeReferentialEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleReferentialRepository roleReferentialRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {


        Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponseDTO(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequestDTO) {
        if (employeeRepository.existsByUsername(signUpRequestDTO.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Error: Username is already taken!"));
        }

        if (employeeRepository.existsByEmail(signUpRequestDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Error: Email is already in use!"));
        }

        // Create new user's account
        Employee employee = new Employee(signUpRequestDTO.getUsername(),
                signUpRequestDTO.getEmail(),
                encoder.encode(signUpRequestDTO.getPassword()));

        Set<String> strRoles = signUpRequestDTO.getRole();
        Set<RoleReferential> roles = new HashSet<>();

        if (strRoles == null) {
            RoleReferential userRole = roleReferentialRepository.findByLabel(RoleTypeReferentialEnum.EMPLOYEE.getLabel())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleReferential adminRole = roleReferentialRepository.findByLabel(RoleTypeReferentialEnum.ADMIN.getLabel())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        RoleReferential modRole = roleReferentialRepository.findByLabel(RoleTypeReferentialEnum.MANAGER.getLabel())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        RoleReferential userRole = roleReferentialRepository.findByLabel(RoleTypeReferentialEnum.EMPLOYEE.getLabel())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        employee.setRoles(roles);
        employeeRepository.save(employee);

        return ResponseEntity.ok(new MessageResponseDTO("User registered successfully!"));
    }
}
