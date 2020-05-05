package com.loginov.demo.dao.ward;

import com.loginov.demo.model.Ward;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WardDAO {
    int insert(Ward ward);

    List<Ward> getAllWards();

    Ward getWardById(int id);
}
