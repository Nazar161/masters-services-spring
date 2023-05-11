package com.example.individual_spring;


import com.example.individual_spring.stores.Master;
import com.example.individual_spring.stores.Service;
import com.example.individual_spring.stores.data.MasterRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJdbcTest
public class MasterRepositoryIntegrationTest {
    @Autowired
    private MasterRepository subject;

    @After
    public void tearDown() throws Exception {
        subject.deleteAll();
    }

    @Test
    public void shouldAddAndFetchMaster() throws Exception {
        List<Service> services = new ArrayList<>();
        Master master = new Master(null, "Test Name", "test post", "11111111111", services);
        subject.save(master);

        Optional<Master> maybePeter = subject.findById(3L);

        assertThat(maybePeter, is(Optional.of(master)));
    }
}
