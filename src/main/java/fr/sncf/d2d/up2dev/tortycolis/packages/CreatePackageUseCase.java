package fr.sncf.d2d.up2dev.tortycolis.packages;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class CreatePackageUseCase {

    private final PackagesRepository packagesRepository;

    public CreatePackageUseCase(PackagesRepository packagesRepository){
        this.packagesRepository = packagesRepository;
    }

    public Package create(CreatePackageParams params){
        
        final var newPackage = Package.builder()
            .email(params.getEmail())
            .city(params.getCity())
            .number(params.getNumber())
            .street(params.getStreet())
            .phoneNumber(params.getPhoneNumber())
            .postalCode(params.getPostalCode())
            .country(params.getCountry())
            .details(params.getDetails())
            .build();

        this.packagesRepository.save(newPackage);


        return newPackage;
    }
}
