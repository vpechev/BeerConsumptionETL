package com.scalefocus.beer.etl.service;

import com.scalefocus.beer.etl.domain.mapper.DtoMapper;
import com.scalefocus.beer.etl.domain.noSql.NoSqlBeerDTO;
import com.scalefocus.beer.etl.domain.sql.FieldsDTO;
import com.scalefocus.beer.etl.domain.sql.SqlBeerDTO;
import com.scalefocus.beer.etl.messaging.publisher.RabbitMqPublisher;
import com.scalefocus.beer.etl.repository.BeerMongoRepository;
import com.scalefocus.beer.etl.repository.BeerSqlRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BeerServiceTest {
    @MockBean
    private BeerMongoRepository mockMongoRepository;
    @MockBean
    private BeerSqlRepository mockSqlRepository;
    @MockBean
    private DtoMapper mockNoSqlToSqlDtoMapper;
    @MockBean
    private RabbitMqPublisher mockRabbitMqPublisher;

    @Autowired
    private BeerService subject;

    @Test
    public void insertToNoSql_ShouldCall_Repo_Test() {
        doNothing().when(mockMongoRepository.insert(anyList()));

        subject.insertToNoSql(new ArrayList<>());

        verify(mockMongoRepository, times(1)).insert(anyList());
    }

    @Test
    public void insertToSql_ShouldCall_Repo_Test() {
        doNothing().when(mockSqlRepository.saveAll(anyList()));

        subject.insertToSql(new ArrayList<>());

        verify(mockSqlRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void emitBeerList_ShouldCall_RabbitPublisher_Test() {
        doNothing().when(mockRabbitMqPublisher).publishToRabbitMq(anyList());

        subject.insertToSql(new ArrayList<>());

        verify(mockRabbitMqPublisher, times(1)).publishToRabbitMq(anyList());
    }

    @Test
    public void transformSqlToNoSqlList_ShouldTransform_Successfully_Test(){
        List<SqlBeerDTO> inputList = new ArrayList<>();
        SqlBeerDTO beer1 = new SqlBeerDTO();
        beer1.setDatasetid("mock-dataset-id");
        beer1.setFields(new FieldsDTO());


        List<NoSqlBeerDTO> result = subject.transformSqlToNoSqlList(inputList);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(inputList.size(), result.size());
        assertNotNull(result.get(0));
        assertNotNull(result.get(1));
        assertNotNull(result.get(0).getId());
        assertNotNull(result.get(0).getName());
        assertNotNull(result.get(0).getCategoryName());
        assertNotNull(result.get(0).getStyleName());
        assertNotNull(result.get(0).getBreweriesName());
        assertNotNull(result.get(0).getCountry());
        assertNotNull(result.get(0).getStateName());
        assertNotNull(result.get(0).getCountry());
        assertNotNull(result.get(0).getStreet());
    }
}
