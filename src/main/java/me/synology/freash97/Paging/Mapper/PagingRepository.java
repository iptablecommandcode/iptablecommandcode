package me.synology.freash97.Paging.Mapper;

import org.springframework.stereotype.Repository;

@Repository
public class PagingRepository {

    public int getTotalCount(){
        return 1;
    }
    public int getPageNum(){
        return 1;
    }

    public int getPageNo() {
        return 1;
    }

}
