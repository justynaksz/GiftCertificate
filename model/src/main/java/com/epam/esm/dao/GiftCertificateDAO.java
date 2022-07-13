package com.epam.esm.dao;

import com.epam.esm.model.GiftCertificate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CRUD operations for GiftCertificate entity.
 */
@Repository
public interface GiftCertificateDAO {

    /**
     * Finds giftCertificate of given id value.
     * @param id                                int id value
     * @return giftCertificate                  giftCertificate of given id value
     * @throws EmptyResultDataAccessException   in case of gift certificate not found
     */
    GiftCertificate findById(int id) throws EmptyResultDataAccessException;

    /**
     * Finds giftCertificates assigned to given tagName value.
     * @param tagName                        String value of tag's name
     * @return giftCertificates lists        giftCertificates assigned to given tag's name
     */
    List<GiftCertificate> findByTag(String tagName);

    /**
     * Finds giftCertificates by part of name or description.
     * @param key                           String value of requested name/description word
     * @return giftCertificates lists       giftCertificates containing key word in their name or description
     */
    List<GiftCertificate> findByNameOrDescription(String key);

    /**
     * Finds all giftCertificates.
     * @return giftCertificates lists    all giftCertificates
     */
    List<GiftCertificate> findAll();

    /**
     * Sorts giftCertificates by ascending order.
     * @return giftCertificates lists in ascending order
     */
    List<GiftCertificate> sortAscending();

    /**
     * Sorts giftCertificates by descending order.
     * @return giftCertificates lists in descending order
     */
    List<GiftCertificate> sortDescending();

    /**
     * Creates new giftCertificate entity.
     * @param giftCertificate     GiftCertificate instance to be inserted into database
     * @return giftCertificate    GiftCertificate instance with specified id value that has been inserted into database
     */
    GiftCertificate createGiftCertificate(GiftCertificate giftCertificate);

    /**
     * Updates giftCertificate contained in database.
     * @param giftCertificate                       GiftCertificate instance to be updated in database
     * @throws EmptyResultDataAccessException       in case of gift certificate not found
     */
    void updateGiftCertificate(GiftCertificate giftCertificate) throws EmptyResultDataAccessException;

    /**
     * Deletes giftCertificate of given id value.
     * @param  id                                   int id value of giftCertificate instance to be removed
     * @throws EmptyResultDataAccessException       in case of gift certificate not found
     */
    void deleteGiftCertificate(int id) throws EmptyResultDataAccessException;
}
