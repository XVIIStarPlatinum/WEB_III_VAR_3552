package TestsDB;

import example.entity.ResultEntity;
import example.repository.ResultRepository;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@DisplayName("ResultRepository test")
@Testcontainers
public class TestResults {

    private static PostgreSQLContainer<?> postgreSQLContainer;
    private static ResultRepository repository;
    @BeforeAll
    public static void beforeAll(){
        postgreSQLContainer = new PostgreSQLContainer<>()
                .withDatabaseName("test")
                .withUsername("test")
                .withPassword("test")
                .withExposedPorts(5432);
        postgreSQLContainer.start();
        repository = new ResultRepository();
    }
    @AfterAll
    public static void afterAll(){
        postgreSQLContainer.stop();
    }

    @Test
    @Order(value = 1)
    public void insertPointTest(){
        repository.addNewResult(new ResultEntity(1, 1, 1F, 1F, false));
        Assertions.assertEquals(1, repository.getEntityManager().createQuery("SELECT a from ResultEntity as a").getResultList().size());
    }
    @Test
    @Order(value = 2)
    public void getAllResultsTest(){
        List<ResultEntity> results = (List<ResultEntity>) repository.getAllResults();
        Assertions.assertEquals(results.size(), repository.getEntityManager().createQuery("SELECT a from ResultEntity as a").getResultList().size());
    }
    @Test
    @Order(value = 3)
    public void truncateTest(){
        repository.clearResults();
        Assertions.assertEquals(0, repository.getEntityManager().createQuery("SELECT a from ResultEntity as a").getResultList().size());
    }

}
