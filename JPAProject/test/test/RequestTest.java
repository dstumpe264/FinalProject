package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Request;

public class RequestTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private Request request;
    
    @Before
      public void setUp() throws Exception {
        this.emf = Persistence.createEntityManagerFactory("AutoDB");
        this.em = emf.createEntityManager();
        request = em.find(Request.class, 1);
    }
    
    @After
      public void tearDown() throws Exception { 
        this.em.close();
        this.emf.close();
        request = null;
    }
    
    @Test
    public void test_quote() {
        assertEquals(1, request.getId());
    }
    
    
    @Test
    public void test_quote_mapping() {
    }
    
}