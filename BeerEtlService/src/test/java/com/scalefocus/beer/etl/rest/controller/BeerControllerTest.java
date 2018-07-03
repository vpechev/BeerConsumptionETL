package com.scalefocus.beer.etl.rest.controller;

import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import com.scalefocus.beer.etl.service.BeerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeerService mockBeerService;

    private String path = "/beers/";

    @Test
    public void insertInSql_Should_Success_Test() throws Exception {
        String mockInputDataJson = "[{},{}]";

        List<SqlBeerDTO> mockResultData = new ArrayList<>();
        SqlBeerDTO b1 = new SqlBeerDTO();
        b1.setId(5);
        SqlBeerDTO b2 = new SqlBeerDTO();
        b1.setId(6);
        mockResultData.add(b1);
        mockResultData.add(b2);

        when(mockBeerService.insertToSql(anyList())).thenReturn(mockResultData);

        this.mockMvc.perform(post(path + "_bulk")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mockInputDataJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(5)))
//                .andExpect(jsonPath("$[1].id", is(6)));
    }
}
