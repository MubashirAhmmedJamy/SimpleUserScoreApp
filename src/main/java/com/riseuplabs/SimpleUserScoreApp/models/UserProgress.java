package com.riseuplabs.SimpleUserScoreApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "user_progress", schema = "score", catalog = "score")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level", columnDefinition = "integer default 0")
    private int level;

    @Column(name = "score", columnDefinition = "float default 0")
    private float score;

    @OneToOne(optional = true)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonBackReference
    private User user;
}
