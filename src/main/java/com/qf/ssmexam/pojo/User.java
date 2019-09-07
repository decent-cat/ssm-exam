package com.qf.ssmexam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {

    @Id
    private Integer id;
    private String name;
    private String password;
    private Character gender;
    private String email;
    private Date birthday;

    @Column(name="createTime")
    private Date createTime;
}
