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

    static PostgreSQLContainer<?> postgreSQLContainer;
    static ResultRepository repository;

    @BeforeAll
    public static void beforeAll(){
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine");
        postgreSQLContainer.start();
    }
    @AfterAll
    public static void afterAll(){
        postgreSQLContainer.stop();
    }
    @BeforeEach
    void setUp(){
        repository = new ResultRepository();
    }
    @Test
    @Order(value = 1)
    public void insertPointTest(){
        int size = repository.getAllResults().size();
        ResultEntity result = new ResultEntity(1, 1, 1F, 1F, false);
        repository.addNewResult(result);
        Assertions.assertEquals(size + 1, repository.getEntityManager().createQuery("SELECT a from ResultEntity as a").getResultList().size());
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
