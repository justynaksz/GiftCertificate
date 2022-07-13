package com.epam.esm.dao;

import com.epam.esm.model.Tag;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CRD operations for Tag entity.
 */
@Repository
public interface TagDAO {

    /**
     * Finds tag of given id value.
     * @param  id                               int id value
     * @return tag                              tag of given id value
     * @throws EmptyResultDataAccessException   in case of tag not found
     */
    Tag findById(int id) throws EmptyResultDataAccessException;

    /**
     * Finds tags of given name.
     * @param  name                               String name value
     * @return tag                                of given name
     * @throws EmptyResultDataAccessException   in case of tag not found
     */
    Tag findByName(String name) throws EmptyResultDataAccessException;

    /**
     * Finds all tags.
     * @return tags    list of all tags
     */
    List<Tag> findAll();

    /**
     * Creates new tag entity.
     * @param  tag    Tag instance to be inserted into database
     * @return tag    Tag instance with specified id value that has been inserted into database
     */
    Tag createTag(Tag tag);

    /**
     * Deletes tag of given id value.
     * @param id     int id value of tag instance to be removed
     * @throws EmptyResultDataAccessException   in case of tag not found
     */
    void deleteTag(int id) throws EmptyResultDataAccessException;
}
