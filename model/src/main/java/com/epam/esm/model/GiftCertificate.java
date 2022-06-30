package com.epam.esm.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * GiftCertificate entity with methods defined in Object class.
 */
public class GiftCertificate {

    private int id;
    private String name;
    private String description;
    private double price;
    private Duration duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getDuration() {
        return duration.toDays();
    }

    public void setDuration(long duration) {
        this.duration = Duration.ofDays(duration);
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificate that = (GiftCertificate) o;
        if (lastUpdateDate != null) {
            return id == that.id && Double.compare(that.price, price) == 0 && name.equals(that.name) && description.equals(that.description) && duration.equals(that.duration) && createDate.equals(that.createDate) && lastUpdateDate.equals(that.lastUpdateDate);
        } else {
            return id == that.id && Double.compare(that.price, price) == 0 && name.equals(that.name) && description.equals(that.description) && duration.equals(that.duration) && createDate.equals(that.createDate) && this.lastUpdateDate == null;
        }
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, duration, createDate, lastUpdateDate);
    }

    @Override
    public String toString() {
        return "GiftCertificate[" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", duration=" + getDuration() +
                ", createDate=" + getCreateDate() +
                ", lastUpdateDate=" + getLastUpdateDate() +
                ']';
    }

    public GiftCertificate() {
    }

    public GiftCertificate(String name, String description, double price, Duration duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }
}
