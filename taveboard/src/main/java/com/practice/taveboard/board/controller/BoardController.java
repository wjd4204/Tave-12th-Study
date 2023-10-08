package com.practice.taveboard.board.controller;

import com.practice.taveboard.board.dto.BoardCreateRequestDto;
import com.practice.taveboard.board.dto.BoardListResponseDto;
import com.practice.taveboard.board.dto.BoardSimpleResponseDto;
import com.practice.taveboard.board.service.BoardService;
import com.practice.taveboard.common.format.success.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시글 생성", notes = "게시글을 생성합니다.")
    @PostMapping()
    public SuccessResponse createBoard(@ApiIgnore Principal principal, @RequestBody BoardCreateRequestDto dto) throws Exception {
        String username = "박정곤";

        boardService.createBoard(username, dto);

        return SuccessResponse.ok();
    }

    @ApiOperation(value = "게시글 조회", notes = "게시글을 상세히 조회합니다.")
    @GetMapping()
    public SuccessResponse<BoardListResponseDto> getBoards(@ApiIgnore Principal principal,
                                                           @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String username = "박정곤";
        return new SuccessResponse(boardService.getBoards(username, page));
    }

    @ApiOperation(value = "게시글 상세 조회",
    notes = "조회 된 게시글 리스트 중 하나를 클릭하여 상세 조회 합니다.")
    @GetMapping("/{boardId}")
    public SuccessResponse<BoardSimpleResponseDto> getDetailBoards(@ApiIgnore Principal principal,
                                                                   @PathVariable(value = "boardId", required = true) int boardId){
        String username = "박정곤";

        return new SuccessResponse(boardService.getDetailBoards(username, boardId));
    }
}
