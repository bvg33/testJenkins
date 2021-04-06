package com.epam.ems.dto;


import java.util.Objects;

public class Tag {
    private int id;

    private String tagName;

    public Tag(int id, String name) {
        this.id = id;
        this.tagName = name;
    }

    public Tag(String name) {
        this.tagName = name;
    }

    public int getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(tagName, tag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }
}
