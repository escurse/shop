package com.escass.shop.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(indexes = @Index(columnList = "title", name = "제목"))
public class ItemEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    // columnDefinition = 사용해서 컬럼타입 강제 지정 length = 사용해서 컬럼양 지정

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String username;
}