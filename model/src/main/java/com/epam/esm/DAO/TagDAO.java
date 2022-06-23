package com.epam.esm.DAO;

import com.epam.esm.model.Tag;

import java.util.List;

public interface TagDAO {

    Tag findById(int id);

    Tag findByName(String name);

    List<Tag> findAll();

    Tag createTag(Tag tag);

    void deleteTag(int id);
}
