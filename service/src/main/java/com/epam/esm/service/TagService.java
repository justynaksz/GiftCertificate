package com.epam.esm.service;

import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.exceptions.AlreadyExistException;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Presents access to service operations with {@code Tag}.
 */
@Service
public class TagService {

    @Autowired
    private TagDAOImpl tagDAO;

    @Autowired
    private TagMapper tagMapper;


    /**
     * Finds {@code tag} of given id value.
     * @param  id                           int id value
     * @return tag                          tag of given id value
     * @throws IllegalArgumentException     in cas of invalid param
     */
    public TagDTO getById(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Id value must be greater than 0.");
        }
        Tag tag = tagDAO.findById(id);
        return tagMapper.toDTO(tag);
    }

    /**
     * Finds all {@code tag} of given name.
     * @param  name      String name value
     * @return tags      tag of given name
     */
    public TagDTO getByName(String name) {
        Tag tag = tagDAO.findByName(name);
        return tagMapper.toDTO(tag);
    }

    /**
     * Finds all {@code tag}.
     * @return tags    list of all tags
     */
    public List<TagDTO> getAll() {
        List<Tag> tags = tagDAO.findAll();
        List<TagDTO> tagDTOs = new ArrayList<>();
        tags.forEach(tag -> tagDTOs.add(tagMapper.toDTO(tag)));
        return tagDTOs;
    }

    /**
     * Creates new {@code tag} entity.
     * @param  tagDTO                      TagDTO instance to be inserted into database
     * @return tagDTO                      TagDTO instance with specified id value that has been inserted into database
     * @throws IllegalArgumentException    in case of null or empty tag name
     * @throws AlreadyExistException       in case tag of this name already exists in database
     */
    public TagDTO addTag(TagDTO tagDTO) throws IllegalArgumentException, AlreadyExistException {
        if (tagDTO.getName() == null || tagDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("At least one of given parameter is null or empty.");
        }
        Tag tag = tagMapper.toModel(tagDTO);
        for (Tag tagInDb : tagDAO.findAll()) {
            if (tagInDb.getName().equals(tag.getName())) {
                throw new AlreadyExistException("Tag of given name already exists.");
            }
        }
        Tag tagInserted = tagDAO.createTag(tag);
        return tagMapper.toDTO(tagInserted);
    }

    /**
     * Deletes {@code tag} of given id value.
     * @param id     int id value of tag instance to be removed
     */
    public void deleteTag(int id) {
        tagDAO.deleteTag(id);
    }
}
