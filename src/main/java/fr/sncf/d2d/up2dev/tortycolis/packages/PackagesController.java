package fr.sncf.d2d.up2dev.tortycolis.packages;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/packages")
public class PackagesController {
    
    @PostMapping
    public void create(@RequestBody CreatePackageRequestBody body){
        
    }
}
