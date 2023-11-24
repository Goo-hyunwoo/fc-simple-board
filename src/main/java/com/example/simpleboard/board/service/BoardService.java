package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardConvertor boardConvertor;
    public BoardDto create(BoardRequest boardRequest){
         var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();
        var savedEntity =  boardRepository.save(entity);
        return boardConvertor.toDto(savedEntity);
    }

    public BoardDto view(Long id) {
        var entity = boardRepository.findById(id).get();
        return boardConvertor.toDto(entity);
    }
}
