package com.zerofiltre.snapanonyme.presentation.snaps.security;

import com.zerofiltre.snapanonyme.SnapanonymeApplication;
import com.zerofiltre.snapanonyme.presentation.dto.UserDTO;
import com.zerofiltre.snapanonyme.presentation.security.JwtConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.zerofiltre.snapanonyme.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SnapanonymeApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ConnectTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    JwtConfig jwtConfig;

    private static final String USERNAME = "omar";
    private static final String PASSWORD = "12345";
    private static final String ROLE = "USER";

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "12345";
    private static final String ADMIN_ROLE = "ADMIN";

    private static final String WRONG_PASSWORD = "85475";


    private UserDTO user, admin, unknown;
    private MvcResult mvcResult;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    String token;

    @Before
    public void setUp() {
        logger.info("start setting up test ====");
        user = new UserDTO();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);

        admin = new UserDTO();
        admin.setUsername(ADMIN_USERNAME);
        admin.setRole(ADMIN_ROLE);
        admin.setPassword(ADMIN_PASSWORD);

        unknown = new UserDTO();
        unknown.setUsername(USERNAME);
        unknown.setPassword(WRONG_PASSWORD);
        logger.info("finish setting up test =====");


    }


    @Test
    public void shouldGenerateTheToken() throws Exception {

        connect(user, mvc, jwtConfig).andExpect(status().isOk())
                .andDo(mvcResult -> {
                    token = extractToken(mvcResult.getResponse(), jwtConfig);
                });

        logger.info("The returned header is: \n" + token);
        assertThat(token).isNotNull();
        assertThat(token).isNotBlank();
        assertThat(token).startsWith(jwtConfig.getPrefix());

    }


    @Test
    public void shouldNotGenerateToken() throws Exception {
        connect(unknown, mvc, jwtConfig).andExpect(status().isUnauthorized());

    }


}
