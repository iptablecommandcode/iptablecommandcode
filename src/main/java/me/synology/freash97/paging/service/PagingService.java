package me.synology.freash97.paging.service;

import lombok.RequiredArgsConstructor;
import me.synology.freash97.paging.entity.PagingEntity;
import me.synology.freash97.paging.mapper.PagingRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagingService {

    private final PagingRepository pagingRepository;

    public PagingEntity getPageInfoByPageNo(int currentPageNo, int totalNoticeCount) {
        PagingEntity pageInfo = new PagingEntity();
        int startPageNo;

        // 페이지 시작 ~ 끝 번호 체크
        // 선택한 페이지번호가 끝 번호일 경우
        if (currentPageNo % pageInfo.getPAGE_SIZE() == 0) {
            startPageNo = currentPageNo - pageInfo.getPAGE_SIZE() + 1;

        } else {
            startPageNo = (currentPageNo / pageInfo.getPAGE_SIZE()) * pageInfo.getPAGE_SIZE() + 1;

        }

        pageInfo.setCurrentPageNo(currentPageNo);
        pageInfo.setStartPageNo(startPageNo);

        setNoticeInfo(totalNoticeCount, pageInfo);
        setEndPageInfo(totalNoticeCount, pageInfo);

        // 이전, 다음버튼 활성화 여부 세팅
        setButtonInfo(totalNoticeCount, pageInfo);

        return pageInfo;

    }

    public PagingEntity getPageInfoByPrev(int currentPageNo, int totalNoticeCount) {
        PagingEntity pageInfo = new PagingEntity();
        int startPageNo;

        // 페이지 시작 ~ 끝 번호 체크
        // 선택한 페이지번호가 끝 번호일 경우
        if (currentPageNo % pageInfo.getPAGE_SIZE() == 0) {
            startPageNo = currentPageNo - (2 * pageInfo.getPAGE_SIZE()) + 1 ;

        } else {
            startPageNo = (currentPageNo / pageInfo.getPAGE_SIZE() - 1) * pageInfo.getPAGE_SIZE() + 1;
        }

        pageInfo.setCurrentPageNo(currentPageNo);
        pageInfo.setStartPageNo(startPageNo);

        setNoticeInfo(totalNoticeCount, pageInfo);
        setEndPageInfo(totalNoticeCount, pageInfo);

        // 이전, 다음버튼 활성화 여부 세팅
        setButtonInfo(totalNoticeCount, pageInfo);

        return pageInfo;
    }

    public PagingEntity getPageInfoByNext(int currentPageNo, int totalNoticeCount) {
        PagingEntity pageInfo = new PagingEntity();
        int startPageNo;

        if (currentPageNo % pageInfo.getPAGE_SIZE() == 0) {
            startPageNo = currentPageNo + 1 ;

        } else {
            startPageNo = (currentPageNo / pageInfo.getPAGE_SIZE() + 1) * pageInfo.getPAGE_SIZE() + 1;
        }

        pageInfo.setCurrentPageNo(currentPageNo);
        pageInfo.setStartPageNo(startPageNo);

        setNoticeInfo(totalNoticeCount, pageInfo);
        setEndPageInfo(totalNoticeCount, pageInfo);
        setButtonInfo(totalNoticeCount, pageInfo);

        return pageInfo;
    }

    private void setEndPageInfo(int totalNoticeCount, PagingEntity pageInfo) {
        pageInfo.setEndPageNo(pageInfo.getStartPageNo() + pageInfo.getPAGE_SIZE() - 1);

        // 게시글 갯수가 끝 번호보다 부족할 경우
        if (totalNoticeCount < pageInfo.getEndPageNo() * pageInfo.getNOTICE_SIZE()) {
            pageInfo.setEndPageNo(totalNoticeCount / pageInfo.getNOTICE_SIZE());
        }
    }

    private void setNoticeInfo(int totalNoticeCount, PagingEntity pageInfo) {
        int startNoticeNo = pageInfo.getNOTICE_SIZE() * (pageInfo.getCurrentPageNo() - 1) + 1;
        int endNoticeNo = startNoticeNo + 9;

        if (totalNoticeCount < endNoticeNo) {
            endNoticeNo = totalNoticeCount;
        }

        pageInfo.setStartNoticeNo(startNoticeNo);
        pageInfo.setEndNoticeNo(endNoticeNo);
    }

    private void setButtonInfo(int totalNoticeCount, PagingEntity pageInfo) {
        if (pageInfo.getStartPageNo() > pageInfo.getPAGE_SIZE()) {
            pageInfo.setPrev(true);
        }

        if (totalNoticeCount > pageInfo.getEndPageNo() * pageInfo.getNOTICE_SIZE()) {
            pageInfo.setNext(true);

            if(totalNoticeCount == pageInfo.getEndNoticeNo()) {
                pageInfo.setNext(false);
            }
        }
    }

}