package com.practice.taveboard.board.service;

import com.practice.taveboard.board.dto.BoardCreateRequestDto;
import com.practice.taveboard.board.dto.BoardListResponseDto;
import com.practice.taveboard.board.dto.BoardSimpleResponseDto;
import com.practice.taveboard.board.dto.BoardUpdateRequestDto;
import com.practice.taveboard.board.entity.Board;
import com.practice.taveboard.board.repository.BoardRepository;
import com.practice.taveboard.common.format.exception.board.NotFoundBoardException;
import com.practice.taveboard.common.format.exception.user.NotFoundUserException;
import com.practice.taveboard.user.entity.User;
import com.practice.taveboard.user.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final int TOTAL_ITEMS_PER_PAGE = 20;


    // 게시글 생성
    public void createBoard(String username, BoardCreateRequestDto dto) throws Exception{

        User user = userRepository.findByUsername(username).orElseThrow(NotFoundUserException::new);
        Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .build();
        boardRepository.save(board);
    }


    // 게시글 리스트 조회
    public BoardListResponseDto getBoards(String username, int page){
        Page<Board> boardPage = boardRepository.findAllByCreatedTimeDesc(PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));

        return BoardListResponseDto.builder()
                .totalPage(boardPage.getTotalPages())
                .currentPage(boardPage.getNumber())
                .list(boardPage.getContent().stream().map((board) -> BoardSimpleResponseDto.builder()
                        .title(board.getTitle())
                        .content(board.getContent())
                        .nickname(board.getUser().getNickname())
                        .createdTime(board.getCreatedTime())
                        .build())
                        .collect(Collectors.toList()))
                .build();
    }

    // 게시글 상세 조회
    public BoardSimpleResponseDto getDetailBoards(String username, Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);

        return BoardSimpleResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .nickname(board.getUser().getNickname())
                .createdTime(board.getCreatedTime())
                .build();

    }

    // 게시글 수정
    public void updateBoards(String username, Long boardId, BoardUpdateRequestDto dto){
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        User user = userRepository.findByUsername(username).orElseThrow(NotFoundUserException::new);

        if((board.getUser().getNickname()).equals(user.getNickname())) {
            board.update(dto.getTitle(), dto.getContent());
        }
    }

    public void deleteBoards(String username, Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        User user = userRepository.findByUsername(username).orElseThrow(NotFoundUserException::new);

        if((board.getUser().getNickname()).equals(user.getNickname())){
            boardRepository.deleteById(boardId);
        }
    }


}
