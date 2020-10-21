package com.duyi.springbootblog.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Columnist implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * intro
     */
    private String intro;

    /**
     * blogCount
     */
    private Integer blogCount;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

    /**
     * columnistState
     */
    private Integer columnistState;


}