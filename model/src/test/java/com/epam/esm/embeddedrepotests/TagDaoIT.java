package com.epam.esm.embeddedrepotests;

import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.embeddeddbconfig.EmbeddedDbConfig;
import com.epam.esm.model.Tag;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Tests CRUD operations on Tag entity.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EmbeddedDbConfig.class}, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TagDaoIT {

    @Autowired
    TagDAOImpl tagDAOImpl = new TagDAOImpl();

    Tag tag;
    Tag tagInserted;
    Tag tagRetrieved;

    /**
     * Tests findById method, which should return tag with given id (unique).
     * Checks if retrieved tag has correct name according to sql script.
     */
    @Test
    @Order(1)
    @DisplayName("find tag by id test")
    public void findByIdShouldReturnTagWithExpectedName() {
        // GIVEN

        // WHEN
        tagRetrieved = tagDAOImpl.findById(1);
        // THEN
        assertEquals("sweets", tagRetrieved.getName());
    }

    /**
     * Tests findByName method, which should return tag with given name (unique).
     * Checks if retrieved tag has correct id according to sql script.
     */
    @Test
    @Order(2)
    @DisplayName("find tag by name test")
    public void findByNameShouldReturnSpecifiedTag() {
        // GIVEN
        tag = new Tag("fashion");
        tag.setId(2);
        // WHEN
        tagRetrieved = tagDAOImpl.findByName("fashion");
        // THEN
        assertEquals(tag.getId(), tagRetrieved.getId());
    }

    /**
     * Tests findAll method, which should return list of all tag records in database.
     * Checks if number of records is correct according to sql script.
     */
    @Test
    @Order(3)
    @DisplayName("fin all tags test")
    public void findAllShouldReturnListOfAllTags() {
        // GIVEN

        // WHEN
        List<Tag> tagsInDb = tagDAOImpl.findAll();
        // THEN
        assertEquals(6, tagsInDb.size());
    }

    /**
     * Tests createTag method, which should insert new tag into database
     * and return inserted tag object.
     * Checks if list of all tag records contains inserted tag,
     * checks if the number of all tag records is correct after insertion (+1),
     *
     */
    @Test
    @Order(4)
    @DisplayName("create tag test")
    public void specificTagIsPresentInDbAndCountOfTagInDbIsCorrect() {
        // GIVEN
        tag = new Tag("family time");
        // WHEN
        tagInserted = tagDAOImpl.createTag(tag);
        // THEN
        assertTrue(tagDAOImpl.findAll().contains(tagInserted));
        assertEquals(7, tagDAOImpl.findAll().size());
    }

    /**
     * Tests delete method, which should remove tag from database.
     * Checks if the number of all tag records is correct (-1),
     * checks if removed giftCertificate is not present in database.
     */
    @Test
    @Order(5)
    @DisplayName("delete tag test")
    public void countOfTagsInDbHasShrunkAfterDeletingAndTagIsNotPresentInDb() {
        // GIVEN
        tag = tagDAOImpl.findById(1);
        int dbSize = tagDAOImpl.findAll().size();
        int expectedDBSizeChange = 1;
        // WHEN
        tagDAOImpl.deleteTag(1);
        // THEN
        assertEquals(dbSize-expectedDBSizeChange, tagDAOImpl.findAll().size());
        assertFalse(tagDAOImpl.findAll().contains(tag));
    }
}
