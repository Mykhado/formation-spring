package fr.sncf.d2d.up2dev.tortycolis.packages.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import fr.sncf.d2d.up2dev.tortycolis.packages.models.Pagination;
import fr.sncf.d2d.up2dev.tortycolis.packages.models.Package;
import fr.sncf.d2d.up2dev.tortycolis.packages.models.PackageStatus;
import fr.sncf.d2d.up2dev.tortycolis.packages.usecases.params.PaginatePackagesParams;

@Repository
public class PackagesRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PackagesRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Pagination<Package> paginate(PaginatePackagesParams params){

        final var conditions = new ArrayList<String>(2);

        params.getDeliveryPersonId().ifPresent(deliveryPersonId -> conditions.add("delivery_person_id = :deliveryPersonId"));
        params.getTrackingCode().ifPresent(trackingCode -> conditions.add("tracking_code = :trackingCode"));

        final var where = conditions.isEmpty() ? "" : "WHERE " + conditions.stream().collect(Collectors.joining(" AND "));

        final var selectQuery = "SELECT * FROM packages " + where + " ORDER BY id ASC OFFSET :offset LIMIT :limit";
        final var countQuery = "SELECT COUNT(id) FROM packages " + where;

        final var selectParameters = new HashMap<String, Object>();

        selectParameters.put("offset", params.getPage() * params.getItemsPerPage());
        selectParameters.put("limit", params.getItemsPerPage());

        final var countParameters = new HashMap<String, Object>();

        params.getDeliveryPersonId()
            .ifPresent(deliveryPersonId -> {
                selectParameters.put("deliveryPersonId", deliveryPersonId.toString());
                countParameters.put("deliveryPersonId", deliveryPersonId.toString());
            });

        params.getTrackingCode()
            .ifPresent(trackingCode -> {
                selectParameters.put("trackingCode", trackingCode);
                countParameters.put("trackingCode", trackingCode);
            });

        final var packages = this.jdbcTemplate.queryForStream(
            selectQuery, 
            selectParameters, 
            (resultSet, n) -> Package.builder()
                .id(UUID.fromString(resultSet.getString("id")))
                .email(resultSet.getString("email"))
                .street(resultSet.getString("street"))
                .city(resultSet.getString("city"))
                .number(resultSet.getString("number"))
                .status(PackageStatus.valueOf(resultSet.getString("status").toUpperCase()))
                .details(resultSet.getString("details"))
                .country(resultSet.getString("country"))
                .postalCode(resultSet.getString("postal_code"))
                .phoneNumber(resultSet.getString("phone_number"))
                .trackingCode(resultSet.getString("tracking_code"))
                .build()
        ).toList();


        final var total = this.jdbcTemplate.queryForObject(countQuery, countParameters, Long.class);

        return new Pagination<>(packages, total);
    }

    @PreAuthorize("@packagesGuard.canCreate(#pack, principal)")
    public void save(Package pack) {
        pack.setId(UUID.randomUUID());
        final var query = "INSERT INTO packages "
            + "(id, number, street, city, phone_number, details, country, email, status, postal_code, tracking_code) "
            + "VALUES (:id, :number, :street, :city, :phoneNumber, :details, :country, :email, :status::package_status, :postalCode, :trackingCode)";
        
        final var parameters = new HashMap<String, Object>(){{
            put("id", pack.getId());
            put("street", pack.getStreet());
            put("number", pack.getNumber());
            put("city", pack.getCity());
            put("phoneNumber", pack.getPhoneNumber());
            put("details", pack.getDetails());
            put("country", pack.getCountry());
            put("email", pack.getEmail());
            put("status", pack.getStatus().toString().toLowerCase());
            put("trackingCode", pack.getTrackingCode());
            put("postalCode", pack.getPostalCode());
        }};
        
        this.jdbcTemplate.update(
            query,
            parameters
        );
    }
}
