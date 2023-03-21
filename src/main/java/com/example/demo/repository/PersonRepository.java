package com.example.demo.repository;

import com.example.demo.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    @Query(value = "select p.* " +
            "from person p " +
            "where p.phone=:phone", nativeQuery = true)
    PersonEntity findByPhone(String phone);

    @Query(value = "insert into person (address, created_date, email, last_modified_date, name, phone, total_amount, user_id) " +
                                "values (:address, :createdDate, :email, :lastModifiedDate, :name, :phone, :totalAmount, :userId)",
            nativeQuery = true)
    @Modifying
    void save(@Param("name") String name, @Param("address") String address,
              @Param("phone") String phone, @Param("email") String email,
              @Param("totalAmount") Long totalAmount, @Param("userId") Long userId,
              @Param("createdDate") Instant createdDate,
              @Param("lastModifiedDate") Instant lastModifiedDate);

    @Query(value = "update person set name = :name, last_modified_date= :lastModifiedDate," +
            "address = :address, email = :email, phone = :phone " +
            "where id = :id",
            nativeQuery = true)
    @Modifying
    void updatePersonInfo(@Param("id") Long id, @Param("name") String name,
                          @Param("address") String address, @Param("email") String email,
                          @Param("phone") String phone,
                          @Param("lastModifiedDate") Instant lastModifiedDate);

    @Query(value = "SELECT p.* FROM person p " +
            "WHERE (:address IS NULL OR upper(p.address) LIKE :address) " +
            "AND (:phone IS NULL OR upper(p.phone) LIKE :phone) " +
            "AND (:email IS NULL OR upper(p.email) LIKE :email) " +
            "AND (:name IS NULL OR upper(p.name) LIKE :name) " +
            "AND (:userId IS NULL OR p.user_id = :userId) " +
            "AND (:fromId IS NULL OR p.id >= :fromId) " +
            "AND (:toId IS NULL OR p.id <= :toId) " +
            "AND (:fromTotal IS NULL OR p.total_amount >= :fromTotal) " +
            "AND (:toTotal IS NULL OR p.total_amount <= :toTotal) " +
            "AND (:fromCreatedDate IS NULL OR p.created_date >= :fromCreatedDate) " +
            "AND (:toCreatedDate IS NULL OR p.created_date <= :toCreatedDate) " +
            "AND (:fromLastModifiedDate IS NULL OR p.last_modified_date >= :fromLastModifiedDate) " +
            "AND (:toLastModifiedDate IS NULL OR p.last_modified_date <= :toLastModifiedDate) ",
            nativeQuery = true)
    List<PersonEntity> findList(@Param("address") String address, @Param("phone") String phone,
                                @Param("email") String email, @Param("name") String name,
                                @Param("userId") Long userId,
                                @Param("fromId") Long fromId, @Param("toId") Long toId,
                                @Param("fromTotal") Long fromTotal, @Param("toTotal") Long toTotal,
                                @Param("fromCreatedDate") Instant fromCreatedDate, @Param("toCreatedDate") Instant toCreatedDate,
                                @Param("fromLastModifiedDate") Instant fromLastModifiedDate, @Param("toLastModifiedDate") Instant toLastModifiedDate);

    @Query(value = "select p.* " +
            "from person p " +
            "where p.id=:personId", nativeQuery = true)
    PersonEntity findByPersonId(Long personId);

    @Query(value = "update person set TOTAL_AMOUNT = :totalAmount, last_modified_date= :lastModifiedDate " +
            "where id = :id",
            nativeQuery = true)
    @Modifying
    void updatePersonTotalAmount(@Param("id") Long id, @Param("totalAmount") Long totalAmount,
                                 @Param("lastModifiedDate") Instant lastModifiedDate);

}