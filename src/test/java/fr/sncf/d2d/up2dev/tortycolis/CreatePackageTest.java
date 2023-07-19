package fr.sncf.d2d.up2dev.tortycolis;

import fr.sncf.d2d.up2dev.tortycolis.packages.CreatePackageUseCase;
import fr.sncf.d2d.up2dev.tortycolis.packages.Package;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest()
@AutoConfigureMockMvc(printOnlyOnFailure = true)

public class CreatePackageTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreatePackageUseCase createPackageUseCase;

    @Test
    public void create() throws Exception {

      /*  when(createPackageUseCase.create(any())).thenReturn(new ResponseEntity<Package>(HttpStatus.CREATED));*/

        this.mockMvc.perform(post("/packages")
                        .content("{\"email\":\"test@domain.com\","
                                + "\"city\":\"Paris\","
                                + "\"number\":\"10\","
                                + "\"street\":\"rue de la paix\","
                                + "\"phoneNumber\":\"0688888888\","
                                + "\"postalCode\":\"75000\","
                                + "\"country\":\"france\","
                                + "\"details\":\"2eme etages\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());


    }
}
