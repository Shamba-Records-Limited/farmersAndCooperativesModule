package com.shabarecords.farmersmodule.repository.title;

import com.shabarecords.farmersmodule.models.title.Title;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */
public interface TitleRepository extends JpaRepository<Title, Integer> {
}
