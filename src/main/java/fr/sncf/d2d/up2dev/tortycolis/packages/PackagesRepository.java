package fr.sncf.d2d.up2dev.tortycolis.packages;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PackagesRepository {

    private final static String INSERT_PACKAGE_SQL = "INSERT INTO packages "
            + "(id, number, street, city, phone_number, details, country, email, status, delivery_person_id, tracking_code) "
            + "VALUES ()";

    private final JdbcTemplate jdbcTemplate;

    public PackagesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public void save(Package pack) {
        pack.setId(UUID.randomUUID());
        final var query = "INSERT INTO packages "
            + "(id, number, street, city, phone_number, details, country, email, status, tracking_code) "
            + "VALUES ('" + pack.getId() + "', "
            + "'" + pack.getNumber() + "', "
            + "'" + pack.getStreet() + "',"
            + "'" + pack.getCity() + "', "
            + "'" + pack.getPhoneNumber() + "',"
            + "'" + pack.getDetails() + "', "
            + "'" + pack.getCountry() + "',"
            + "'" + pack.getEmail() + "', "
            + "'" + pack.getStatus().toString().toLowerCase() + "',"
            + "'" + pack.getTrackingCode() + "'"
            + ")";
        System.out.println(query);
        this.jdbcTemplate.update(query);
    }
}
