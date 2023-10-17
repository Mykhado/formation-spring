package fr.sncf.d2d.up2dev.tortycolis.packages.usecases.params;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class PaginatePackagesParams {
    
    @Min(0)
    private long page;

    @Min(1)
    @Max(100)
    private long itemsPerPage;

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
}
