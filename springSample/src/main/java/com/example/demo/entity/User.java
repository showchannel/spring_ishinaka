package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

/**
 * ユーザー情報 Entity
 */
@Entity
@Data
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -870708489937857961L;

	/**
	 * ID
	 */
	@TableGenerator(name = "seqTable", table = "seq_table", pkColumnName = "seq_name", pkColumnValue = "word_seq", valueColumnName = "seq_value")
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 名前
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 住所
	 */
	@Column(name = "address")
	private String address;
	/**
	 * 電話番号
	 */
	@Column(name = "phone")
	private String phone;
	/**
	 * 更新日時
	 */
	@Column(name = "update_date")
	private Date updateDate;
	/**
	 * 登録日時
	 */
	@Column(name = "create_date")
	private Date createDate;
}