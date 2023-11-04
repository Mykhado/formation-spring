package fr.sncf.d2d.up2dev.tortycolis.packages.guards;

import java.security.MessageDigest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import fr.sncf.d2d.up2dev.tortycolis.packages.models.Package;
import fr.sncf.d2d.up2dev.tortycolis.users.models.ApplicationUserDetails;
import fr.sncf.d2d.up2dev.tortycolis.users.models.Role;

/**
 * Contrôle d'accès lié aux colis.
 */
@Component
public class PackagesGuard {
    
    public boolean canRead(List<Package> packages, Object principal, String trackingCode){

        if (Objects.nonNull(trackingCode))
            return packages.stream()
                .allMatch(p -> MessageDigest.isEqual(p.getTrackingCode().getBytes(), trackingCode.getBytes()));
        
        if (principal instanceof ApplicationUserDetails userDetails){

            final var roles = Role.fromAuthorities(userDetails.getAuthorities());

            if (roles.contains(Role.ADMINISTRATOR))
                return true;
            
            if (roles.contains(Role.DELIVERY_PERSON))
                return packages.stream()
                    .allMatch(p -> p.getDeliveryPersonId().equals(userDetails.getDomainUser().getId()));
        }

        return false;
    }

    public boolean canCreate(Package pack, Object principal){

        if (principal instanceof ApplicationUserDetails userDetails){

            final var roles = Role.fromAuthorities(userDetails.getAuthorities());

            if (roles.contains(Role.ADMINISTRATOR))
                return true;

            if (roles.contains(Role.DELIVERY_PERSON))
                return Optional.ofNullable(pack.getDeliveryPersonId())
                    .map(deliveryPersonId -> deliveryPersonId.equals(userDetails.getDomainUser().getId()))
                    .orElse(false);
        }

        return false;
    }
}
