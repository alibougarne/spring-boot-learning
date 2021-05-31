package com.real360.demo.features.roles.roleRepository;

import com.real360.demo.features.roles.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }

    @Test
    void itShouldGetRoleByName(){
        // given
        Role superAdmin = new Role();
//        superAdmin = roleRepository.findAll().get(0);
//        if(superAdmin == null){
        superAdmin.setName("superAdmin");
        roleRepository.save(superAdmin);
        // when
        Optional<Role> admin = roleRepository.findRoleByName("superAdmin");
        // then
        assertThat(admin).containsSame(superAdmin);
    }

}