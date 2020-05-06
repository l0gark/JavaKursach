package com.loginov.demo.dao.person;

import com.loginov.demo.dao.diagnosis.DiagnosisDAO;
import com.loginov.demo.dao.ward.WardDAO;
import com.loginov.demo.model.Diagnosis;
import com.loginov.demo.model.Person;
import com.loginov.demo.model.Ward;
import com.loginov.demo.model.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PersonJdbcDAO implements PersonDAO {

    @Override
    public void insert(final Person person) {

    }

    @Override
    public List<PersonDto> getAllPersons() {
        return Collections.emptyList();
    }

    @Override
    public PersonDto getPersonById(int id) {
        return null;
    }

}
