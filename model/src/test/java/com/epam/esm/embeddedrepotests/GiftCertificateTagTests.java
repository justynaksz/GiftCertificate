package com.epam.esm.embeddedrepotests;

import com.epam.esm.dao.impl.GiftCertificateTagDAOImpl;
import com.epam.esm.configuration.embeddeddb.EmbeddedDbConfig;
import com.epam.esm.model.GiftCertificateTag;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests CRUD operations on GiftCertificateTag entity.
 */
public class GiftCertificateTagTests {
    EmbeddedDatabase database;
    GiftCertificateTagDAOImpl giftCertificateTagDAOImpl = new GiftCertificateTagDAOImpl();

    /**
     * Sets up embedded database as H2 data source before each Test.
     */
    @BeforeEach
    public void setUp() {
        database = EmbeddedDbConfig.createH2DataSource();
    }

    /**
     * Tests findGiftCertificateTagById method, which should return
     * giftCertificateTag with given id (unique).
     * Checks if retrieved giftCertificateTag has correct giftCertificateId and tagId according to sql script.
     */
    @Test
    public void findGiftCertificateTagByIdTest() {
        // GIVEN
        giftCertificateTagDAOImpl.setDataSourceObject(database);
        // WHEN
        GiftCertificateTag giftCertificateTagRetrieved = giftCertificateTagDAOImpl.findGiftCertificateTagById(1);
        // THEN
        assertEquals(1, giftCertificateTagRetrieved.getGiftCertificateId());
        assertEquals(2, giftCertificateTagRetrieved.getTagId());
    }

    /**
     * Tests findGiftCertificateTagByGiftCertificateId method,
     * which should return list of all giftCertificateTags records in database
     * with given giftCertificateId.
     * Checks if number of records is correct according to sql script.
     */
    @Test
    public void findGiftCertificateTagByGiftCertificateIdTest() {
        // GIVEN
        giftCertificateTagDAOImpl.setDataSourceObject(database);
        //WHEN
        List<GiftCertificateTag> byGiftCertificateId = giftCertificateTagDAOImpl.findGiftCertificateTagByGiftCertificateId(1);
        // THEN
        assertEquals(3, byGiftCertificateId.size());
    }

    /**
     * Tests findGiftCertificateTagByTagId method,
     * which should return list of all giftCertificateTags records in database
     * with given tagId.
     * Checks if number of records is correct according to sql script.
     */
    @Test
    public void findGiftCertificateTagByTagIdTest() {
        // GIVEN
        giftCertificateTagDAOImpl.setDataSourceObject(database);
        //WHEN
        List<GiftCertificateTag> byTagId = giftCertificateTagDAOImpl.findGiftCertificateTagByTagId(1);
        // THEN
        assertEquals(1, byTagId.size());
    }

    /**
     * Tests findAllGiftCertificateTag method, which should return
     * list of all giftCertificateTag records in database.
     * Checks if number of records is correct according to sql script.
     */
    @Test
    public void findAllGiftCertificateTagIdTest() {
        // GIVEN
        giftCertificateTagDAOImpl.setDataSourceObject(database);
        //WHEN
        List<GiftCertificateTag> allGiftCertificates = giftCertificateTagDAOImpl.findAllGiftCertificateTag();
        // THEN
        assertEquals(7, allGiftCertificates.size());
    }

    /**
     * Tests createGiftCertificateTag method, which should insert
     * new giftCertificateTag into database
     * and return inserted giftCertificateTag object.
     * Checks if list of all giftCertificateTag records contains inserted giftCertificateTag,
     * checks if the inserted giftCertificate is equal to the one with the same id in database.
     */
    @Test
    public void createGiftCertificateTagTest() {
        // GIVEN
        giftCertificateTagDAOImpl.setDataSourceObject(database);
        GiftCertificateTag giftCertificateTag = new GiftCertificateTag(2, 1);
        //WHEN
        GiftCertificateTag giftCertificateTagInserted = giftCertificateTagDAOImpl.createGiftCertificateTag(giftCertificateTag);
        // THEN
        assertTrue(giftCertificateTagDAOImpl.findAllGiftCertificateTag().contains(giftCertificateTagInserted));
        assertEquals(giftCertificateTag.getGiftCertificateId(), giftCertificateTagInserted.getGiftCertificateId());
        assertEquals(giftCertificateTag.getTagId(), giftCertificateTagInserted.getTagId());
        assertEquals(8, giftCertificateTagDAOImpl.findAllGiftCertificateTag().size());
    }

    /**
     * Tests deleteGiftCertificateTag method,
     * which should remove giftCertificateTag from database.
     * Checks if the number of all tag records is correct (-1).
     * In H2 it was necessary to insert new giftCertificateTag before testing delete method.
     */
    @Test
    public void deleteGiftCertificateTagTest() {
        // GIVEN
        giftCertificateTagDAOImpl.setDataSourceObject(database);
        // WHEN
        int dbSize = giftCertificateTagDAOImpl.findAllGiftCertificateTag().size();
        giftCertificateTagDAOImpl.deleteGiftCertificateTag(3);
        // THEN
        assertEquals(6, giftCertificateTagDAOImpl.findAllGiftCertificateTag().size());
    }

    /**
     * Shuts down the database after each test.
     */
    @AfterEach
    public void shutDb() {
        database.shutdown();
    }
}
