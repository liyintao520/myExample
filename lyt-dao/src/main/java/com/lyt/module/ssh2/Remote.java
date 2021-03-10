package com.lyt.module.ssh2;

import lombok.Data;

/**
 * @ClassName Remote
 * @Description TODO
 * @Author liyintao
 * @Date 2020/11/9 14:25
 */
@Data
public class Remote {

    private String user = "root";
    private String host = "127.0.0.1";
    private int port = 22;
    private String password = "";
    private String identity = "~/.ssh/id_rsa";
    private String passphrase = "";
}