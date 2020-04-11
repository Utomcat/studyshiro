package com.ranyk.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-04-10 20:36:18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User implements Serializable {
    private static final long serialVersionUID = 913324354688438009L;
    
    private Integer id;
    
    private String name;
    
    private String pwd;

    private String perms;

}