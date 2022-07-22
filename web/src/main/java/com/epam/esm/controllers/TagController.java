package com.epam.esm.controllers;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.service.TagService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for {@code tag}  objects.
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    private final Logger logger = Logger.getLogger(getClass().getName());
    private final TagService tagService;
    private TagDTO tagDTO;

    @Autowired
    public TagController(TagService tagService, TagDTO tagDTO) {
        this.tagService = tagService;
        this.tagDTO = tagDTO;
    }

    /**
     * Finds {@code tag}  with requested id.
     * Handles GET http-request.
     * @param id        requested id
     * @return TagDTO   of given id
     */
    @GetMapping("/{id}")
    public TagDTO getById (@PathVariable int id){
       try {
           tagDTO = tagService.getById(id);
       } catch (Exception exception) {
           logger.error(exception.getMessage());
       }
        return tagDTO;
    }

    /**
     * Gets all {@code tag}.
     * Handles GET http-request.
     * @return tags   list of all tags in database
     */
    @GetMapping
    public List<TagDTO> getAll() {
        return tagService.getAll();
    }

    /**
     * Finds {@code tag}  with requested name.
     * Handles GET http-request.
     * @param name       requested id
     * @return TagDTO    of given name
     */
    @GetMapping("name/{name}")
    public TagDTO getByName (@PathVariable String name) {
        try {
            tagDTO = tagService.getByName(name);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return tagDTO;
    }

    /**
     * Creates new {@code tag}.
     * Handles POST http-request.
     * @param  tagDTO        tag to be inserted into database
     * @return TagDTO        tag that has been inserted into database
     */
    @PostMapping()
    public TagDTO createTag (@RequestBody TagDTO tagDTO) {
        TagDTO tagDTOCreated = null;
        try {
            tagDTOCreated = tagService.addTag(tagDTO);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return tagDTOCreated;
    }

    /**
     * Deletes {@code tag} with requested id.
     * Handles DELETE http-request.
     * @param id        requested id
     */
    @DeleteMapping("/{id}")
    public void deleteTag (@PathVariable int id) {
        try {
            tagService.deleteTag(id);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }
}
