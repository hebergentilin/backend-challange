package com.invillia.acme.log;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Class
 *
 * @author Paulo
 * @since 26/11/2018
 */
@Slf4j
@Data
public class LogUtils {
    private LogUtils(){

    }

    public static void printExceptionStack(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
         log.trace(sw.toString());
    }

    public static void printExceptionStack(Throwable e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        log.trace(sw.toString());
    }
}
