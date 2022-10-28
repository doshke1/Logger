package com.example.Logger.repository;

import com.example.Logger.model.db.Log;
import com.example.Logger.model.db.LogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query("SELECT log FROM Log log WHERE (:message is null or log.message = :message) and (:logType is null or log.logType = :logType) and " +
            "(:dateFrom is null or log.createdDate > :dateFrom ) and (:dateTo is null or log.createdDate < :dateTo)")
    List<Log> findByAllParams(@Param("message") String message, @Param("logType") LogType logType,
                              @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

    @Query("SELECT COUNT(log) FROM Log log WHERE log.client.clientId = :clientId")
    Long countAllByClientId(@Param("clientId") Long clientId);
}
