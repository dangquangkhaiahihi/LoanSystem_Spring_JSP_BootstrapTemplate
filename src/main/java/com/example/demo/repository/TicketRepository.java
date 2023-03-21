package com.example.demo.repository;

import com.example.demo.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity,Long> {
    @Query(value = "SELECT * FROM ticket "
            + "WHERE (:isPlus IS NULL OR IS_PLUS = :isPlus) "
            + "AND (:note IS NULL OR UPPER(note) LIKE :note) "
            + "AND (:id IS NULL OR id = :id) "
            + "AND (:personId IS NULL OR PERSON_ID = :personId) "
            + "AND (:fromAmount IS NULL OR amount >= :fromAmount) "
            + "AND (:toAmount IS NULL OR amount <= :toAmount) "
            + "AND (:fromDateOfTrans IS NULL OR DATE_OF_TRANS >= :fromDateOfTrans) "
            + "AND (:toDateOfTrans IS NULL OR DATE_OF_TRANS <= :toDateOfTrans) "
            + "AND (:fromLastModifiedDate IS NULL OR LAST_MODIFIED_DATE >= :fromLastModifiedDate) "
            + "AND (:toLastModifiedDate IS NULL OR LAST_MODIFIED_DATE <= :toLastModifiedDate)",
            nativeQuery = true)
    List<TicketEntity> findList(
            @Param("isPlus") Boolean isPlus,
            @Param("note") String note,
            @Param("id") Long id,
            @Param("personId") Long personId,
            @Param("fromAmount") Long fromAmount,
            @Param("toAmount") Long toAmount,
            @Param("fromDateOfTrans") Instant fromDateOfTrans,
            @Param("toDateOfTrans") Instant toDateOfTrans,
            @Param("fromLastModifiedDate") Instant fromLastModifiedDate,
            @Param("toLastModifiedDate") Instant toLastModifiedDate);

    @Modifying
    @Query(value = "insert into ticket (AMOUNT, IS_PLUS, NOTE, IMAGE_URL, " +
            "PERSON_ID, DATE_OF_TRANS, LAST_MODIFIED_DATE) " +
            "values (:amount, :isPlus, :note, :imgUrl, :personId, :dateOfTrans, :lastModifiedDate)",
            nativeQuery = true)
    void save(@Param("amount") Long amount, @Param("isPlus") Boolean isPlus, @Param("note") String note,
              @Param("imgUrl") String imgUrl,
              @Param("personId") Long personId,
              @Param("dateOfTrans") Instant dateOfTrans,
              @Param("lastModifiedDate") Instant lastModifiedDate);
}
