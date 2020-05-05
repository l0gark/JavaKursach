package com.loginov.demo.dao.ward;

import com.loginov.demo.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class WardJdbcDAO implements WardDAO {
    private static final String WARD_TABLE_NAME = "ward";
    private static final String NAME = "name";
    private static final String MAX_COUNT = "max_count";

    private static final String INSERT_SQL = "INSERT INTO " + WARD_TABLE_NAME + "(" + NAME + ", " + MAX_COUNT + ") VALUES(?,?)";
    private static final String GET_ALL_SQL = "SELECT * from " + WARD_TABLE_NAME;
    private static final String GET_BY_ID_SQL = "SELECT * from " + WARD_TABLE_NAME + " where id = ?";

    @NonNull
    private final DataSource dataSource;

    @Autowired
    public WardJdbcDAO(@NonNull final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int insert(@NonNull final Ward ward) {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, ward.getName());
            preparedStatement.setInt(2, ward.getMaxCount());

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
    public List<Ward> getAllWards() {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL);
            final ResultSet result = preparedStatement.executeQuery();
            final List<Ward> wards = new ArrayList<>();
            while (result.next()) {
                wards.add(getWardFromResult(result));
            }
            return wards;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Ward getWardById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            final ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return getWardFromResult(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Ward getWardFromResult(@NonNull final ResultSet result) throws SQLException {
        return new Ward(
                result.getString(NAME),
                result.getInt(MAX_COUNT)
        );
    }
}
