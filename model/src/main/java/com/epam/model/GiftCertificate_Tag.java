package com.epam.model;

import java.util.Objects;

public class GiftCertificate_Tag {

    private int id;
    private int giftCertificateId;
    private int tagId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGiftCertificateId() {
        return giftCertificateId;
    }

    public void setGiftCertificateId(int giftCertificateId) {
        this.giftCertificateId = giftCertificateId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificate_Tag that = (GiftCertificate_Tag) o;
        return id == that.id && giftCertificateId == that.giftCertificateId && tagId == that.tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, giftCertificateId, tagId);
    }

    @Override
    public String toString() {
        return "GiftCertificate_Tag[" +
                "id=" + id +
                ", giftCertificateId=" + giftCertificateId +
                ", tagId=" + tagId +
                ']';
    }
}
