package my.hydra.practice.controller;

import lombok.RequiredArgsConstructor;
import my.hydra.practice.models.request.RequestPostBoardDetail;
import my.hydra.practice.models.request.RequestPutBoardDetail;
import my.hydra.practice.models.response.*;
import my.hydra.practice.service.BoardDetailService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardAPIController {
    private final BoardDetailService service;

    @PostMapping("/{boardNo}")
    public ResponsePostBoardDetail registBoard(@AuthenticationPrincipal User user,
                                               @RequestBody RequestPostBoardDetail detail,
                                               @PathVariable int boardNo)
    {
        return service.save(detail, user, boardNo);
    }

    @GetMapping("/{boardNo}")
    public ResponseGetBoardDetailList getBoardList(@PathVariable int boardNo, int page, int pageSize)
    {
        return service.getBoardList(boardNo, page, pageSize);
    }

    @GetMapping("/{boardNo}/{no}")
    public ResponseGetBoardDetail getBoardDetail(@PathVariable int boardNo, @PathVariable long no) {
        return service.getBoardDetail(no);
    }

    @DeleteMapping("/{boardNo}/{no}")
    public ResponseDeleteBoardDetail deleteBoardDetail(
                                                       @PathVariable int boardNo,
                                                       @PathVariable long no)
    {
        return service.boardDelete(boardNo, no);
    }
    @PutMapping("/{boardNo}/{no}")
    public ResponsePutBoardDetail updateBoardDetail(@PathVariable int boardNo, @PathVariable long no,
                                  @RequestBody RequestPutBoardDetail detail) {
        return service.boardUpdate(boardNo, no, detail.getTitle(), detail.getContent());
    }
}
