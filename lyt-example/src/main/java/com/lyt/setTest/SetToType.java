package com.lyt.setTest;

import com.lyt.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @ClassName SetToType
 * @Description Set<T> 集合转换成其他类型 </>
 * @Author liyintao
 * @Date 2020/5/11 13:37
 */
@Slf4j
public class SetToType {

    public static void main(String[] args) {
        log.info(JsonUtil.toJson(setToArray()));

    }

    /**
     * Set<String> 转换 String[] 数组</>
     * @return
     */
    public static String[] setToArray() {
        Set<String> setKeys = InitSetDb.getSet();
        String[] keysStr = setKeys.toArray(new String[setKeys.size()]);
        return keysStr;
    }
}
