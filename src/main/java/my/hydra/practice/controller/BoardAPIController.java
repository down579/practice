package my.hydra.practice.controller;

import lombok.RequiredArgsConstructor;
import my.hydra.practice.models.request.RequestPostBoardDetail;
import my.hydra.practice.models.response.ResponsePostBoardDetail;
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
}
