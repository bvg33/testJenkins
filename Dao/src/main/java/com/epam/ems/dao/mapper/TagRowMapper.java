package com.epam.ems.dao.mapper;

import com.epam.ems.dto.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TagRowMapper implements RowMapper<Tag> {
    private static final String NAME = "tag_name";
    private static final String ID = "id";

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        String name = rs.getString(NAME);
        int id = rs.getInt(ID);
        return new Tag(id, name);
    }
}
