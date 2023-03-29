package com.kyobo.koreait.mapper;

import com.kyobo.koreait.domain.dtos.CartDTO;
import com.kyobo.koreait.domain.dtos.HeartDTO;
import com.kyobo.koreait.domain.vos.BookVO;
import com.kyobo.koreait.domain.vos.CartVO;
import com.kyobo.koreait.domain.vos.PaymentVO;
import com.kyobo.koreait.domain.vos.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM `user_tbl` WHERE `email` = #{email}")
    UserVO get_user(String email);

    //회원가입
    @Insert("INSERT INTO `user_tbl` VALUES " +
            "(#{email}, #{password}, #{name}, #{birth}, #{phone}, default)")
    void register_user(UserVO userVO);

    //장바구니 내용 전부 가져오기
    List<CartDTO> get_cart(String userEmail);

    //장바구니 추가
    boolean insert_books_in_cart(List<CartVO> cartVOS);

    //장바구니에 있는 책의 개수 수정하기
    boolean modify_book_count_in_cart(CartVO cartVO);

    //장바구니에 있는 책의 개수 수정하기 => 원하는 개수만큼 + / -
    boolean modify_book_count_in_cart_by_count(List<CartVO> cartVOS);

    //장바구니에 있는 책들 장바구니에서 삭제하기
    boolean delete_book_in_cart(List<CartVO> cartVOS);

    //현재 찜 목록 전체 가져오기
    List<BookVO> get_books_in_heart(String userEmail);

    //찜하기
    boolean insert_books_in_heart(List<HeartDTO> heartDTOS);

    //결제 내역 가져오기
    List<PaymentVO> get_payment(String userEmail);

    //결제 내역 추가하기
    boolean insert_payment(PaymentVO paymentVO);
    
    //주문 내역 가져오기
    List<CartDTO> get_order(String orderNo);
    
    //주문 내역 추가하기
    boolean insert_order(List<CartVO> cartVOS);




}






