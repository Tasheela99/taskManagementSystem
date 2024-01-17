package com.system.task.management.system.repo;

import com.system.task.management.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM user WHERE user_name=?1", nativeQuery = true)
    public User findByUsername(String username);

    @Query(value = "SELECT property_id FROM user WHERE property_id like ?% ORDER BY CAST(SUBSTRING(property_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    public String findLastId(String s, int i);
}
