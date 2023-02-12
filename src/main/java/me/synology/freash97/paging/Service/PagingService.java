package me.synology.freash97.paging.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.synology.freash97.paging.entity.PagingEntity;
import me.synology.freash97.paging.mapper.PagingRepository;
import org.springframework.stereotype.Service;

// 페이지 번호 개수
// 페이지 시작 번호
// 페이지 끝 번호
// 이전 버튼 활성화 여부
// 다음 버튼 활성화 여부


@Service
@RequiredArgsConstructor
public class PagingService {

// 한 페이지의 게시글 개수 : 10개
// 보여줄 페이지 번호 개수 : 5개 ( 1 ~ 5)

// 게시판 시작 번호
// 게시판 끝 번호

// 보여줄 페이지 번호

// 페이지 시작 번호
// 페이지 끝 번호

// 이전 버튼 활성화 여부
// 다음 버튼 활성화 여부

    private final PagingRepository pagingRepository;

    public PagingEntity getPageInfoByPageNo(int choicePageNo) {
        PagingEntity pageInfo = new PagingEntity();
        int startPageNo;
        int endPageNo;
        int totalNoticeCount = pagingRepository.getToTalCount();

        // 게시글 시작 ~ 끝 번호 체크
        int startNoticeNo = pageInfo.getNOTICE_SIZE() * choicePageNo + 1;
        int endNoticeNo = pageInfo.getNOTICE_SIZE() * (choicePageNo + 1);

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

        pageInfo.setStartNoticeNo(startNoticeNo);
        pageInfo.setEndNoticeNo(endNoticeNo);


        // 이전, 다음버튼 활성화 여부 세팅
        if (startPageNo > pageInfo.getPAGE_SIZE()) {
            pageInfo.setPrev(true);
        }

        if (totalNoticeCount > endPageNo * pageInfo.getNOTICE_SIZE()) {
            pageInfo.setNext(true);
        }

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

        return pageInfo;
    }

}

// 페이징 바이버튼 만들기
// 서비스 조회