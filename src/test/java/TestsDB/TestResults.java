package TestsDB;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import example.service.AreaChecker;
import example.beans.ResultsControllerBean;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.google.common.collect.HashMultiset;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.sql.*;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
@Testcontainers
public class TestResults {
    @ClassRule
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.0").withDatabaseName("point_model").withUsername("postgres").withPassword("240854");

    @BeforeAll
    public static void beforeAll(){
        postgreSQLContainer.start();
    }
    @AfterAll
    public static void afterAll(){
        postgreSQLContainer.stop();
    }

    @Test
    public void insertPointTest() throws SQLException {

    }
    @Test
    public void truncateTest() throws SQLException {

    }
    @Test
    public void getAllResultsTest() throws SQLException {


    }
}
