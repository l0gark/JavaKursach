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
    private static final String PERSON_TABLE_NAME = "people";

    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String FATHER_NAME = "father_name";
    private static final String DIAGNOSIS_ID = "diagnosis_id";
    private static final String WARD_ID = "ward_id";

    private static final String INSERT_SQL = "INSERT INTO " + PERSON_TABLE_NAME
            + "(" + FIRST_NAME + ", "
            + LAST_NAME + ", "
            + FATHER_NAME + ", "
            + DIAGNOSIS_ID + ", "
            + WARD_ID
            + ") VALUES(?,?,?,?,?)";
    private static final String GET_ALL_SQL = "SELECT * from " + PERSON_TABLE_NAME;
    private static final String GET_BY_ID_SQL = "SELECT * from (" + PERSON_TABLE_NAME + ") VALUES(?)";

    @NonNull
    private final DataSource dataSource;

    @NonNull
    private final WardDAO wardDAO;

    @NonNull
    private final DiagnosisDAO diagnosisDAO;

    @Autowired
    public PersonJdbcDAO(
            @NonNull final DataSource dataSource,
            @NonNull WardDAO wardDAO,
            @NonNull DiagnosisDAO diagnosisDAO) {
        this.dataSource = dataSource;
        this.wardDAO = wardDAO;
        this.diagnosisDAO = diagnosisDAO;
    }

    @Override
    public void insert(final Person person) {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getFatherName());
            preparedStatement.setInt(4, person.getDiagnosisId());
            preparedStatement.setInt(5, person.getWardId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PersonDto> getAllPersons() {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL);
            final ResultSet result = preparedStatement.executeQuery();
            final List<PersonDto> list = new ArrayList<>();
            while (result.next()) {
                list.add(getPersonFromResult(result));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public PersonDto getPersonById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            final ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return getPersonFromResult(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

    private PersonDto getPersonFromResult(@NonNull final ResultSet result) throws SQLException {
        final Person person = new Person(
                result.getString(FIRST_NAME),
                result.getString(LAST_NAME),
                result.getString(FATHER_NAME),
                result.getInt(WARD_ID),
                result.getInt(DIAGNOSIS_ID)
        );

        final Ward ward = wardDAO.getWardById(person.getWardId());
        final Diagnosis diagnosis = diagnosisDAO.getDiagnosisById(person.getDiagnosisId());

        return new PersonDto(
                person.getFirstName(),
                person.getLastName(),
                person.getFatherName(),
                ward,
                diagnosis
        );
    }
}
