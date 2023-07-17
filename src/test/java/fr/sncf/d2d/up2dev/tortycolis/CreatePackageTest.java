package fr.sncf.d2d.up2dev.tortycolis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = true)
public class CreatePackageTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void create() throws Exception {
        this.mockMvc.perform(
            post("/packages")
                .content("{" + 
                    "\"email\":\"test@domain.com\"," +
                    "\"street\":\"rue de la paix\"," +
                    "\"city\":\"Paris\"," +
                    "\"phoneNumber\":\"0688888888\"," +
                    "\"number\":\"10\"," +
                    "" +
                "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
            .andExpectAll(status().isCreated());
    }
}
