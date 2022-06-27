package com.epam.esm.embeddedrepotests;

import com.epam.esm.dao.GiftCertificateTagDAO;
import com.epam.esm.dao.impl.GiftCertificateTagDAOImpl;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.configuration.embeddeddb.EmbeddedDbConfig;
import com.epam.esm.model.Tag;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Tests CRUD operations on Tag entity.
 */
public class TagTests {
    EmbeddedDatabase database;
    TagDAOImpl tagDAOImpl = new TagDAOImpl();

    /**
     * Sets up embedded database as H2 data source before each Test.
     */
    @BeforeEach
    public void setUp() {
        database = EmbeddedDbConfig.createH2DataSource();
    }

    /**
     * Tests findById method, which should return tag with given id (unique).
     * Checks if retrieved tag has correct name according to sql script.
     */
    @Test
    public void findByIdTagTest() {
        // GIVEN
        tagDAOImpl.setDataSourceObject(database);
        // WHEN
        Tag tagRetrieved = tagDAOImpl.findById(1);
        // THEN
        assertEquals("sweets", tagRetrieved.getName());
    }

    /**
     * Tests findByName method, which should return tag with given name (unique).
     * Checks if retrieved tag has correct id according to sql script.
     */
    @Test
    public void findByNameTagTest() {
        // GIVEN
        tagDAOImpl.setDataSourceObject(database);
        Tag tag = new Tag("fashion");
        tag.setId(2);
        // WHEN
        Tag tagRetrieved = tagDAOImpl.findByName("fashion");
        // THEN
        assertEquals(tag.getId(), tagRetrieved.getId());
    }

    /**
     * Tests findAll method, which should return list of all tag records in database.
     * Checks if number of records is correct according to sql script.
     */
    @Test
    public void findAllTagTest() {
        // GIVEN
        tagDAOImpl.setDataSourceObject(database);
        // WHEN
        List<Tag> tagsInDb = tagDAOImpl.findAll();
        // THEN
        assertEquals(6, tagsInDb.size());
    }

    /**
     * Tests createTag method, which should insert new tag into database
     * and return inserted tag object.
     * Checks if list of all tag records contains inserted tag,
     * checks if the name of inserted tag is correct and
     * checks if the number of all tag records is correct (+1).
     */
    @Test
    public void createTagTest() {
        // GIVEN
        tagDAOImpl.setDataSourceObject(database);
        Tag tag = new Tag("family time");
        // WHEN
        Tag tagInserted = tagDAOImpl.createTag(tag);
        // THEN
        assertTrue(tagDAOImpl.findAll().contains(tagInserted));
        assertEquals(tag.getName(), tagInserted.getName());
        assertEquals(7, tagDAOImpl.findAll().size());
    }

    /**
     * Tests delete method, which should remove tag from database.
     * Checks if the number of all tag records is correct (-1).
     * In H2 it was necessary to insert new tag before testing delete method.
     */
    @Test
    public void deleteTagTest() {
        // GIVEN
        tagDAOImpl.setDataSourceObject(database);
        // WHEN
//        tagDAOImpl.createTag(new Tag("outdoor fun"));
        int dbSize = tagDAOImpl.findAll().size();
        tagDAOImpl.deleteTag(1);
        // THEN
        assertEquals(dbSize-1, tagDAOImpl.findAll().size());
    }

    /**
     * Shuts down the database after each test.
     */
    @AfterEach
    public void shutDb() {
        database.shutdown();
    }

}
