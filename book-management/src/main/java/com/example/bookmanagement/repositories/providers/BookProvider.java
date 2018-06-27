package com.example.bookmanagement.repositories.providers;

import com.example.bookmanagement.models.Book;
import org.apache.ibatis.jdbc.SQL;

public class BookProvider {

        public String getAllProvider(){
            return new SQL(){{
                SELECT("*");
                FROM("tb_book");
                ORDER_BY("id desc");
            }}.toString();
        }


        public String create(Book book){
        return new SQL(){{
            INSERT_INTO("tb_book");
            VALUES("title", "#{title}");
            VALUES("author", "#{author}");
            VALUES("publisher", "#{publisher}");
            VALUES("thumbnail", "#{thumbnail}");

        }}.toString();
    }
        public String remove(Integer id){
            return new SQL(){{
                DELETE_FROM ("tb_book");
                WHERE("id=#{id}");
            }}.toString();
        }



}
