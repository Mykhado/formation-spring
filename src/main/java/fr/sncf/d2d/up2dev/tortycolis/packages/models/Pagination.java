package fr.sncf.d2d.up2dev.tortycolis.packages.models;

import java.util.List;

public class Pagination<T> {
    
    private final long total;

    private final List<T> items;

    public Pagination(List<T> items, long total){
        this.items = items;
        this.total = total;
    }

    public long getTotal() {
        return this.total;
    }

    public List<T> getItems() {
        return this.items;
    }   
}
