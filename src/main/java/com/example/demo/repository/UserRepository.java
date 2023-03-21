package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM USER WHERE USERNAME = :username", nativeQuery = true)
    UserEntity findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM USER WHERE EMAIL = :email", nativeQuery = true)
    UserEntity findByEmail(@Param("email") String email);

    @Query(value = "INSERT INTO USER (`NAME`, EMAIL, USERNAME, PASSWORD, AMOUNT) " +
            "values (:name, :email, :username, :password, :amount)",
            nativeQuery = true)
    @Modifying
    void save(@Param("name") String name, @Param("email") String email,
              @Param("username") String username, @Param("password") String password,
              @Param("amount") Long amount);

    @Query(value = "UPDATE USER SET PASSWORD = :password WHERE ID = :id",
            nativeQuery = true)
    @Modifying
    void updatePassword(@Param("password") String password, @Param("id") Long id);

    @Query(value = "UPDATE USER SET NAME = :name, EMAIL =:email WHERE ID = :id",
            nativeQuery = true)
    @Modifying
    void updateProfile(@Param("name") String name, @Param("email") String email, @Param("id") Long id);
}
