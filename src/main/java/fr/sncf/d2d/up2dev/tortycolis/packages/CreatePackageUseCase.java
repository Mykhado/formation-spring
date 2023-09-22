package fr.sncf.d2d.up2dev.tortycolis.packages;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class CreatePackageUseCase {

    private static final int TRACKING_CODE_SIZE = 16;

    private final PackagesRepository packagesRepository;

    public CreatePackageUseCase(PackagesRepository packagesRepository){
        this.packagesRepository = packagesRepository;
    }

    public Package create(CreatePackageParams params) throws NoSuchAlgorithmException{
        
        final var newPackage = Package.builder()
            .email(params.getEmail())
            .city(params.getCity())
            .number(params.getNumber())
            .street(params.getStreet())
            .phoneNumber(params.getPhoneNumber())
            .postalCode(params.getPostalCode())
            .country(params.getCountry())
            .details(params.getDetails())
            .trackingCode(this.generateTrackingCode())
            .status(PackageStatus.PENDING)
            .build();

        this.packagesRepository.save(newPackage);

        return newPackage;
    }

    private String generateTrackingCode() throws NoSuchAlgorithmException {
        
        final var alphabet = new char[] {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };
        final var random = SecureRandom.getInstanceStrong();
        final var bytes = new byte[TRACKING_CODE_SIZE];
        random.nextBytes(bytes);

        var output = "";
        for (var i = 0; i < TRACKING_CODE_SIZE; i++){
            output += alphabet[(((int)bytes[i]) + 128) % alphabet.length];
        }
        return output;
    }
}
