package com.loginov.demo;

import com.loginov.demo.dao.person.PersonDAO;
import com.loginov.demo.dao.ward.WardDAO;
import com.loginov.demo.model.Ward;
import com.loginov.demo.model.dto.PersonCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class AppStarter implements CommandLineRunner {
    public static final String[] LAST_NAMES = {"Логинов", "Потапов", "Хазыров", "Мельников", "Дмитриев"};
    public static final String[] FIRST_NAMES = {"Аркадий", "Потап", "Кирилл", "Виктор", "Александр"};
    public static final String[] FATHER_NAMES = {"Аркадьевич", "Потапович", "Кириллович", "Викторович", "Александрович"};
    private static final Random random = new Random();
    private static final String[] DIAGNOSES = {"Тиф", "Простатит", "Аденома простаты", "Рак", "Кашель", "Короновайрус"};

    @Autowired
    PersonDAO personDAO;

    @Autowired
    WardDAO wardDAO;

    @Override
    public void run(String... args) throws Exception {
        final List<Ward> wards = new ArrayList<>(Arrays.asList(
                new Ward(-1L, "Палата 1", 5),
                new Ward(-1L, "Палата 2", 5),
                new Ward(-1L, "Палата 3", 5),
                new Ward(-1L, "Палата 6", 5)
        ));


        final List<Long> wardIdList = wards.stream()
                .map(ward -> wardDAO.insert(ward).getId())
                .collect(Collectors.toList());

        for (int i = 0; i < 5; i++) {
            final Long wardId = wardIdList.get(random.nextInt(wardIdList.size()));
            personDAO.insert(randomPerson(wardId));
        }
    }

    private PersonCreateDto randomPerson(Long wardId) {
        final String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        final String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        final String fatherName = FATHER_NAMES[random.nextInt(FATHER_NAMES.length)];
        final String diagnosis = DIAGNOSES[random.nextInt(DIAGNOSES.length)];

        return new PersonCreateDto(firstName, lastName, fatherName, wardId, diagnosis);
    }
}
