package fr.sncf.d2d.up2dev.tortycolis.packages.usecases;

import org.springframework.stereotype.Service;

import fr.sncf.d2d.up2dev.tortycolis.packages.models.Package;
import fr.sncf.d2d.up2dev.tortycolis.packages.models.Pagination;
import fr.sncf.d2d.up2dev.tortycolis.packages.persistence.PackagesRepository;
import fr.sncf.d2d.up2dev.tortycolis.packages.usecases.params.PaginatePackagesParams;

@Service
public class PaginatePackagesUseCase {

    private final PackagesRepository packagesRepository;
    
    public PaginatePackagesUseCase(PackagesRepository packagesRepository){
        this.packagesRepository = packagesRepository;
    }

    public Pagination<Package> paginate(PaginatePackagesParams params){ 
        return this.packagesRepository.paginate(params);
    }
}
