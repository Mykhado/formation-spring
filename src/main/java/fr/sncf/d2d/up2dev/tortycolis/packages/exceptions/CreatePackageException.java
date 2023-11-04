package fr.sncf.d2d.up2dev.tortycolis.packages.exceptions;

/**
 * Exception qui survient lors d'une erreur technique de création de package.
 */
public class CreatePackageException extends Exception {
    
    public CreatePackageException(Throwable source){
        super(source);
    }
}
