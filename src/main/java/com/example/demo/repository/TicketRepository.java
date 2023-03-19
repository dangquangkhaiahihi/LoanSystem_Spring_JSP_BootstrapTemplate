package com.example.demo.repository;

import com.example.demo.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    @Modifying
    @Query(value = "insert into ticket (AMOUNT, IS_PLUS, NOTE, DATE_OF_TRANS, IMAGE_URL, " +
            "PERSON_ID, CREATED_BY, CREATED_DATE, LAST_MODIFIED_BY, LAST_MODIFIED_DATE) " +
            "values (:amount, :isPlus, :note, :dateOfTrans, :imgUrl, :personId, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate)",
            nativeQuery = true)
    void save(@Param("amount") Long amount, @Param("isPlus") Boolean isPlus, @Param("note") String note,
              @Param("dateOfTrans") Instant dateOfTrans, @Param("imgUrl") String imgUrl,
              @Param("personId") Long personId,
              @Param("createdBy") String createdBy, @Param("createdDate") Instant createdDate,
              @Param("lastModifiedBy") String lastModifiedBy, @Param("lastModifiedDate") Instant lastModifiedDate);

    @Override
    @Query(value = "select * from ticket where id = :id", nativeQuery = true)
    Optional<TicketEntity> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM ticket "
            + "WHERE (:isPlus IS NULL OR IS_PLUS = :isPlus) "
            + "AND (:note IS NULL OR UPPER(note) LIKE :note) "
            + "AND (:id IS NULL OR id = :id) "
            + "AND (:personId IS NULL OR PERSON_ID = :personId) "
            + "AND (:fromAmount IS NULL OR amount >= :fromAmount) "
            + "AND (:toAmount IS NULL OR amount <= :toAmount) "
            + "AND (:fromCreatedDateInstant IS NULL OR created_date >= :fromCreatedDateInstant) "
            + "AND (:toCreatedDateInstant IS NULL OR created_date <= :toCreatedDateInstant) "
            + "AND (:fromDateOfTransInstant IS NULL OR DATE_OF_TRANS >= :fromDateOfTransInstant) "
            + "AND (:toDateOfTransInstant IS NULL OR DATE_OF_TRANS <= :toDateOfTransInstant)",
            countQuery = "SELECT COUNT(*) FROM ticket "
                    + "WHERE (:isPlus IS NULL OR IS_PLUS = :isPlus) "
                    + "AND (:note IS NULL OR UPPER(note) LIKE :note) "
                    + "AND (:id IS NULL OR id = :id) "
                    + "AND (:personId IS NULL OR PERSON_ID = :personId) "
                    + "AND (:fromAmount IS NULL OR amount >= :fromAmount) "
                    + "AND (:toAmount IS NULL OR amount <= :toAmount) "
                    + "AND (:fromCreatedDateInstant IS NULL OR created_date >= :fromCreatedDateInstant) "
                    + "AND (:toCreatedDateInstant IS NULL OR created_date <= :toCreatedDateInstant) "
                    + "AND (:fromDateOfTransInstant IS NULL OR date_of_trans >= :fromDateOfTransInstant) "
                    + "AND (:toDateOfTransInstant IS NULL OR date_of_trans <= :toDateOfTransInstant)",
            nativeQuery = true)
    Page<TicketEntity> findList(
            @Param("isPlus") Boolean isPlus,
            @Param("note") String note,
            @Param("id") Long id,
            @Param("personId") Long personId,
            @Param("fromAmount") Long fromAmount,
            @Param("toAmount") Long toAmount,
            @Param("fromCreatedDateInstant") Instant fromCreatedDateInstant,
            @Param("toCreatedDateInstant") Instant toCreatedDateInstant,
            @Param("fromDateOfTransInstant") Instant fromDateOfTransInstant,
            @Param("toDateOfTransInstant") Instant toDateOfTransInstant,
            Pageable pageable);

    @Modifying
    @Query(value = "update ticket set IMAGE_URL = :imgUrl where id = :id",
            nativeQuery = true)
    void updateImg(@Param("imgUrl") String imgUrl, @Param("id") Long id);
}
