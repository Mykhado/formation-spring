package fr.sncf.d2d.up2dev.tortycolis.packages.usecases.params;

import java.util.Optional;
import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class PaginatePackagesParams {
    
    @Min(0)
    private long page;

    @Min(1)
    @Max(100)
    private long itemsPerPage;

    private UUID deliveryPersonId;

    private String trackingCode;

    public Optional<UUID> getDeliveryPersonId(){
        return Optional.ofNullable(this.deliveryPersonId);
    }

    public Optional<String> getTrackingCode(){
        return Optional.ofNullable(this.trackingCode);
    }

    public long getPage() {
        return this.page;
    }

    public long getItemsPerPage() {
        return this.itemsPerPage;
    }

    public void setPage(long page){
        this.page = page;
    }

    public void setItemsPerPage(long itemsPerPage){
        this.itemsPerPage = itemsPerPage;
    }

    public void setTrackingCode(String trackingCode){
        this.trackingCode = trackingCode;
    }

    public void setDeliveryPersonId(UUID deliveryPersonId){
        this.deliveryPersonId = deliveryPersonId;
    }
}
