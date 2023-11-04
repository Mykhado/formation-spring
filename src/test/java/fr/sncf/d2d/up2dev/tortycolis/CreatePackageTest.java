package fr.sncf.d2d.up2dev.tortycolis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = true)
public class CreatePackageTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Cas de succès
     */
    @Test
    @WithUserDetails(value = "r.nzaou@sncf.fr")
    public void create() throws Exception {

        final var beforeCount = JdbcTestUtils.countRowsInTable(this.jdbcTemplate, "packages");

        this.mockMvc.perform(
            post("/packages")
                .content("{" + 
                    "\"email\":\"test@domain.com\"," +
                    "\"street\":\"rue de la paix\"," +
                    "\"city\":\"Paris\"," +
                    "\"phoneNumber\":\"0688888888\"," +
                    "\"number\":\"10\"," +
                    "\"details\": \"j'habite au 3ème étage porte droite\"," +
                    "\"postalCode\": \"69001\"," +
                    "\"country\": \"France\"" +
                "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
            .andExpectAll(
                status().isCreated(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.id", isA(String.class)),
                jsonPath("$.street", is("rue de la paix")),
                jsonPath("$.city", is("Paris")),
                jsonPath("$.phoneNumber", is("0688888888")),
                jsonPath("$.details", is("j'habite au 3ème étage porte droite")),
                jsonPath("$.postalCode", is("69001")),
                jsonPath("$.country", is("France")),
                jsonPath("$.deliveryPersonId", nullValue())
            );

        Assertions.assertEquals(beforeCount + 1, JdbcTestUtils.countRowsInTable(this.jdbcTemplate, "packages"));
    }
}
