package com.seaz.proyectospringboot.service;

import com.seaz.proyectospringboot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAll();

    public Page<User> findAll(Pageable pegeable);

    public Optional<User> findById(Long id);

    public User save(User user);
    public void delete(Long id);
}
