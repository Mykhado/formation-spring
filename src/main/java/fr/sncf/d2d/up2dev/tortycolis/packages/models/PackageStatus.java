package fr.sncf.d2d.up2dev.tortycolis.packages.models;

/**
 * Les différents statuts qu'un colis peut avoir.
 */
public enum PackageStatus {
    // En attente de traitement.
    PENDING,
    // En cours de livraison.
    IN_TRANSIT,
    // Colis livré.
    DELIVERED,
    // Colis retourné à l'expéditeur.
    RETURNED    
}
