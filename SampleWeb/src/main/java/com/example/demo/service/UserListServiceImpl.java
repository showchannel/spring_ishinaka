package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.example.demo.constant.ExcuteResult;
import com.example.demo.dto.UserListInfo;
import com.example.demo.dto.UserSearchInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー一覧画面Service実装クラス
 * 
 * @author ys-fj
 *
 */
@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {

	/** ユーザー情報テーブルDAO */
	private final UserInfoRepository repository;

	/** Dozer Mapper */
	private final Mapper mapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserListInfo> editUserList() {
		return toUserListInfos(repository.findAll());
	}

	/**
	 * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します。
	 * 
	 * @param userInfos ユーザー情報EntityのList
	 * @return ユーザ一覧情報DTOのList
	 */
	private List<UserListInfo> toUserListInfos(List<UserInfo> userInfos) {
		var userListInfos = new ArrayList<UserListInfo>();
		for (UserInfo userInfo : userInfos) {
			var userListInfo = mapper.map(userInfo, UserListInfo.class);
			userListInfos.add(userListInfo);
		}
		return userListInfos;
	}

	/* 変更済み */
	public List<UserListInfo> editUserListByParam(UserSearchInfo dto) {
		var userInfo = mapper.map(dto, UserInfo.class);
		return toUserListInfos(findUserInfoByParam(userInfo));
	}

	/**
	 * ユーザー情報の条件検索を行い、検索結果を返却します。
	 * 
	 * @param form 入力情報
	 * @return 検索結果
	 */
	private List<UserInfo> findUserInfoByParam(UserInfo userInfo) {
		var loginIdParam = AppUtil.addWildcard(userInfo.getLoginId());
		return repository.findByLoginIdLike(loginIdParam);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExcuteResult deleteUserInfoById(String loginId) {
		var userInfo = repository.findById(loginId);
		if (userInfo.isEmpty()) {
			return ExcuteResult.ERROR;
		}
		repository.deleteById(loginId);
		return ExcuteResult.SUCCEED;
	}
}
