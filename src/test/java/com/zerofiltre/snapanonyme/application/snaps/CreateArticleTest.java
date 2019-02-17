package com.zerofiltre.snapanonyme.application.snaps;

import com.zerofiltre.snapanonyme.SnapanonymeApplication;
import com.zerofiltre.snapanonyme.application.Snaps.CreateSnap;
import com.zerofiltre.snapanonyme.application.mapper.SnapDTOMapper;
import com.zerofiltre.snapanonyme.domain.model.Location;
import com.zerofiltre.snapanonyme.domain.model.Picture;
import com.zerofiltre.snapanonyme.domain.model.Snap;
import com.zerofiltre.snapanonyme.infrastructure.repository.JPAArticleRepository;
import com.zerofiltre.snapanonyme.presentation.dto.SnapDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SnapanonymeApplication.class)
public class CreateArticleTest {

    @Autowired
    CreateSnap createSnap;

    @Autowired
    SnapDTOMapper mapper;

    @Autowired
    JPAArticleRepository repository;


    SnapDTO snapDTO;

    private static final String DEFAULT_MIME_TYPE = MediaType.IMAGE_JPEG_VALUE;
    private static final boolean DEFAULT_VISIBILITY_VALUE = true;
    private static final int DEFAULT_REPORTS_NUMBER = 0;
    private static final Instant DEFAULT_POSTED_ON = Instant.now();
    private static final Location DEFAULT_LOCATION = new Location(48.8262423, 2.3460248999999997);

    @Before
    public void setUp() {
        Snap snap = new Snap();
        snap.setPostedAt(DEFAULT_LOCATION);
        snapDTO = mapper.snapToSnapDTO(snap);
    }

    @Test
    public void should_persist_snap() throws IOException {

        int databaseSizeBeforeCreate = repository.all().size();

        //Create the snap
        mapper.snapDTOToSnap(createSnap.create(DEFAULT_LOCATION, null));

        //validate the snap in the database
        List<Snap> dbSnaps = repository.all();
        assertThat(dbSnaps.size()).isEqualTo(databaseSizeBeforeCreate + 1);
        Snap newlyCreatedSnap = dbSnaps.get(dbSnaps.size() - 1);
        assertThat(newlyCreatedSnap.getReportsNumber()).isEqualTo(DEFAULT_REPORTS_NUMBER);
        assertThat(newlyCreatedSnap.isVisible()).isTrue();
        assertThat(newlyCreatedSnap.getPostedAt().getLatitude()).isEqualTo(DEFAULT_LOCATION.getLatitude());
        assertThat(newlyCreatedSnap.getPostedAt().getLongitude()).isEqualTo(DEFAULT_LOCATION.getLongitude());


    }
}
