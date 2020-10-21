package com.duyi.springbootblog.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * title
     */
    private String title;

    /**
     * summary
     */
    private String summary;

    /**
     * content
     */
    private String content;

    /**
     * publishDate
     */
    private Date publishDate;

    /**
     * columnId
     */
    private Integer columnId;

    /**
     * views
     */
    private Integer views;

    /**
     * tags
     */
    private String tags;

    /**
     * comments
     */
    private String comments;

    /**
     * blogImg
     */
    private String blogImg;

    /**
     * blogState
     */
    private Integer blogState;

    /**
     * admireState
     */
    private Integer admireState;

    /**
     * commentState
     */
    private Integer commentState;

    /**
     * recommendState
     */
    private Integer recommendState;

    /**
     * reprintState
     */
    private Integer reprintState;

    /**
     * createTime
     */
    private Date createTime;


    private Date updateTime;


    private Columnist columnist;


    private Integer property;

}
