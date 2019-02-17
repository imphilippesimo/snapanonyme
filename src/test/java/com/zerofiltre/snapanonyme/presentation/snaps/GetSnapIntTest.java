package com.zerofiltre.snapanonyme.presentation.snaps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerofiltre.snapanonyme.application.Snaps.GetSnaps;
import com.zerofiltre.snapanonyme.domain.model.Snap;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetSnapIntTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private GetSnaps getSnaps;

    @Autowired
    private ObjectMapper objectMapper;

    private MvcResult mvcResult;


    @Test
    public void should_get_only_CloseSnaps() throws Exception {

        //TODO
        //1 consider a location
        //2 retrieve snaps 30m or less far away from that position
        //3 validate the test by checking if the distance between the locations, for each result is less than 30m
        mvcResult = mvc.perform(MockMvcRequestBuilders.get("/snaps").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk()).andReturn();

        String responseContentAsString = mvcResult.getResponse().getContentAsString();
        List<Snap> snaps = deSerializeContent(responseContentAsString);

        assertThat(String.valueOf(snaps.size()), CoreMatchers.is(String.valueOf(1)));
        assertThat(String.valueOf(snaps.get(0).getId()), CoreMatchers.is(String.valueOf(1)));


    }

    private List<Snap> deSerializeContent(String jsonContent) throws IOException {
        return objectMapper.readValue(jsonContent, new TypeReference<List<Snap>>() {
        });
    }

}