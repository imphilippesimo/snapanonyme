package com.zerofiltre.snapanonyme.presentation.snaps.security;

import com.zerofiltre.snapanonyme.SnapanonymeApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SnapanonymeApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ConnectIntTests {

    @Autowired
    private MockMvc mvc;

    //TODO Connect with good credentials and check if token is provided
    //TODO Connect with wrong credentials and check if we receive the corresponding exception


}
