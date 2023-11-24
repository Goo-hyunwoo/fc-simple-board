package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;

    private String status;

    @OneToMany(
            mappedBy = "board"
    )
    @Builder.Default
    @Where(clause = "status = 'REGISTERED'")
    @OrderBy(clause = "id desc")
    private List<PostEntity> postList = List.of();

}
