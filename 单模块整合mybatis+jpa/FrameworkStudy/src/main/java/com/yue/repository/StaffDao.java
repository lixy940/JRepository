package com.yue.repository;

import com.yue.entity.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface StaffDao extends PagingAndSortingRepository<Staff, Long> {

	public List<Staff> findByUsername(String username);
	@Query(value = "SELECT * FROM staff WHERE id = ?1", nativeQuery = true)
	public Staff findById(long id);
}
