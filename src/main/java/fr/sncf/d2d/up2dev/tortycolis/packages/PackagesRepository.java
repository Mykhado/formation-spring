package fr.sncf.d2d.up2dev.tortycolis.packages;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PackagesRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PackagesRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Package pack) {
        pack.setId(UUID.randomUUID());
        final var query = "INSERT INTO packages "
            + "(id, number, street, city, phone_number, details, country, email, status, tracking_code) "
            + "VALUES (:id, :number, :street, :city, :phoneNumber, :details, :country, :email, :status::package_status, :trackingCode)";
        this.jdbcTemplate.update(
            query,
            Map.of(
                "id", pack.getId(),
                "street", pack.getStreet(),
                "number", pack.getNumber(),
                "city", pack.getCity(),
                "phoneNumber", pack.getPhoneNumber(),
                "details", pack.getDetails(),
                "country", pack.getCountry(),
                "email", pack.getEmail(),
                "status", pack.getStatus().toString().toLowerCase(),
                "trackingCode", pack.getTrackingCode()
            )
        );
    }
}
