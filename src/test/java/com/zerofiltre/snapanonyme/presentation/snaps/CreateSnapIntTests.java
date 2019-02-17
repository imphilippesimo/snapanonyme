package com.zerofiltre.snapanonyme.presentation.snaps;

import com.zerofiltre.snapanonyme.SnapanonymeApplication;
import com.zerofiltre.snapanonyme.TestUtil;
import com.zerofiltre.snapanonyme.application.Snaps.GetSnaps;
import com.zerofiltre.snapanonyme.application.mapper.SnapDTOMapper;
import com.zerofiltre.snapanonyme.domain.model.Location;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerofiltre.snapanonyme.presentation.dto.SnapDTO;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SnapanonymeApplication.class)
@AutoConfigureMockMvc
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

    SnapDTO snap;
    MockMultipartFile picture;

    @Autowired
    GetSnaps snaps;

    @Autowired
    SnapDTOMapper mapper;

    ClassLoader classLoader = getClass().getClassLoader();

    private static final String pictureName = "beach.jpg";

    @Before
    public void setUp() {
        snap = new SnapDTO();
        snap.setReportsNumber(DEFAULT_REPORTS_NUMBER);
        snap.setVisible(DEFAULT_VISIBILITY_VALUE);
        snap.setPostedOn(DEFAULT_POSTED_ON);
        snap.setPostedAt(DEFAULT_LOCATION);
        try {
            picture = new MockMultipartFile("beach", classLoader.getResourceAsStream(pictureName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void should_create_article() throws Exception {

        int databaseSizeBeforeCreate = snaps.all().size();

        mvcResult = mvc.perform(MockMvcRequestBuilders.multipart("/snaps")
                .file(picture)
                .content(TestUtil.convertObjectToJsonBytes(snap))
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated());

        List<SnapDTO> dbSnaps = snaps.all();

        Assertions.assertThat(dbSnaps.size()).isEqualTo(databaseSizeBeforeCreate + 1);
        SnapDTO newlyCreatedSnap = dbSnaps.get(dbSnaps.size() - 1);
        Assertions.assertThat(newlyCreatedSnap.getPicture()).isNotNull();
        Assertions.assertThat(newlyCreatedSnap.getPicture().getId()).isNotNull();
        Assertions.assertThat(newlyCreatedSnap.getPicture().getMimeType()).isEqualTo(DEFAULT_MIME_TYPE);
        Assertions.assertThat(newlyCreatedSnap.getReportsNumber()).isEqualTo(DEFAULT_REPORTS_NUMBER);
        Assertions.assertThat(newlyCreatedSnap.isVisible()).isTrue();

    }


}
