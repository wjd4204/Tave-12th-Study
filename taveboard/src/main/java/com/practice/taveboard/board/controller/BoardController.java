package com.practice.taveboard.board.controller;

import com.practice.taveboard.board.dto.BoardCreateRequestDto;
import com.practice.taveboard.board.dto.BoardListResponseDto;
import com.practice.taveboard.board.dto.BoardSimpleResponseDto;
import com.practice.taveboard.board.dto.BoardUpdateRequestDto;
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
                                                                   @PathVariable(value = "boardId", required = true) Long boardId){
        String username = "박정곤";

        return new SuccessResponse(boardService.getDetailBoards(username, boardId));
    }

    @ApiOperation(value = "게시글 수정",
            notes = "지정된 게시글의 제목 내용을 수정합니다.")
    @PatchMapping("/{boardId}")
    public SuccessResponse updateBoards(@ApiIgnore Principal principal,
                                        @PathVariable(value = "boardId", required = true) Long boardId,
                                        @RequestBody BoardUpdateRequestDto boardUpdateRequestDto){
        String username = "박정곤";
        boardService.updateBoards(username, boardId, boardUpdateRequestDto);
        return SuccessResponse.ok("update Success");
    }

    @ApiOperation(value = "게시글 삭제",
    notes = "게시글을 삭제합니다.")
    @DeleteMapping("/{boardId}")
    public SuccessResponse deleteBoards(@ApiIgnore Principal principal,
                                        @PathVariable(value = "boardId", required = true) Long boardId){
        String username = "박정곤";
        boardService.deleteBoards(username, boardId);
        return SuccessResponse.ok("delete Success");
    }
}
