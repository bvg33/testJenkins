package com.epam.ems.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Certificate {
    private int id;
    private String name;
    private String description;
    private int price;
    private int duration;
    private String createDate;
    private String lastUpdateDate;
    private List<Tag> tags;

    public Certificate(String name, String description, int price, int duration, String createDate, String lastUpdateDate, List<Tag> tags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tags = new ArrayList<>(tags);
    }

    public Certificate(int id, String name, String description, int price, int duration, String createDate, String lastUpdateDate, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tags = new ArrayList<>(tags);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public List<Tag> getTags() {
        return new ArrayList<>(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate that = (Certificate) o;
        return price == that.price && duration == that.duration && Objects.equals(name, that.name) && Objects.equals(description, that.description) &&
                Objects.equals(createDate, that.createDate) && Objects.equals(lastUpdateDate, that.lastUpdateDate)
                && tags.equals(that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, duration, createDate, lastUpdateDate, tags);
    }
}
