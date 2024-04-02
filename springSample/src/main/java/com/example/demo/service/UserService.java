package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;

public interface UserService {

	public Page<User> findPagenatedList(String name, int pageNo, int pageSize, String sortField, String sortDirection);

	public User findById(Long id);

	public void update(UserUpdateRequest userUpdateRequest);

	public void create(UserRequest userRequest);

	public void delete(Long id);
}