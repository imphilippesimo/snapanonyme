package com.zerofiltre.snapanonyme.presentation.snaps.security;

import com.zerofiltre.snapanonyme.SnapanonymeApplication;
import com.zerofiltre.snapanonyme.TestUtil;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.zerofiltre.snapanonyme.TestUtil.connect;
import static com.zerofiltre.snapanonyme.TestUtil.extractToken;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SnapanonymeApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SecuredAPIIntTests {

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
    private static final String PUBLIC_API_RESPONSE_CONTENT = "you are free to do whatever you want here";
    private static final String USER_API_RESPONSE_CONTENT = "you are here because you logged in";
    private static final String ADMIN_API_RESPONSE_CONTENT = "This is the admin area, congratulations ";
    MockHttpServletResponse response;


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
    public void shouldAccessPublicAPIWithoutBeingConnected() throws Exception {

        mvcResult = mvc.perform(get("/public")).andExpect(status().isOk()).andReturn();
        response = mvcResult.getResponse();
        assertThat(response.getContentAsString()).isEqualToIgnoringCase(PUBLIC_API_RESPONSE_CONTENT);

    }

    @Test
    public void shouldNotAccessUserAPIWithoutBeingConnected() throws Exception {
        mvcResult = mvc.perform(get("/user")).andExpect(status().isUnauthorized()).andReturn();
    }

    @Test
    public void shouldNotAccessAdminAPIWithoutBeingConnected() throws Exception {
        mvcResult = mvc.perform(get("/admin")).andExpect(status().isUnauthorized()).andReturn();
    }

    //Connect with "USER" role credentials and access "/user" role APIs, it has to be ok!
    @Test
    public void shouldAccessUserAPIWithUserRoleConnected() throws Exception {
        connect(user, mvc, jwtConfig).andExpect(status().isOk())
                .andDo(mvcResult -> {
                    token = extractToken(mvcResult.getResponse(), jwtConfig);
                });
        mvcResult = mvc.perform(get("/user").header(jwtConfig.getHeader(), token)).andExpect(status().isOk()).andReturn();
        response = mvcResult.getResponse();
        assertThat(response.getContentAsString()).isEqualToIgnoringCase(USER_API_RESPONSE_CONTENT);

    }

    //Connect with "USER" role credentials and access "/admin" role APIs, it has to fail!
    @Test
    public void shouldNotAccessAdminAPIWithUserRoleConnected() throws Exception {
        connect(user, mvc, jwtConfig).andExpect(status().isOk())
                .andDo(mvcResult -> {
                    token = extractToken(mvcResult.getResponse(), jwtConfig);
                });
        mvcResult = mvc.perform(get("/admin").header(jwtConfig.getHeader(), token)).andExpect(status().isForbidden()).andReturn();


    }

    //Connect with "ADMIN" role credentials and access "/admin" role APIs, it has to be ok!
    @Test
    public void shouldAccessAdminAPIWithAdminRoleConnected() throws Exception {
        connect(admin, mvc, jwtConfig).andExpect(status().isOk())
                .andDo(mvcResult -> {
                    token = extractToken(mvcResult.getResponse(), jwtConfig);
                });
        mvcResult = mvc.perform(get("/admin").header(jwtConfig.getHeader(), token)).andExpect(status().isOk()).andReturn();
        response = mvcResult.getResponse();
        assertThat(response.getContentAsString()).isEqualToIgnoringCase(ADMIN_API_RESPONSE_CONTENT);

    }

    //Connect with "ADMIN" role credentials and access "/user" role APIs, it has to be ok!
    @Test
    public void shouldAccessUserAPIWithAdminRoleConnected() throws Exception {
        connect(admin, mvc, jwtConfig).andExpect(status().isOk())
                .andDo(mvcResult -> {
                    token = extractToken(mvcResult.getResponse(), jwtConfig);
                });
        mvcResult = mvc.perform(get("/user").header(jwtConfig.getHeader(), token)).andExpect(status().isOk()).andReturn();
        response = mvcResult.getResponse();
        assertThat(response.getContentAsString()).isEqualToIgnoringCase(USER_API_RESPONSE_CONTENT);

    }
    //Connect with "ADMIN" role credentials and access "/public" role APIs, it has to be ok!

    @Test
    public void shouldAccessPublicAPIWithAdminRoleConnected() throws Exception {
        connect(admin, mvc, jwtConfig).andExpect(status().isOk())
                .andDo(mvcResult -> {
                    token = extractToken(mvcResult.getResponse(), jwtConfig);
                });
        mvcResult = mvc.perform(get("/public").header(jwtConfig.getHeader(), token)).andExpect(status().isOk()).andReturn();
        response = mvcResult.getResponse();
        assertThat(response.getContentAsString()).isEqualToIgnoringCase(PUBLIC_API_RESPONSE_CONTENT);

    }

}
