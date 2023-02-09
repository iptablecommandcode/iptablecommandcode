package me.synology.freash97.Paging.Service;

import lombok.NoArgsConstructor;
import me.synology.freash97.Notice.Entity.NoticeEntity;
import me.synology.freash97.Paging.Mapper.PagingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class PagingService {
    private PagingRepository pagingRepository;

    public int getPageNum(){

       int Notices = pagingRepository.getTotalCount();



        pagingRepository.getPageNum();
        return 1;
    }

    public int getPageNo() {
        pagingRepository.getPageNo();
        return 1;
    }
}
