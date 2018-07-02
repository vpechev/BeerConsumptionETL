package com.scalefocus.beer.etl.service;

import com.scalefocus.beer.etl.domain.mapper.DtoMapper;
import com.scalefocus.beer.etl.domain.noSql.NoSqlBeerDTO;
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
    public void insertToNoSql_WithList_ShouldCall_Repo_Test() {
        doNothing().when(mockMongoRepository.insert(anyList()));

        subject.insertToNoSql(new ArrayList<>());

        verify(mockMongoRepository, times(1)).insert(anyList());
    }

    @Test
    public void insertToNoSql_WithSingleObject_ShouldCall_Repo_Test() {
        doNothing().when(mockMongoRepository.insert(anyList()));

        subject.insertToNoSql(new NoSqlBeerDTO());

        verify(mockMongoRepository, times(1)).insert(any(NoSqlBeerDTO.class));
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
}
