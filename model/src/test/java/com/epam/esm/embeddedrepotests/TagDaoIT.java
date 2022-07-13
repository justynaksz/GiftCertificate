package com.epam.esm.embeddedrepotests;

import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.model.Tag;
import com.epam.esm.service.DataSourceConfig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceConfig.class}, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TagDaoIT {

    @Autowired
    TagDAOImpl tagDAOImpl;
    @Autowired
    Tag tag;
    @Autowired
    Tag tagInserted;
    @Autowired
    Tag tagRetrieved;

    @Nested
    @DisplayName("find by id tests")
    class findByIdTest {

        @Test
        @Order(1)
        @DisplayName("tag is correctly found")
        void findByIdShouldReturnCorrectTag() {
            // GIVEN
            int id = 1;
            // WHEN
            tagRetrieved = tagDAOImpl.findById(id);
            // THEN
            assertEquals("sweets", tagRetrieved.getName());
        }

        @Test
        @Order(2)
        @DisplayName("tag of given id doesn't exist")
        void findByIdShouldThrowExceptionIfTagDoesNotExist() {
            // GIVEN
            int id = 999;
            // WHEN

            // THEN
            assertThrows(EmptyResultDataAccessException.class, () -> tagDAOImpl.findById(id));
        }
    }

    @Nested
    @DisplayName("find by name tests")
    class findByName {

        @Test
        @Order(3)
        @DisplayName("tag is correctly found")
        void findByNameShouldReturnCorrectTag() {
            // GIVEN
            tag.setId(2);
            tag.setName("fashion");
            // WHEN
            tagRetrieved = tagDAOImpl.findByName("fashion");
            // THEN
            assertEquals(tag.getId(), tagRetrieved.getId());
        }

        @Test
        @Order(4)
        @DisplayName("tag of given name doesn't exist")
        void findByNameShouldThrowExceptionIfTagDoesNotExist() {
            // GIVEN
            String name = "photo";
            // WHEN

            // THEN
            assertThrows(EmptyResultDataAccessException.class, () -> tagDAOImpl.findByName(name));
        }
    }

    @Test
    @Order(5)
    @DisplayName("find all tags test")
    void findAllShouldReturnListOfAllTags() {
        // GIVEN

        // WHEN
        List<Tag> tagsInDb = tagDAOImpl.findAll();
        System.out.println(tagsInDb);
        // THEN
        assertEquals(6, tagsInDb.size());
    }

    @Test
    @Order(6)
    @DisplayName("create tag test")
    void specificTagIsPresentInDbAndCountOfTagInDbIsCorrect() {
        // GIVEN
        tag.setName("family time");
        // WHEN
        tagInserted = tagDAOImpl.createTag(tag);
        // THEN
        assertTrue(tagDAOImpl.findAll().contains(tagInserted));
        assertEquals(7, tagDAOImpl.findAll().size());
    }

    @Nested
    @DisplayName("delete tag tests")
    class deleteTagTest {

        @Test
        @Order(7)
        @DisplayName("tag is correctly removed")
        void deleteTagShouldRemoveTagOfGivenIdFromDatabase(){
            // GIVEN
            int id = 5;
            tag = tagDAOImpl.findById(id);
            int dbSize = tagDAOImpl.findAll().size();
            int expectedDBSizeChange = 1;
            // WHEN
            tagDAOImpl.deleteTag(id);
            // THEN
            assertEquals(dbSize-expectedDBSizeChange, tagDAOImpl.findAll().size());
            assertFalse(tagDAOImpl.findAll().contains(tag));}

        @Test
        @Order(8)
        @DisplayName("delete non existing tag test")
        void deleteNonExistingTagShouldTrowException(){
            // GIVEN
            int id = 999;
            // WHEN

            // THEN
            assertThrows(EmptyResultDataAccessException.class, () -> tagDAOImpl.deleteTag(id));
        }
    }
}
