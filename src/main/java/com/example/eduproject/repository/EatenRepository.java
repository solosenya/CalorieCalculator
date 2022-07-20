package com.example.eduproject.repository;

import com.example.eduproject.eaten.EatenFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface EatenRepository extends JpaRepository<EatenFood, Integer> {
    @Modifying
    @Transactional
    @Query("delete from EatenFood food where food.food=:food")
    Integer deleteEaten(@Param("food") String food);
}
