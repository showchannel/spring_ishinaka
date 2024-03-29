package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookRequest implements Serializable {
 
  @NotEmpty(message = "名前を入力してください")
  private String title;
  
  private String isbn;

  private String thumbnail;
  
  private String description;
  
  private String author;
  
  private String publicationdate;
}