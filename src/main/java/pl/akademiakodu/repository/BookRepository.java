package pl.akademiakodu.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pl.akademiakodu.model.Book;
import pl.akademiakodu.model.SearchModel;

import java.util.List;

/**
 * Created by Norbert on 2017-10-29.
 */

public interface BookRepository extends CrudRepository<Book, Integer>{

    List<Book> findBooksByTitle(String title);
   

}
