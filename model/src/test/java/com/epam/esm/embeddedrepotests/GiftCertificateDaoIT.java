package com.epam.esm.embeddedrepotests;

import com.epam.esm.dao.impl.GiftCertificateDAOImpl;
import com.epam.esm.embeddeddbconfig.EmbeddedDbConfig;
import com.epam.esm.model.GiftCertificate;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests CRUD operations on GiftCertificateTag entity.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EmbeddedDbConfig.class}, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GiftCertificateDaoIT {

    @Autowired
    GiftCertificateDAOImpl giftCertificateDAOImpl = new GiftCertificateDAOImpl();

    GiftCertificate giftCertificate;
    GiftCertificate giftCertificateInserted;
    GiftCertificate giftCertificateRetrieved;
    List <GiftCertificate> giftCertificatesInDb = new ArrayList<>();

    /**
     * Tests findById method, which should return giftCertificate with given id (unique).
     * Checks if retrieved giftCertificate is correct.
     */
    @Test
    @Order(1)
    @DisplayName("find gift certificate by id test")
    public void shouldReturnSpecifiedGiftCertificate() {
        // GIVEN

        // WHEN
        giftCertificateRetrieved = giftCertificateDAOImpl.findById(1);
        giftCertificate = new GiftCertificate("H&M gift card", "Gift card to the fashion store", 100.00, Duration.ofDays(90));
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
    @Order(2)
    @DisplayName("find gift certificates by tag test")
    public void shouldReturnListOfGiftCertificatesWithSpecifiedTag() {
        // GIVEN

        // WHEN
        giftCertificatesInDb = giftCertificateDAOImpl.findByTag("shopping");
        // THEN
        assertEquals(2, giftCertificatesInDb.size());
    }

    /**
     * Tests findByNameOrDescription method, which should return list of giftCertificates
     * with given text included in name or description column.
     * Checks if number of records is correct according to sql script.
     */
    @Test
    @Order(3)
    @DisplayName("find gift certificates by key word in name or description test")
    public void shouldReturnListOfGiftCertificatesWithKeyWordInNameOrDescription() {
        // GIVEN

        // WHEN
        giftCertificatesInDb = giftCertificateDAOImpl.findByNameOrDescription("store");
        // THEN
        assertEquals(2, giftCertificatesInDb.size());
    }

    /**
     * Tests findAll method, which should return list of all giftCertificates records in database.
     * Checks if number of records is correct according to sql script.
     */
    @Test
    @Order(4)
    @DisplayName("find all gift certificates test ")
    public void shouldReturnListOfAllGiftCertificates() {
        // GIVEN

        // WHEN
        giftCertificatesInDb = giftCertificateDAOImpl.findAll();
        // THEN
        assertEquals(3, giftCertificatesInDb.size());
    }


    /**
     * Tests sortAscending method, which should return list of all giftCertificates records in database in ascending order.
     * Checks if number of records is correct according to sql script,
     * checks if the first and the last giftCertificate are in correct position.
     */
    @Test
    @Order(5)
    @DisplayName("sort in ascending order test")
    public void checksIfListSizeAndASCOrderIsCorrect() {
        // GIVEN

        // WHEN
        giftCertificatesInDb = giftCertificateDAOImpl.sortAscending();
        // THEN
        assertEquals(3, giftCertificatesInDb.size());
        assertEquals("Gift card to cafe", giftCertificatesInDb.get(2).getDescription());
        assertEquals("Gift card to the fashion store", giftCertificatesInDb.get(0).getDescription());
    }

    /**
     * Tests sortDescending method, which should return list of all giftCertificates records in database in descending order.
     * Checks if number of records is correct according to sql script,
     * checks if the first and the last giftCertificate are in correct position.
     */
    @Test
    @Order(6)
    @DisplayName("sort in descending order test")
    public void checksIfListSizeAndDSCOrderIsCorrect() {
        // GIVEN

        // WHEN
        giftCertificatesInDb = giftCertificateDAOImpl.sortDescending();
        // THEN
        assertEquals(3, giftCertificatesInDb.size());
        assertEquals("Gift card to cafe", giftCertificatesInDb.get(0).getDescription());
        assertEquals("Gift card to the fashion store", giftCertificatesInDb.get(2).getDescription());
    }

    /**
     * Tests createGiftCertificate method, which should insert new giftCertificate into database
     * and return inserted giftCertificate object.
     * Checks if list of all giftCertificate records contains inserted giftCertificate,
     * checks if the name and description of inserted giftCertificate is correct,
     * checks if the number of all giftCertificate records is correct (+1).
     */
    @Test
    @Order(7)
    @DisplayName("create gift certificate test")
    public void specificGiftCertificateIsPresentInDbAndCountOfGiftCertificatesInDbIsCorrect() {
        // GIVEN
        giftCertificate = new GiftCertificate("Paintball voucher", "2 hours of paintball match in Paintball-World", 49.99, Duration.ofDays(180));
        int initialDbSize = giftCertificateDAOImpl.findAll().size();
        int expectedDBSizeChange = 1;
        // WHEN
        giftCertificateInserted = giftCertificateDAOImpl.createGiftCertificate(giftCertificate);
        // THEN
        assertTrue(giftCertificateDAOImpl.findAll().contains(giftCertificateInserted));
        assertEquals(giftCertificate, giftCertificateInserted);
        assertEquals(initialDbSize + expectedDBSizeChange, giftCertificateDAOImpl.findAll().size());
    }

    /**
     * Tests updateGiftCertificate method, which should update giftCertificate in database.
     * Checks if list of all giftCertificate records remains the same size,
     * checks if the name and description of updated giftCertificate is correct.
     */
    @Test
    @Order(8)
    @DisplayName("update gift certificate test")
    public void sizeOfDbIsNotChangedAndGiftCertificateIsCorrectlyUpdated() {
        // GIVEN
        int initDatabaseSize = giftCertificateDAOImpl.findAll().size();
        giftCertificate = giftCertificateDAOImpl.findById(1);
        giftCertificate.setDescription("Gift card to the fashion shop");
        // WHEN
        giftCertificateDAOImpl.updateGiftCertificate(giftCertificate);
        // THEN
        assertEquals(initDatabaseSize, giftCertificateDAOImpl.findAll().size());
        assertEquals(giftCertificate, giftCertificateDAOImpl.findById(1));
    }

    /**
     * Tests deleteGiftCertificate method, which should remove giftCertificate from database.
     * Checks if the number of all giftCertificate records is correct (-1),
     * checks if removed giftCertificate is not present in database.
     */
    @Test
    @Order(9)
    @DisplayName("delete gift certificate test")
    public void countOfGiftCertificatesInDbHasShrunkAfterDeletingAndGiftCertificateIsNotPresentInDb() {
        // GIVEN
        int dbSize = giftCertificateDAOImpl.findAll().size();
        int expectedDBSizeChange = 1;
        // WHEN
        giftCertificate = giftCertificateDAOImpl.findById(1);
        giftCertificateDAOImpl.deleteGiftCertificate(1);
        // THEN
        assertEquals(dbSize-expectedDBSizeChange, giftCertificateDAOImpl.findAll().size());
        assertFalse(giftCertificateDAOImpl.findAll().contains(giftCertificate));
    }
}
