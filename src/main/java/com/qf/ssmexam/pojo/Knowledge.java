package com.qf.ssmexam.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="knowledge")
public class Knowledge {

    @Id
    private Integer id;
    private String no;
    private String name;
    private SysUser sysUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="createtime")
    private Date createTime;
}
