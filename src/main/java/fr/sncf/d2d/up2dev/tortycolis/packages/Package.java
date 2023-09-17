package fr.sncf.d2d.up2dev.tortycolis.packages;

import java.util.UUID;

public class Package {

    private UUID id;
    
    private final String number;

    private final String street;

    private final String postalCode;

    private final String city;

    private final String country;

    private final String details;

    private final String phoneNumber;

    private final PackageStatus status;

    private final String email;

    private final String trackingCode;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumber() {
        return this.number;
    }

    public String getStreet() {
        return this.street;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public String getDetails() {
        return this.details;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public PackageStatus getStatus(){
        return this.status;
    }

    public String getTrackingCode(){
        return this.trackingCode;
    }

    private Package(String number, String street, String postalCode, String city, String country, String details,
            String phoneNumber, PackageStatus status, String email, String trackingCode) {
        this.number = number;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.details = details;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
        this.trackingCode = trackingCode;
    }

    public static PackageBuilder builder(){
        return new PackageBuilder();
    }

    public static class PackageBuilder {

        private String number;

        private String street;

        private String postalCode;

        private String city;

        private String country;

        private String details;

        private String phoneNumber;

        private String email;

        private PackageStatus status;

        private String trackingCode;

        public PackageBuilder number(String number){
            this.number = number;
            return this;
        }

        public PackageBuilder street(String street){
            this.street = street;
            return this;
        }

        public PackageBuilder postalCode(String postalCode){
            this.postalCode = postalCode;
            return this;
        }

        public PackageBuilder city(String city){
            this.city = city;
            return this;
        }

        public PackageBuilder country(String country){
            this.country = country;
            return this;
        }

        public PackageBuilder details(String details){
            this.details = details;
            return this;
        }

        public PackageBuilder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PackageBuilder email(String email){
            this.email = email;
            return this;
        }

        public PackageBuilder status(PackageStatus status){
            this.status = status;
            return this;
        }

        public PackageBuilder trackingCode(String code){
            this.trackingCode = code;
            return this;
        }

        public Package build(){
            return new Package(
                this.number,
                this.street,
                this.postalCode, 
                this.city,
                this.country,
                this.details,
                this.phoneNumber,
                this.status,
                this.email,
                this.trackingCode
            );
        }
    }
}
