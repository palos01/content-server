package ai.dnai.cs.dao.jpa;

import ai.dnai.cs.domain.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {
    List<Hotel> findByCity(String city);
    Page findAll(Pageable pageable);
}
