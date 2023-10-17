package fr.sncf.d2d.up2dev.tortycolis.packages.usecases;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import fr.sncf.d2d.up2dev.tortycolis.packages.models.Package;
import fr.sncf.d2d.up2dev.tortycolis.packages.models.PackageStatus;
import fr.sncf.d2d.up2dev.tortycolis.packages.persistence.PackagesRepository;
import fr.sncf.d2d.up2dev.tortycolis.packages.usecases.params.CreatePackageParams;

@Service
public class CreatePackageUseCase {

    private static final int TRACKING_CODE_SIZE = 16;
    
    private static final char[] ALPHABET = new char[] {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

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
        
        final var random = SecureRandom.getInstanceStrong();
        final var bytes = new byte[TRACKING_CODE_SIZE];
        random.nextBytes(bytes); // [10, 20, -50, -120, -10, 0, 120...]

        var output = "";
        for (var i = 0; i < TRACKING_CODE_SIZE; i++){
            output += ALPHABET[Byte.toUnsignedInt(bytes[i]) % ALPHABET.length];
        }
        return output;
    }
}


