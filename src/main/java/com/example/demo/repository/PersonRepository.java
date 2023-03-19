package com.example.demo.repository;

import com.example.demo.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    @Query(value = "select p.* " +
            "from person p " +
            "where p.phone=:phone", nativeQuery = true)
    Optional<PersonEntity> findByPhone(String phone);

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
}