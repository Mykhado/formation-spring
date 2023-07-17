package fr.sncf.d2d.up2dev.tortycolis.packages;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/packages")
public class PackagesController {

    private final CreatePackageUseCase createPackageUseCase;

    public PackagesController(CreatePackageUseCase createPackageUseCase){
        this.createPackageUseCase = createPackageUseCase;
    }
    
    @PostMapping
    public Package create(@RequestBody @Valid CreatePackageParams params){
        return this.createPackageUseCase.create(params);
    }
}
