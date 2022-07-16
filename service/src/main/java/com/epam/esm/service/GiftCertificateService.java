package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.mapper.GiftCertificateMapper;
import com.epam.esm.model.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Presents access to service operations with {@code GiftCertificate}.
 */
@Service
public class GiftCertificateService {

    private final GiftCertificateDAO giftCertificateDAO;
    private final GiftCertificateMapper giftCertificateMapper;

    @Autowired
    public GiftCertificateService(GiftCertificateDAO giftCertificateDAO, GiftCertificateMapper giftCertificateMapper) {
        this.giftCertificateDAO = giftCertificateDAO;
        this.giftCertificateMapper = giftCertificateMapper;
    }

    /**
     * Finds {@code giftCertificate} of given id value.
     * @param id                            int id value
     * @return gift certificate             gift certificate of given id value
     * @throws IllegalArgumentException     in case of invalid param
     */
    public GiftCertificateDTO getById(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Id value must be greater than 0.");
        }
        GiftCertificate giftCertificate = giftCertificateDAO.findById(id);
        return giftCertificateMapper.toDTO(giftCertificate);

    }

    /**
     * Finds {@code giftCertificate} assigned to given tagName value.
     * @param tagName               String value of tag's name
     * @return giftCertificates     list of giftCertificates assigned to given tag's name
     */
    public List<GiftCertificateDTO> getByTag(String tagName){
        List<GiftCertificate> giftCertificates = giftCertificateDAO.findByTag(tagName);
        List<GiftCertificateDTO> giftCertificateDTOs = new ArrayList<>();
        giftCertificates.forEach(giftCertificate -> giftCertificateDTOs.add(giftCertificateMapper.toDTO(giftCertificate)));
        return giftCertificateDTOs;
    }

    /**
     * Finds all {@code giftCertificate} by part of name or description.
     * @param key                  String value of desired name/description word
     * @return giftCertificates    list of giftCertificates containing key word in their name or description
     */
    public List<GiftCertificateDTO> getByNameOrdDescription(String key) {
        List<GiftCertificate> giftCertificates = giftCertificateDAO.findByNameOrDescription(key);
        List<GiftCertificateDTO> giftCertificateDTOs = new ArrayList<>();
        giftCertificates.forEach(giftCertificate -> giftCertificateDTOs.add(giftCertificateMapper.toDTO(giftCertificate)));
        return giftCertificateDTOs;
    }

    /**
     * Finds all {@code giftCertificate}.
     * @return lists of giftCertificates    all giftCertificates
     */
    public List<GiftCertificateDTO> getAll() {
        List<GiftCertificate> giftCertificates = giftCertificateDAO.findAll();
        List<GiftCertificateDTO> giftCertificateDTOs = new ArrayList<>();
        giftCertificates.forEach(giftCertificate -> giftCertificateDTOs.add(giftCertificateMapper.toDTO(giftCertificate)));
        return giftCertificateDTOs;
    }

    /**
     * Sorts all {@code giftCertificate} by ascending order.
     * @return giftCertificates in ascending order
     */
    public List<GiftCertificateDTO> sortAscending() {
        List<GiftCertificate> giftCertificates = giftCertificateDAO.sortAscending();
        List<GiftCertificateDTO> giftCertificateDTOs = new ArrayList<>();
        giftCertificates.forEach(giftCertificate -> giftCertificateDTOs.add(giftCertificateMapper.toDTO(giftCertificate)));
        return giftCertificateDTOs;
    }

    /**
     * Sorts all {@code giftCertificate} by descending order.
     * @return giftCertificates in descending order
     */
    public List<GiftCertificateDTO> sortDescending() {
        List<GiftCertificate> giftCertificates = giftCertificateDAO.sortDescending();
        List<GiftCertificateDTO> giftCertificateDTOs = new ArrayList<>();
        giftCertificates.forEach(giftCertificate -> giftCertificateDTOs.add(giftCertificateMapper.toDTO(giftCertificate)));
        return giftCertificateDTOs;
    }

    /**
     * Creates new {@code giftCertificate} entity.
     * @param giftCertificateDTO            GiftCertificate instance to be inserted into database
     * @return giftCertificate              GiftCertificate instance with specified id value that has been inserted into database
     * @throws IllegalArgumentException     in case of invalid param
     */
    public GiftCertificateDTO addGiftCertificate(GiftCertificateDTO giftCertificateDTO) throws IllegalArgumentException {
        if (giftCertificateDTO.getName() == null || giftCertificateDTO.getName().trim().isEmpty()
        || giftCertificateDTO.getDescription() == null || giftCertificateDTO.getDescription().trim().isEmpty()
        || giftCertificateDTO.getPrice() == 0 || giftCertificateDTO.getDuration() == 0) {
            throw new IllegalArgumentException("At least one of given parameter is null or empty.");
        }
        GiftCertificate giftCertificate = giftCertificateMapper.toModel(giftCertificateDTO);
        GiftCertificate giftCertificateInserted = giftCertificateDAO.createGiftCertificate(giftCertificate);
        return giftCertificateMapper.toDTO(giftCertificateInserted);
    }

    /**
     * Updates {@code giftCertificate} contained in database.
     * @param giftCertificateDTO    GiftCertificate instance to be updated in database
     */
    public void updateGiftCertificate(GiftCertificateDTO giftCertificateDTO) {
        GiftCertificate giftCertificate = giftCertificateMapper.toModel(giftCertificateDTO);
        giftCertificateDAO.updateGiftCertificate(giftCertificate);
    }

    /**
     * Deletes {@code giftCertificate} of given id value.
     * @param id    int id value of giftCertificate instance to be removed
     */
    public void deleteGiftCertificate(int id) {
        giftCertificateDAO.deleteGiftCertificate(id);
    }
}
