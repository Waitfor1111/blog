package com.duyi.springbootblog.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {


    /** id */
    private Integer id;

    /** name */
    private String name;

    /** password */
    private String password;

    /** icon */
    private String icon;

    /** admin */
    private Integer admin;

    /** createTime */
    private Date createTime;


}