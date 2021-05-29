package my.hydra.practice.service;

import lombok.RequiredArgsConstructor;
import my.hydra.practice.domains.BoardDetail;
import my.hydra.practice.domains.Member;
import my.hydra.practice.models.ResponseCommon;
import my.hydra.practice.models.board.SelectBoardList;
import my.hydra.practice.models.board.SelectBoardNo;
import my.hydra.practice.models.request.RequestPostBoardDetail;
import my.hydra.practice.models.response.ResponseDeleteBoardDetail;
import my.hydra.practice.models.response.ResponseGetBoardDetail;
import my.hydra.practice.models.response.ResponseGetBoardDetailList;
import my.hydra.practice.models.response.ResponsePostBoardDetail;
import my.hydra.practice.repository.BoardCodeRepository;
import my.hydra.practice.repository.BoardDetailRepository;
import my.hydra.practice.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardDetailService {
    private final BoardDetailRepository boardDetailRepository;
    private final MemberRepository memberRepository;
    private final BoardCodeRepository boardCodeRepository;

    public ResponsePostBoardDetail save(RequestPostBoardDetail detail, User user, int boardNo) {
        Member registMember = memberRepository.findByMemberId(user.getUsername()).orElse(null);
        ResponseCommon responseCommon = new ResponseCommon();
        ResponsePostBoardDetail result = new ResponsePostBoardDetail();
        SelectBoardNo existBoard;
        if(registMember == null) {
            responseCommon.setCode("0001");
            responseCommon.setMessage("회원 정보가 존재하지 않습니다.");
            result.setResult(responseCommon);
            result.setBoardNo(0);

            return result;
        }
        // 유효한 게시판 번호인지 확인하는 로직도 필요함
        existBoard = boardCodeRepository.findBoardNoByBoardNo(boardNo);
        if(existBoard == null) {
            responseCommon.setCode("0002");
            responseCommon.setMessage("유효하지 않은 게시판입니다.");
            result.setResult(responseCommon);
            result.setBoardNo(0);

            return result;
        }

        if (detail.getTitle().trim().isEmpty() || detail.getTitle() == null) {
            responseCommon.setCode("0003");
            responseCommon.setMessage("제목을 입력하세요.");
            result.setResult(responseCommon);
            result.setBoardNo(0);

            return result;
        }
        if (detail.getContent().trim().isEmpty() || detail.getContent() == null) {
            responseCommon.setCode("0004");
            responseCommon.setMessage("내용을 입력하세요.");
            result.setResult(responseCommon);
            result.setBoardNo(0);

            return result;
        }

        BoardDetail boardDetail = new BoardDetail(boardNo, detail.getTitle(), detail.getContent());
        boardDetail.setMember(registMember);
        boardDetail.setCreateDate(LocalDateTime.now());
        boardDetail.setUpdateDate(LocalDateTime.now());

        boardDetail = boardDetailRepository.save(boardDetail);

        responseCommon.setCode("0000");
        responseCommon.setMessage("등록이 완료되었습니다.");
        result.setResult(responseCommon);
        result.setBoardNo(boardDetail.getBoardNo());

        return result;
    }
    public ResponseGetBoardDetailList getBoardList(int boardNo, int page, int pageSize) {
        SelectBoardNo existBoard;
        ResponseCommon responseCommon = new ResponseCommon();
        ResponseGetBoardDetailList result = new ResponseGetBoardDetailList();
        // 유효한 게시판 번호인지 확인하는 로직도 필요함
        existBoard = boardCodeRepository.findBoardNoByBoardNo(boardNo);
        if(existBoard == null) {
            responseCommon.setCode("0004");
            responseCommon.setMessage("유효하지 않은 게시판입니다.");
            result.setResult(responseCommon);
            result.setDataList(null);

            return result;
        }

        PageRequest pageRequest = PageRequest.of(page - 1,pageSize, Sort.by(Sort.Direction.DESC,"CreateDate"));

        Page<SelectBoardList> pageList = boardDetailRepository.getBoardDetailList(boardNo, pageRequest);

        responseCommon.setCode("0000");
        responseCommon.setMessage("조회에 성공하였습니다.");
        result.setResult(responseCommon);
        result.setDataList(pageList);

        return result;
    }
    public ResponseGetBoardDetail getBoardDetail(long no) {
        ResponseGetBoardDetail result = new ResponseGetBoardDetail();

        SelectBoardList detail;

        UserDetails userDetails = getUserDetails();


        // 조회수 증가
        boardDetailRepository.updateBoardViewCount(no);

        detail = boardDetailRepository.getBoardDetailByNo(no);

        if(detail != null) {
            result.setCode("0000");
            result.setMessage("조회에 성공하였습니다.");
            result.setDetail(detail);

            if(userDetails.getUsername().equals(detail.getMemberId())) {
                result.setRegistMember(true);
            }
            else {
                result.setRegistMember(false);
            }
        }
        else {
            result.setCode("0001");
            result.setMessage("존재하지 않는 게시글입니다.");
            result.setDetail(null);
        }
        return result;
    }
    public ResponseDeleteBoardDetail boardDelete(int boardNo, long no) {
        ResponseDeleteBoardDetail result = new ResponseDeleteBoardDetail();

        SelectBoardList detail = boardDetailRepository.getBoardDetailByNo(no);

        UserDetails userDetails = getUserDetails();

        // 글 작성자가 삭제하는 것인지 확인
        if(detail.getMemberId().equals(userDetails.getUsername())) {
            int resultCount = boardDetailRepository.deleteBoard(boardNo, no);
            result.setRowCount(resultCount);
        }
        else {
            result.setRowCount(0);
        }
        return result;
    }
    private UserDetails getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;
        return userDetails;
    }
}
