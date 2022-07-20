package com.example.eduproject.repository;

import com.example.eduproject.newdish.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("delete from Dish dish where dish.name=:name")
    Integer deleteDish(@Param("name") String name);

    @Query("select case when count(dish)> 0 then true else false end from Dish dish where lower(dish.name) like lower(:name)")
    boolean existsDishByName(@Param("name") String name);

    @Query("select dish from Dish dish where dish.name=:name")
    Dish findDishByName(@Param("name") String name);
}
