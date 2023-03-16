package com.kyobo.koreait.domain.vos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class BookVO {

    private String ISBN;
    private String title;
    private String contents;
    private String author;
    private String publisher;
    private int introduce;
    private int price;
}
