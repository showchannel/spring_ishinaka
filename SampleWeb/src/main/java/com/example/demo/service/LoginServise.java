package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServise {
	private final UserInfoRepository repository;
}
