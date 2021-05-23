package com.epam.ems.dto.lists;

import com.epam.ems.dto.UserOrderInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
public class UserOrderInfoList extends RepresentationModel {
    private List<UserOrderInfo> userOrderInfo;
}
