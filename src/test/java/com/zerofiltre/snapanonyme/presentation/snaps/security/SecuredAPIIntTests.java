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
public class SecuredAPIIntTests {

    @Autowired
    private MockMvc mvc;

    //TODO Try to access "public" role API without being connected, it has to be ok!
    //TODO Try to access "USER" role API without being connected, it has to fail!
    //TODO Try to access "ADMIN" role API without being connected, it has to fail!
    //TODO Connect with "USER" role credentials and access "/user" role APIs, it has to be ok!
    //TODO Connect with "USER" role credentials and access "/admin" role APIs, it has to fail!
    //TODO Connect with "ADMIN" role credentials and access "/admin" role APIs, it has to be ok!
    //TODO Connect with "ADMIN" role credentials and access "/user" role APIs, it has to be ok!
    //TODO Connect with "ADMIN" role credentials and access "/public" role APIs, it has to be ok!



}
