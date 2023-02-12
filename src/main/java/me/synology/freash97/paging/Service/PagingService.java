package me.synology.freash97.paging.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.synology.freash97.paging.entity.PagingEntity;
import me.synology.freash97.paging.mapper.PagingRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagingService {

    private final PagingRepository pagingRepository;

    public PagingEntity getPageInfoByPageNo(int choicePageNo) {
        PagingEntity pageInfo = new PagingEntity();
        int startPageNo;
        int endPageNo;
        int totalNoticeCount = pagingRepository.getToTalCount();

        // 게시글 시작 ~ 끝 번호 체크
        setNoticeInfo(choicePageNo, pageInfo);

        // 페이지 시작 ~ 끝 번호 체크
        // 선택한 페이지번호가 끝 번호일 경우
        if (choicePageNo % pageInfo.getPAGE_SIZE() == 0) {
            startPageNo = choicePageNo - pageInfo.getPAGE_SIZE() -1;
            endPageNo = choicePageNo;

        } else {
            startPageNo = (choicePageNo / pageInfo.getPAGE_SIZE()) * pageInfo.getPAGE_SIZE() + 1;
            endPageNo = startPageNo + pageInfo.getPAGE_SIZE() - 1;

            // 게시글 갯수가 끝 번호보다 부족할 경우
            if (totalNoticeCount < endPageNo * pageInfo.getNOTICE_SIZE()) {
                endPageNo = totalNoticeCount / pageInfo.getNOTICE_SIZE();
            }
        }

        // 값 세팅
        pageInfo.setChoicePageNo(choicePageNo);

        pageInfo.setStartPageNo(startPageNo);
        pageInfo.setEndPageNo(endPageNo);


        // 이전, 다음버튼 활성화 여부 세팅
        setButtonInfo(totalNoticeCount, pageInfo);

        return pageInfo;

    }

    public PagingEntity getPageInfoByPrev(int currentPageNo) {
        PagingEntity pageInfo = new PagingEntity();
        int startPageNo;
        int endPageNo;
        int totalNoticeCount = pagingRepository.getToTalCount();

        // 페이지 시작 ~ 끝 번호 체크
        // 선택한 페이지번호가 끝 번호일 경우
        if (currentPageNo % pageInfo.getPAGE_SIZE() == 0 && currentPageNo > pageInfo.getPAGE_SIZE()) {
            startPageNo = currentPageNo / pageInfo.getPAGE_SIZE() -1;

        } else {
            startPageNo = currentPageNo / pageInfo.getPAGE_SIZE();
        }

        endPageNo = startPageNo + pageInfo.getPAGE_SIZE() - 1;

        // 게시글 시작 ~ 끝 번호 체크
        setNoticeInfo(currentPageNo, pageInfo);

        // 값 세팅
        pageInfo.setChoicePageNo(startPageNo);

        pageInfo.setStartPageNo(startPageNo);
        pageInfo.setEndPageNo(endPageNo);

        // 이전, 다음버튼 활성화 여부 세팅
        setButtonInfo(totalNoticeCount, pageInfo);

        return pageInfo;
    }

    private void setNoticeInfo(int pageNo, PagingEntity pageInfo) {
        int startNoticeNo = pageInfo.getNOTICE_SIZE() * (pageNo - 1) + 1;
        int endNoticeNo = startNoticeNo + 10;

        pageInfo.setStartNoticeNo(startNoticeNo);
        pageInfo.setEndNoticeNo(endNoticeNo);
    }

    private void setButtonInfo(int totalNoticeCount, PagingEntity pageInfo) {
        if (pageInfo.getStartPageNo() > pageInfo.getPAGE_SIZE()) {
            pageInfo.setPrev(true);
        }

        if (totalNoticeCount > pageInfo.getEndPageNo() * pageInfo.getNOTICE_SIZE()) {
            pageInfo.setNext(true);
        }
    }

}