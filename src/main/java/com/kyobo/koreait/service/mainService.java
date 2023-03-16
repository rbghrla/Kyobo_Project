package com.kyobo.koreait.service;

import com.kyobo.koreait.domain.vos.BookVO;
import com.kyobo.koreait.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class mainService {
    @Autowired
    private MainMapper mapper;

    public List<BookVO> get_all_books(){
        return mapper.get_all_books();
    }

    //장바구니 추가
    public boolean insert_cart(List<BookVO> bookVOS){
        return mapper.insert_cart(bookVOS);
    }
}
