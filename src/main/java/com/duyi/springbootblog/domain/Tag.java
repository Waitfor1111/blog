package com.duyi.springbootblog.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {

    /** id */
    private Integer id;

    /** name */
    private String name;

    /** blogCount */
    private Integer blogCount;

    /** createTime */
    private Date createTime;



}
