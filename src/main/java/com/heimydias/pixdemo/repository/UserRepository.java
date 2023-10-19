package com.heimydias.pixdemo.repository;

import com.heimydias.pixdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByChavePix(String chavePix);
    boolean existsById(Long id);

    User findByChavePix(String pixChave);

}
