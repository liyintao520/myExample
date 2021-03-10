package com.lyt.jschTest;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.lyt.module.ssh2.Remote;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SFTPUtils
 * @Description TODO
 * @Author liyintao
 * @Date 2020/11/9 14:27
 */
@Slf4j
public class SFTPUtils {

    private static final int CONNECT_TIMEOUT = 12000; // 单位 毫秒。     设置五分钟

    private static final String FTP_PATH = "/usr/local/datax/datax/job/test"; // 单位 毫秒。

    public static void main(String[] args) throws Exception {
        Remote remote = new Remote();
        remote.setHost("27.221.53.14");
        remote.setPort(46025);
        remote.setPassword("ad4er2f22");
        Session session = getSession(remote);
        session.connect(CONNECT_TIMEOUT);
        if (session.isConnected()) {
            log.info("Host({}) connected--> ", remote.getHost());
        }
        // 执行命令开始
//        remoteExecute(session, "pwd");
//        remoteExecute(session, "mkdir /usr/local/datax/datax/job/test");
//        remoteExecute(session, "ls /usr/local/datax/datax/job/test");
//        remoteExecute(session, "touch /usr/local/datax/datax/job/test/test1; touch /usr/local/datax/datax/job/test/test2");
//        remoteExecute(session, "echo 'It a test file.' > /usr/local/datax/datax/job/test/test-file");
//        remoteExecute(session, "ls -all /usr/local/datax/datax/job/test");
//        remoteExecute(session, "ls -all /usr/local/datax/datax/job/test | grep test");
//        remoteExecute(session, "cat /usr/local/datax/datax/job/test/test-file");
//        // 执行命令结束
//        log.info("*****************************************************************************************************************");
//        remoteExecute(session, "cd /usr/local/datax/datax/bin; pwd; python datax.py -r mysqlreader -w mysqlwriter;"); //  这个好使
        remoteExecute(session, "python /usr/local/datax/datax/bin/datax.py -r mysqlreader -w mysqlwriter;"); //  这个好使
//        remoteExecute(session, "cd /usr/local/datax/datax; pwd; bin/datax.py job/3.json;");
//        remoteExecute(session, "cd /usr/local/datax/datax && pwd && bin/datax.py job/3.json;");
//        remoteExecute(session, "python --help");
//        remoteExecute(session, "cd /usr/local/datax/datax/bin; pwd;  python /usr/local/datax/datax/bin/datax.py '/usr/local/datax/datax/job/3.json'");
//        remoteExecute(session, "python /usr/local/datax/datax/bin/datax.py /usr/local/datax/datax/job/stream2stream.json;");

//        log.info("*****************************************************************************************************************");

//        remoteExecute(session, "python --version");
//        remoteExecute(session, "chmod  777 /usr/local/datax/datax");
//        remoteExecute(session, "chmod 777 /usr/local/datax/datax; chmod  777 /usr/bin/python2.7; chmod  777 /usr/bin/python; chmod  777 /etc/python; cd /usr/local/datax/datax/bin; pwd; python datax.py ../job/liyintaoceshi.json; pwd;");

        session.disconnect();
    }

    /**
     * JSch使用Session来定义一个远程节点
     * @param remote
     * @return
     * @throws JSchException
     */
    public static Session getSession(Remote remote) throws JSchException {
        JSch jSch = new JSch();
        if (Files.exists(Paths.get(remote.getIdentity()))) {
            jSch.addIdentity(remote.getIdentity(), remote.getPassphrase());
        }
        Session session = jSch.getSession(remote.getUser(), remote.getHost(),remote.getPort());
        session.setPassword(remote.getPassword());
        session.setConfig("StrictHostKeyChecking", "no");
        return session;
    }

    /**
     * 远程指令 Session上执行命令
     * @param session
     * @param command
     * @return
     * @throws JSchException
     */
    public static List<String> remoteExecute(Session session, String command) throws JSchException {
        log.debug(">> {}", command);
        int returnCode  = -1;
        List<String> resultLines = new ArrayList<>();
        ChannelExec channel = null;
        try{
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            InputStream input = channel.getInputStream();
            channel.connect(CONNECT_TIMEOUT);
            try {
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
                String inputLine = null;
                while((inputLine = inputReader.readLine()) != null) {
                    log.debug("   {}", inputLine);
                    resultLines.add(inputLine);
                }
                if (channel.isClosed()) {
                    returnCode = channel.getExitStatus();
                }
                log.info("运行状态：{}", returnCode);
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (Exception e) {
                        log.error("JSch inputStream close error:", e);
                    }
                }
            }
        } catch (IOException e) {
            log.error("IOcxecption:", e);
        } finally {
            if (channel != null) {
                try {
                    channel.disconnect();
                } catch (Exception e) {
                    log.error("JSch channel disconnect error:", e);
                }
            }
        }
        return resultLines;
    }

}
