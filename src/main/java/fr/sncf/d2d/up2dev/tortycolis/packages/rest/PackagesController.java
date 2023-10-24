package fr.sncf.d2d.up2dev.tortycolis.packages.rest;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.sncf.d2d.up2dev.tortycolis.packages.models.Package;
import fr.sncf.d2d.up2dev.tortycolis.packages.models.Pagination;
import fr.sncf.d2d.up2dev.tortycolis.packages.usecases.CreatePackageUseCase;
import fr.sncf.d2d.up2dev.tortycolis.packages.usecases.PaginatePackagesUseCase;
import fr.sncf.d2d.up2dev.tortycolis.packages.usecases.params.CreatePackageParams;
import fr.sncf.d2d.up2dev.tortycolis.packages.usecases.params.PaginatePackagesParams;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/packages")
public class PackagesController {

    private final CreatePackageUseCase createPackageUseCase;
    private final PaginatePackagesUseCase paginatePackagesUseCase;

    public PackagesController(
        CreatePackageUseCase createPackageUseCase,
        PaginatePackagesUseCase paginatePackagesUseCase
    ){
        this.createPackageUseCase = createPackageUseCase;
        this.paginatePackagesUseCase = paginatePackagesUseCase;
    }
    
    @GetMapping
    @PostAuthorize("@packagesGuard.canRead(returnObject.items, principal)")
    public Pagination<Package> paginate(@Valid PaginatePackagesParams params){
        return this.paginatePackagesUseCase.paginate(params);
    }

    @PostMapping
    public Package create(@RequestBody @Valid CreatePackageParams params) throws NoSuchAlgorithmException {
        return this.createPackageUseCase.create(params);
    }
}
