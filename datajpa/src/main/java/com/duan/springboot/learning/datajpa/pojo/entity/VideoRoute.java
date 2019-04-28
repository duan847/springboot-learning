package com.duan.springboot.learning.datajpa.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VideoRoute {
    @Id
    @GeneratedValue
    private Long id;
    private Long videoId;
    private String name;
    private String line;
    private String url;
}
