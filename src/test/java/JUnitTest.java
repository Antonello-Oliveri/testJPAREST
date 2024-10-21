/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import it.anto.jparest.AppConfig;
import it.anto.jparest.model.StockData;
import it.anto.jparest.routes.UserRoute;
import it.anto.jparest.services.IEntityRepository;
import it.anto.jparest.model.User;
import java.io.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 *
 * @author Anto
 */
@Disabled
public class JUnitTest {

    public JUnitTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void PopulateTables() {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        IEntityRepository repos = ctx.getBean(IEntityRepository.class);
        User usr = new User();
        usr.setNome("AntonelloAXMMM");
        usr.setCognome("peppinoAXMMM");
        repos.createEntity(usr);
        // Stock
        StockData stockData = new StockData();
        stockData.setSKU("XPA00290");
        stockData.setStock(10.0);
        repos.createEntity(stockData);
       
    }

    @Test
    public void CreateUserByRoute() {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserRoute userRoute = ctx.getBean(UserRoute.class);
        User usr = new User();
        usr.setNome("AntonelloPOST");
        usr.setCognome("peppinoPOST");
        userRoute.saveUser(usr);
    }
    
    
}
