package com.seaz.proyectospringboot.repository;

import com.seaz.proyectospringboot.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {


}
