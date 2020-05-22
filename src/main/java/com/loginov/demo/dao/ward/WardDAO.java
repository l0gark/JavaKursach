package com.loginov.demo.dao.ward;

import com.loginov.demo.model.Ward;
import org.springframework.stereotype.Component;

import java.util.List;

public interface WardDAO {
    Ward insert(Ward ward);

    List<Ward> getAllWards();

    Ward getWardById(Long id);
}
