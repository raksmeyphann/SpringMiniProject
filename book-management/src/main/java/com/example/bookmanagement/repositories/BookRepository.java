package com.example.bookmanagement.repositories;

import com.example.bookmanagement.models.Book;
import com.example.bookmanagement.repositories.providers.BookProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository {



    @SelectProvider(type = BookProvider.class, method = "getAllProvider")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name")
    })
    List<Book> getAll();


    @Select("select * from tb_book where id=#{id}")
    Book findOne(@Param("id") Integer id);


    @Update("update tb_book set title=#{title}, author=#{author}, publisher=#{publisher}, thumbnail=#{thumbnail} where id=#{id}")
    boolean update(Book book);

    @Delete("delete from tb_book where id=#{id}")
    boolean remove(Integer id);


    @Select("select count(*) from tb_book")
    Integer count();


    @InsertProvider(type = BookProvider.class, method = "create")
    boolean create(Book book);


}
