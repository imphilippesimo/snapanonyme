package com.zerofiltre.snapanonyme.presentation.snaps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerofiltre.snapanonyme.SnapanonymeApplication;
import com.zerofiltre.snapanonyme.application.Snaps.CreateSnap;
import com.zerofiltre.snapanonyme.application.Snaps.DeleteSnap;
import com.zerofiltre.snapanonyme.application.Snaps.GetSnaps;
import com.zerofiltre.snapanonyme.domain.model.Location;
import com.zerofiltre.snapanonyme.presentation.dto.SnapDTO;
import org.apache.tika.Tika;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SnapanonymeApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GetSnapIntTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private GetSnaps getSnaps;

    @Autowired
    private CreateSnap createSnap;

    @Autowired
    private DeleteSnap deleteSnap;

    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(GetSnapIntTest.class);

    private MvcResult mvcResult;

    private static final Location EXPLORER_LOCATION = new Location(11.54256, 3.88527);
    private static final Location FAR_AWAY_SNAP_LOCATION = new Location(48.8262423, 2.3460248999999997);
    private static final Location CLOSE_SNAP_LOCATION = new Location(11.54261, 3.88532);
    private static final double DEFAULT_DISTANCE = 10;

    ClassLoader classLoader = this.getClass().getClassLoader();
    MockMultipartFile picture;

    InputStream stream = classLoader.getResourceAsStream("beach.jpg");
    Tika tika = new Tika();

    @Before
    public void setUp() throws IOException {
        logger.debug("Start Setting up the tests");
        //Register a snap with a close location
        picture = new MockMultipartFile("beach", "beach.jpg", tika.detect(stream), stream);
        logger.debug("End Setting up the tests");


    }

    private List<SnapDTO> performTest(Location location) throws Exception {
        //all db snaps
        deleteSnap.all();
        createSnap.create(EXPLORER_LOCATION, picture);

        mvcResult = mvc.perform(MockMvcRequestBuilders.get("/snaps")
                .param("longitude", String.valueOf(location.getLongitude()))
                .param("latitude", String.valueOf(location.getLatitude()))
                .param("distance", String.valueOf(DEFAULT_DISTANCE))
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk()).andReturn();

        String responseContentAsString = mvcResult.getResponse().getContentAsString();
        return deSerializeContent(responseContentAsString);

    }


    @Test
    public void should_Return_at_least_one_snap() throws Exception {
        List<SnapDTO> snaps = performTest(CLOSE_SNAP_LOCATION);

        //check that the snaps contains at least 1 result
        assertThat(snaps.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void should_Return_no_result() throws Exception {
        List<SnapDTO> snaps = performTest(FAR_AWAY_SNAP_LOCATION);

        //check that the snaps contains no result
        assertThat(snaps.size()).isGreaterThanOrEqualTo(0);

    }


    private List<SnapDTO> deSerializeContent(String jsonContent) throws IOException {
        return objectMapper.readValue(jsonContent, new TypeReference<List<SnapDTO>>() {
        });
    }

}