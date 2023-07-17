package fr.sncf.d2d.up2dev.tortycolis.packages;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class PackagesRepository {
    
    private final List<Package> packages = new CopyOnWriteArrayList<>();

    public void save(Package pack){
        pack.setId(UUID.randomUUID());
        this.packages.add(pack);
    }
}
