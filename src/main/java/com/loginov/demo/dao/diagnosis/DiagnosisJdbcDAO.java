package com.loginov.demo.dao.diagnosis;

import com.loginov.demo.model.Diagnosis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DiagnosisJdbcDAO implements DiagnosisDAO {
    private static final String DIAGNOSIS_TABLE_NAME = "diagnosis";
    private static final String NAME = "name";

    private static final String INSERT_SQL = "INSERT INTO " + DIAGNOSIS_TABLE_NAME + " (" + NAME + ") VALUES(?)";

    private static final String GET_ALL_SQL = "SELECT * from " + DIAGNOSIS_TABLE_NAME;
    private static final String GET_BY_ID_SQL = "SELECT * from " + DIAGNOSIS_TABLE_NAME + " where id = ?";

    @NonNull
    private final DataSource dataSource;

    @Autowired
    public DiagnosisJdbcDAO(@NonNull final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int insert(@NonNull final Diagnosis diagnosis) {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, diagnosis.getName());

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 1) {
                final ResultSet result = preparedStatement.getGeneratedKeys();
                if (result.next()) {
                    return result.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Diagnosis> getAllDiagnosis() {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL);
            final ResultSet result = preparedStatement.executeQuery();

            final List<Diagnosis> diagnoses = new ArrayList<>();
            while (result.next()) {
                diagnoses.add(getDiagnosisFromResult(result));
            }
            return diagnoses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Diagnosis getDiagnosisById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            final ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return getDiagnosisFromResult(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Diagnosis getDiagnosisFromResult(@NonNull final ResultSet result) throws SQLException {
        return new Diagnosis(result.getString(NAME));
    }
}
