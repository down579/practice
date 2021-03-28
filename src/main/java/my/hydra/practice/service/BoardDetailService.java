package my.hydra.practice.service;

import lombok.RequiredArgsConstructor;
import my.hydra.practice.domains.BoardDetail;
import my.hydra.practice.domains.Member;
import my.hydra.practice.models.ResponseCommon;
import my.hydra.practice.models.request.RequestPostBoardDetail;
import my.hydra.practice.models.response.ResponsePostBoardDetail;
import my.hydra.practice.repository.BoardCodeRepository;
import my.hydra.practice.repository.BoardDetailRepository;
import my.hydra.practice.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
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
        int existBoard = 0;
        if(registMember == null) {
            responseCommon.setCode("0001");
            responseCommon.setMessage("회원 정보가 존재하지 않습니다.");
            result.setResult(responseCommon);
            result.setBoardNo(0);

            return result;
        }
        // 유효한 게시판 번호인지 확인하는 로직도 필요함
        existBoard = boardCodeRepository.findBoardCodeByBoardNo(detail.getBoardNo());
        if(existBoard == 0) {
            responseCommon.setCode("0004");
            responseCommon.setMessage("유효하지 않은 게시판입니다.");
            result.setResult(responseCommon);
            result.setBoardNo(0);

            return result;
        }

        if (detail.getTitle().trim().isEmpty() || detail.getTitle() == null) {
            responseCommon.setCode("0002");
            responseCommon.setMessage("제목을 입력하세요.");
            result.setResult(responseCommon);
            result.setBoardNo(0);

            return result;
        }
        if (detail.getContent().trim().isEmpty() || detail.getContent() == null) {
            responseCommon.setCode("0003");
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

        responseCommon.setCode("0003");
        responseCommon.setMessage("등록이 완료되었습니다.");
        result.setResult(responseCommon);
        result.setBoardNo(boardDetail.getBoardNo());

        return result;
    }
}
