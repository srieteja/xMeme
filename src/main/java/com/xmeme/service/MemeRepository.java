package com.xmeme.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.net.URL;
import java.util.UUID;

@Repository
public interface MemeRepository extends PagingAndSortingRepository<MemeType, UUID> {

    @Query(value = "SELECT * FROM meme_table", nativeQuery=true)
    public Page<MemeType> getLatestHundred(Pageable page);

    @Modifying
    @Query(value = "UPDATE meme_table meme SET URL=?2 WHERE ID=?1", nativeQuery = true)
    void updateUrl(UUID id, URL url);

    @Modifying
    @Query(value = "UPDATE meme_table SET CAPTION=?2 WHERE ID=?1", nativeQuery = true)
    void updateCaption(UUID id, String caption);

    @Modifying
    @Query(value = "UPDATE meme_table SET URL=?2, CAPTION=?3 WHERE ID=?1", nativeQuery = true)
    void updateUrlCaption(UUID id, URL url, String caption);

    @Query(value="SELECT * FROM meme_table WHERE submittedby = ?1 and url = ?2 and caption = ?3", nativeQuery = true)
    public MemeType ifMemeExists(String submittedby, URL url, String caption);

}
