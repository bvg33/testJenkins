package com.epam.ems.dto.lists;

import com.epam.ems.dto.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
public class TagList extends RepresentationModel<TagList> {
    private List<Tag> tags;
}
