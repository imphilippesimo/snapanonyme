package com.doranco.snapanonyme.application;

import com.doranco.snapanonyme.SnapanonymeApplication;
import com.doranco.snapanonyme.domain.model.Picture;
import com.doranco.snapanonyme.domain.model.Snap;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SnapanonymeApplication.class)
public class CreateSnapTest {

    @Autowired
    CreateSnap createSnap;

    Snap snap;

    @Before
    public void setUp() {

        Picture picture = new Picture();
        picture.setMimeType(MediaType.IMAGE_JPEG.getType());
        snap = new Snap();
        snap.setPicture(picture);
        snap.setReportsNumber(0);
        snap.setVisible(Boolean.TRUE);
    }

    @Test
    public void should_persist_snap() {
        snap = createSnap.create(snap);
        assertThat(String.valueOf(snap.getPicture().getId()), CoreMatchers.is(notNullValue()));
    }
}
