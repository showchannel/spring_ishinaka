package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

	Optional<UserInfo> findById(String loginId);
	
	/**
	 * ログインIDの部分一致検索を行います。
	 * 
	 * @param loginId ログインID
	 * @return 検索でヒットしたユーザー情報のリスト
	 */
	List<UserInfo> findByLoginIdLike(String loginId);

}
