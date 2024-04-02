package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

@Entity
@Data
@Table(name = "book")
public class Book implements Serializable {

	private static final long serialVersionUID = -870708489937857961L;

	@TableGenerator(name = "seqTable", table = "seq_table", pkColumnName = "seq_name", pkColumnValue = "word_seq", valueColumnName = "seq_value")
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "isbn")
	private String isbn;
	
	@Column(name = "thumbnail")
	private String thumbnail;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "publicationdate")
	private String publicationdate;
	
	/*@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "update_date")
	private Date updateDate;*/
	
	@Column(name = "borrowcount")
	private int borrowcount;
}
