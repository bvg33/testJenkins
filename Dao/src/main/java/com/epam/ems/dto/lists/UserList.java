package com.epam.ems.dto.lists;

import com.epam.ems.dto.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
public class UserList extends RepresentationModel<UserList> {
    private List<AppUser> users;
}
