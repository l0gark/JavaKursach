package com.loginov.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@ApiModel(description = "Ward object")
@Data
@NoArgsConstructor
@Entity
@Table(name = "ward")
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(notes = "Ward name", required = true)
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @ApiModelProperty(notes = "MaxCount of person", required = true)
    @NonNull
    @Column(name = "max_count", nullable = false)
    private Integer maxCount;
}
