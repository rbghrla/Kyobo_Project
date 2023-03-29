package com.kyobo.koreait.controller;

import com.kyobo.koreait.domain.dtos.BookDTO;
import com.kyobo.koreait.domain.dtos.UploadFileDTO;
import com.kyobo.koreait.domain.vos.BookVO;
import com.kyobo.koreait.service.MainService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@Log4j2
@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    @PermitAll
    @GetMapping("/main/maptest")
    public void maptest(){

    }

    @Value("${com.kyobo.koreait.upload.path}")
    private String uploadPath;

    @ResponseBody
    @PostMapping("/upload")
    public void upload_file(UploadFileDTO uploadFileDTO){
        if(uploadFileDTO.getFiles() != null){
            uploadFileDTO.getFiles().forEach(multipartFile -> {
                log.info(multipartFile.getOriginalFilename());
            });
        }
    }





    @PermitAll
    @GetMapping("/")
    public String main(){
        log.info(" ====== main페이지 ========");
        return "/main/home";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/main/cart")
    public void cart(){
        log.info(" ====== 장바구니 페이지 (cart) ========");
    }

    @ResponseBody
    @GetMapping("/main/books")
    public BookDTO get_all_books_by_condition(
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "rating") String order,
            @RequestParam(defaultValue = "8") int pagePerArticle,
            @RequestParam(defaultValue = "1") int nowPage
    ){
        return mainService.get_all_books_by_condition(searchKeyword, order, pagePerArticle, nowPage);
    }


    // 책 제목 혹은 이미지 클릭했을 때 책의 상세정보 페이지로 이동하는 메소드
    @PermitAll
    @GetMapping("/main/details/{bookISBN}")
    public String main_book_details(
            @PathVariable String bookISBN,
            Model model
    ){
        log.info(" main_book_details - 책 상세정보 페이지 로딩중.. ");
        BookVO bookVO = mainService.get_book_by_isbn(bookISBN);
        log.info(" 가져온 책 정보 => " + bookVO);
        if(bookVO == null){
            log.info(" 해당 책 페이지가 존재하지 않음.. ");
            return "/error/main"; //에러 페이지로 이동하도록 함
        }

        model.addAttribute("bookVO", bookVO);
        return "/main/details";
    }
    
    //방금 주문 내역 확인 페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/main/order")
    public void order_page(){
        log.info(" ===== order_page - 주문 완료 페이지 ===== ");
    }
    
    
    
    
}









