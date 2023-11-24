package com.example.simpleboard.post.db;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private BoardEntity board;
    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime postedAt;

    @OneToMany(mappedBy = "post")
    @Builder.Default
    @Where(clause = "status = 'REGISTERED'")
    @OrderBy(clause = "id desc")
    private List<ReplyEntity> replyList = List.of();
}
