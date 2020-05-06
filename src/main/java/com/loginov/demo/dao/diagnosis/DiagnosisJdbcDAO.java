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
    @Override
    public int insert(@NonNull final Diagnosis diagnosis) {
        return -1;
    }

    @Override
    public List<Diagnosis> getAllDiagnosis() {

        return Collections.emptyList();
    }

    @Override
    public Diagnosis getDiagnosisById(int id) {

        return null;
    }

    private static Diagnosis getDiagnosisFromResult(@NonNull final ResultSet result) throws SQLException {
        return new Diagnosis(result.getString("name"));
    }
}
