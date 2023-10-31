package fr.sncf.d2d.up2dev.tortycolis.packages.guards;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import fr.sncf.d2d.up2dev.tortycolis.packages.models.Package;
import fr.sncf.d2d.up2dev.tortycolis.users.models.ApplicationUserDetails;
import fr.sncf.d2d.up2dev.tortycolis.users.models.Role;

@Component
public class PackagesGuard {
    
    public boolean canRead(List<Package> packages, Object principal){
        
        if (principal instanceof ApplicationUserDetails userDetails){

            final var roles = Role.fromAuthorities(userDetails.getAuthorities());

            if (roles.contains(Role.ADMINISTRATOR))
                return true;
            
            if (roles.contains(Role.DELIVERY_PERSON))
                return packages.stream().allMatch(p -> p.getDeliveryPersonId().equals(userDetails.getDomainUser().getId()));
        }

        return false;
    }
}
