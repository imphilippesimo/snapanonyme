package com.zerofiltre.snapanonyme.presentation.snaps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerofiltre.snapanonyme.SnapanonymeApplication;
import com.zerofiltre.snapanonyme.application.Snaps.GetSnaps;
import com.zerofiltre.snapanonyme.application.mapper.SnapDTOMapper;
import com.zerofiltre.snapanonyme.domain.model.Location;
import com.zerofiltre.snapanonyme.presentation.dto.SnapDTO;
import org.apache.tika.Tika;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SnapanonymeApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CreateSnapIntTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private GetSnaps getSnaps;

    @Autowired
    private ObjectMapper objectMapper;

    private ResultActions mvcResult;

    private static final String DEFAULT_MIME_TYPE = MediaType.IMAGE_JPEG_VALUE;
    private static final boolean DEFAULT_VISIBILITY_VALUE = true;
    private static final int DEFAULT_REPORTS_NUMBER = 0;
    private static final Instant DEFAULT_POSTED_ON = Instant.now();

    private static final Location DEFAULT_LOCATION = new Location(48.8262423, 2.3460248999999997);
    private static final Location DEFAULT_LOCATION_1 = new Location(11.54301, 3.88575);
    private static final Location DEFAULT_LOCATION_2 = new Location(11.54289, 3.88565);
    private static final Location DEFAULT_LOCATION_3 = new Location(11.54284, 3.88547);
    private static final Location DEFAULT_LOCATION_4 = new Location(11.54274, 3.88542);
    private static final Location DEFAULT_LOCATION_5 = new Location(11.54267, 3.88536);
    private static final Location DEFAULT_LOCATION_6 = new Location(11.54261, 3.88532);
    List<Location> locations = new ArrayList<Location>(Arrays.asList(DEFAULT_LOCATION, DEFAULT_LOCATION_1, DEFAULT_LOCATION_2, DEFAULT_LOCATION_3, DEFAULT_LOCATION_3, DEFAULT_LOCATION_4, DEFAULT_LOCATION_5, DEFAULT_LOCATION_6));
    List<SnapDTO> snapsDTOs = new ArrayList<SnapDTO>();
    List<MockMultipartFile> pictures = new ArrayList<MockMultipartFile>();

    @Autowired
    GetSnaps snaps;

    @Autowired
    SnapDTOMapper mapper;

    ClassLoader classLoader = getClass().getClassLoader();

    private static final String pictureName = "boy.png";

    @Before
    public void setUp() {
        SnapDTO snap;
        for (Location location : locations) {
            snap = new SnapDTO();
            snap.setReportsNumber(DEFAULT_REPORTS_NUMBER);
            snap.setVisible(DEFAULT_VISIBILITY_VALUE);
            snap.setPostedOn(DEFAULT_POSTED_ON);
            snap.setPostedAt(location);
            try {
                InputStream stream = classLoader.getResourceAsStream("boy.png");
                Tika tika = new Tika();
                pictures.add(new MockMultipartFile("boy", "boy.png", tika.detect(stream), stream));

            } catch (IOException e) {
                e.printStackTrace();
            }
            snapsDTOs.add(snap);

        }


    }


    @Test
    public void should_create_article() throws Exception {

        int databaseSizeBeforeCreate = snaps.all().size();
        int i = 0;

        for (SnapDTO snapDTO : snapsDTOs) {
            mvcResult = mvc.perform(MockMvcRequestBuilders.multipart("/public/snaps")
                    .file("picture", pictures.get(i).getBytes())
                    .param("longitude", String.valueOf(snapDTO.getPostedAt().getLongitude()))
                    .param("latitude", String.valueOf(snapDTO.getPostedAt().getLatitude()))
                    .contentType(MediaType.MULTIPART_FORM_DATA))
                    .andExpect(status().isCreated());
            i++;
        }

        List<SnapDTO> dbSnaps = snaps.all();

        Assertions.assertThat(dbSnaps.size()).isEqualTo(databaseSizeBeforeCreate + snapsDTOs.size());
        for (int j = 0; j < snapsDTOs.size(); j++) {
            SnapDTO newlyCreatedSnap = dbSnaps.get(dbSnaps.size() - (j + 1));
            Assertions.assertThat(newlyCreatedSnap.getPicture()).isNotNull();
            Assertions.assertThat(newlyCreatedSnap.getPicture().getId()).isNotNull();
            //TODO: valid names, and mime types, to do so register pass along all the multipart file ans not only the content
            //Assertions.assertThat(newlyCreatedSnap.getPicture().getMimeType()).isEqualTo(DEFAULT_MIME_TYPE);
            Assertions.assertThat(newlyCreatedSnap.getReportsNumber()).isEqualTo(DEFAULT_REPORTS_NUMBER);
            Assertions.assertThat(newlyCreatedSnap.isVisible()).isTrue();
        }


    }


}
