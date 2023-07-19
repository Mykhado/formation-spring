package fr.sncf.d2d.up2dev.tortycolis.packages;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/packages")
public class PackagesController {

    private final CreatePackageUseCase createPackageUseCase;

    public PackagesController(CreatePackageUseCase createPackageUseCase) {
        this.createPackageUseCase = createPackageUseCase;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Package create(@RequestBody @Valid CreatePackageParams params) {

        return this.createPackageUseCase.create(params);
    }

    // Version avec response entity
    /*    public ResponseEntity<Package> create(@RequestBody @Valid CreatePackageParams params){

        return this.createPackageUseCase.create(params);
    }*/


}
