package com.epam.DAO;

import com.epam.model.GiftCertificate;
import com.epam.model.Tag;

import java.util.List;

public interface GiftCertificateDAO {

    GiftCertificate findById(int id);

    List<GiftCertificate> findByTag(String tagName);

    List<GiftCertificate> findByNameOrDescription(String key);

    List<GiftCertificate> findAll();

    List<GiftCertificate> sortAscending();

    List<GiftCertificate> sortDescending();

    GiftCertificate createGiftCertificate(GiftCertificate giftCertificate);

    void updateGiftCertificate(GiftCertificate giftCertificate);

    void deleteGiftCertificate(int id);
}
