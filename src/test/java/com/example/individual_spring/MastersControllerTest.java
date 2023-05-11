package com.example.individual_spring;

import com.example.individual_spring.stores.Master;
import com.example.individual_spring.stores.Service;
import com.example.individual_spring.stores.data.MasterRepository;
import com.example.individual_spring.stores.web.MastersController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MastersController.class)
public class MastersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private MastersController subject;

    @MockBean
    private MasterRepository masterRepo;

    @BeforeEach
    public void setUp() throws Exception {
        initMocks(this);
        subject = new MastersController(masterRepo);
    }

    @WithMockUser(roles="USER")
    @Test
    public void shouldAddMasterAndReturnString() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/masters").with(csrf())
                        .param("fullName", "Test full name")
                        .param("post", "test post")
                        .param("phone", "11111111111"))
                .andExpect(redirectedUrl("/masters"))
                .andExpect(status().isFound());
    }

    @WithMockUser(roles="USER")
    @Test
    public void shouldReturnAllMasters() throws Exception {
        List<Master> masters = new ArrayList<>();

        given(masterRepo.findAll()).willReturn(masters);

        String result = subject.showMasters();

        assertThat(result, is("masters"));
    }
}
