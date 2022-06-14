package com.epam.DAO;

import com.epam.model.Tag;

import java.util.List;

public interface TagDAO {

    Tag findById(int id);

    List<Tag> findByName(String name);

    List<Tag> findAll();

    Tag createTag(Tag tag);

    void deleteTag(int id);
}
