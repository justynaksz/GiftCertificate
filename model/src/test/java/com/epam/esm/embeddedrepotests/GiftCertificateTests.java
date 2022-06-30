package com.epam.esm.embeddedrepotests;

import com.epam.esm.dao.impl.GiftCertificateDAOImpl;
import com.epam.esm.embeddeddbconfig.EmbeddedDbConfig;
import com.epam.esm.model.GiftCertificate;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Tests CRUD operations on GiftCertificateTag entity.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EmbeddedDatabaseBuilder.class)
@ActiveProfiles("dev")
public class GiftCertificateTests {
    EmbeddedDatabase database;
    GiftCertificateDAOImpl giftCertificateDAOImpl = new GiftCertificateDAOImpl();

    /**
     * Sets up embedded database as H2 data source before each Test.
     */
    @BeforeEach
    public void setUp() {
        database = EmbeddedDbConfig.createH2DataSource();
    }

    /**
     * Tests findById method, which should return giftCertificate with given id (unique).
     * Checks if retrieved tag has correct name, description, price and duration according to sql script.
     */
    @Test
    public void findByIdTest() {
        // GIVEN
        giftCertificateDAOImpl.setDataSourceObject(database);
        // WHEN
        GiftCertificate giftCertificateRetrieved = giftCertificateDAOImpl.findById(1);
        GiftCertificate giftCertificate = new GiftCertificate("H&M gift card", "Gift card to the fashion store", 100.00, Duration.ofDays(90));
        giftCertificate.setId(1);
        giftCertificate.setCreateDate(LocalDateTime.parse("2022-06-22T18:31:44.574"));
        giftCertificate.setLastUpdateDate(null);
        // THEN
        assertEquals(giftCertificate, giftCertificateRetrieved);
    }

    /**
     * Tests findByTag method, which should return list of giftCertificates
     * with given tag name (unique).
     * Checks if number of records is correct according to sql script.
     */
    @Test
    public void findByTagTest() {
        // GIVEN
        giftCertificateDAOImpl.setDataSourceObject(database);
        // WHEN
        List <GiftCertificate> giftCertificatesInDb = giftCertificateDAOImpl.findByTag("shopping");
        // THEN
        assertEquals(2, giftCertificatesInDb.size());
    }

    /**
     * Tests findByNameOrDescription method, which should return list of giftCertificates
     * with given text included in name or description column.
     * Checks if number of records is correct according to sql script.
     */
    @Test
    public void findByNameOrDescriptionTest() {
        // GIVEN
        giftCertificateDAOImpl.setDataSourceObject(database);
        // WHEN
        List <GiftCertificate> giftCertificatesInDb = giftCertificateDAOImpl.findByNameOrDescription("store");
        // THEN
        assertEquals(2, giftCertificatesInDb.size());
    }

    /**
     * Tests findAll method, which should return list of all giftCertificates records in database.
     * Checks if number of records is correct according to sql script.
     */
    @Test
    public void findByAllTest() {
        // GIVEN
        giftCertificateDAOImpl.setDataSourceObject(database);
        // WHEN
        List <GiftCertificate> giftCertificatesInDb = giftCertificateDAOImpl.findAll();
        // THEN
        assertEquals(3, giftCertificatesInDb.size());
    }

    /**
     * Tests sortAscending method, which should return list of all giftCertificates records in database in ascending order.
     * Checks if number of records is correct according to sql script,
     * checks if the first and the last giftCertificate are in correct position.
     */
    @Test
    public void sortAscendingTest() {
        // GIVEN
        giftCertificateDAOImpl.setDataSourceObject(database);
        // WHEN
        List <GiftCertificate> giftCertificatesInDb = giftCertificateDAOImpl.sortAscending();
        // THEN
        assertEquals(3, giftCertificatesInDb.size());
        assertEquals("Gift card to the fashion store", giftCertificatesInDb.get(0).getDescription());
        assertEquals("Gift card to cafe", giftCertificatesInDb.get(2).getDescription());
    }


    @Test
    public void sortDescendingTest() {
        // GIVEN
        giftCertificateDAOImpl.setDataSourceObject(database);
        // WHEN
        List <GiftCertificate> giftCertificatesInDb = giftCertificateDAOImpl.sortDescending();
        // THEN
        assertEquals(3, giftCertificatesInDb.size());
        assertEquals("Gift card to the fashion store", giftCertificatesInDb.get(2).getDescription());
        assertEquals("Gift card to cafe", giftCertificatesInDb.get(0).getDescription());
    }

    /**
     * Tests createGiftCertificate method, which should insert new giftCertificate into database
     * and return inserted giftCertificate object.
     * Checks if list of all giftCertificate records contains inserted giftCertificate,
     * checks if the name and description of inserted giftCertificate is correct and
     * checks if the number of all giftCertificate records is correct (+1).
     */
    @Test
    public void createGiftCertificateTest() {
        // GIVEN
        giftCertificateDAOImpl.setDataSourceObject(database);
        GiftCertificate giftCertificate = new GiftCertificate("Paintball voucher", "2 hours of paintball match in Paintball-World", 49.99, Duration.ofDays(180));
        // WHEN
        GiftCertificate giftCertificateInserted = giftCertificateDAOImpl.createGiftCertificate(giftCertificate);
        // THEN
        assertTrue(giftCertificateDAOImpl.findAll().contains(giftCertificateInserted));
        assertEquals(giftCertificate, giftCertificateInserted);
        assertEquals(4, giftCertificateDAOImpl.findAll().size());
    }

    /**
     * Tests updateGiftCertificate method, which should update giftCertificate in database.
     * Checks if list of all giftCertificate records remains the same size,
     * checks if the name and description of updated giftCertificate is correct.
     */
    @Test
    public void updateTest() {
        // GIVEN
        giftCertificateDAOImpl.setDataSourceObject(database);
        // WHEN
        int initDatabaseSize = giftCertificateDAOImpl.findAll().size();
        GiftCertificate giftCertificate = giftCertificateDAOImpl.findById(1);
        giftCertificate.setDescription("Gift card to the fashion shop");
        giftCertificateDAOImpl.updateGiftCertificate(giftCertificate);
        // THEN
        assertEquals(initDatabaseSize, giftCertificateDAOImpl.findAll().size());
        assertEquals(giftCertificate, giftCertificateDAOImpl.findById(1));

    }

    /**
     * Tests deleteGiftCertificate method, which should remove giftCertificate from database.
     * Checks if the number of all giftCertificate records is correct (-1).
     * In H2 it was necessary to insert new giftCertificate before testing delete method.
     */
    @Test
    public void deleteTest() {
        // GIVEN
        giftCertificateDAOImpl.setDataSourceObject(database);
        // WHEN
        int dbSize = giftCertificateDAOImpl.findAll().size();
        giftCertificateDAOImpl.deleteGiftCertificate(1);
        // THEN
        assertEquals(dbSize-1, giftCertificateDAOImpl.findAll().size());
    }

    /**
     * Shuts down the database after each test.
     */
    @AfterEach
    public void shutDb() {
        database.shutdown();
    }
}
