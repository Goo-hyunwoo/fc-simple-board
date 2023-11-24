package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.post.service.PostConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardConvertor {
    private final PostConvertor postConvertor;

    public BoardDto toDto(BoardEntity boardEntity) {
        var postList = boardEntity.getPostList()
                .stream()
                .map(postConvertor::toDto)
                .collect(Collectors.toList());

        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postList(postList)
                .build();
    }
}
