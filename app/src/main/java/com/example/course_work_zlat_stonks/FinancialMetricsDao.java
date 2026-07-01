package com.example.course_work_zlat_stonks;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface FinancialMetricsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FinancialMetricsEntity entity);

    @Query("DELETE FROM cw_database")
    void deleteAll();

    @Query("SELECT * FROM cw_database WHERE Ticker = :ticker")
    FinancialMetricsEntity getByTicker(String ticker);

    @Query("SELECT COUNT(*) > 0 FROM cw_database WHERE Ticker = :ticker")
    boolean exists(String ticker);

    @Query("SELECT Ticker FROM cw_database ORDER BY Ticker")
    List<String> getAllTickers();

    @Query("SELECT * FROM cw_database WHERE Ticker LIKE '%' || :query || '%'")
    List<FinancialMetricsEntity> searchTickers(String query);

}