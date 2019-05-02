package com.github.thierrysquirrel.core.factory.execution;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: ThreadPoolExecutorExecution  
 * Description:  
 * date: 2019/4/27 19:55 
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ThreadPoolExecutorExecution {
	public static void statsThread(ThreadPoolExecutor threadPoolExecutor, Runnable runnable) {
		threadPoolExecutor.execute(runnable);
	}
}
