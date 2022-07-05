package com.processingtime.scraper.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "RESULT")

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String processingTime;

    @Column
    private String lastUpdated;
}

