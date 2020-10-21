package com.duyi.springbootblog.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {


    /** id */
    private Integer id;

    /** replyName */
    private String replyName;

    /** replyEmail */
    private String replyEmail;

    /** blogId */
    private Integer blogId;

    /** publishDate */
    private Date publishDate;

    /** bloggerState */
    private Integer bloggerState;

    /** replyState */
    private Integer replyState;

    /** replyContent */
    private String replyContent;

    /** parentId */
    private Integer parentId;

    /** createTime */
    private Date createTime;


}