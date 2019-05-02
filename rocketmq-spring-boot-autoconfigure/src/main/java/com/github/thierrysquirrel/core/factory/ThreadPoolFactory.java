package com.github.thierrysquirrel.core.factory;

import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ThreadPoolFactory <br/>
 * Description: <br/>
 * date: 2019/4/27 19:52<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
public class ThreadPoolFactory {
	public static ThreadPoolExecutor createConsumeThreadPoolExecutor(RocketProperties rocketProperties) {
		Integer threadNums = rocketProperties.getCreateConsumeThreadNums();

		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat("InitializeConsumerListener").build();

		return new ThreadPoolExecutor(threadNums,
				threadNums,
				0,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(1024),
				threadFactory,
				new ThreadPoolExecutor.AbortPolicy()
		);
	}

	public static ThreadPoolExecutor createProducerThreadPoolExecutor(RocketProperties rocketProperties) {

		Integer threadNums = rocketProperties.getCreateProducerThreadNums();

		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat("SendMessage").build();

		return new ThreadPoolExecutor(threadNums,
				threadNums,
				0,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(4096),
				threadFactory,
				new ThreadPoolExecutor.AbortPolicy()
		);
	}
}
