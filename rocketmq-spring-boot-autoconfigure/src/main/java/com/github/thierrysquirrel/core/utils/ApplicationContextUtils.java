/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.thierrysquirrel.core.utils;

import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionExecuter;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;
import com.github.thierrysquirrel.core.producer.DefaultLocalTransactionChecker;
import com.github.thierrysquirrel.core.producer.DefaultLocalTransactionExecuter;
import com.github.thierrysquirrel.core.producer.DefaultSendCallback;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

/**
 * ClassName: ApplicationContextUtils
 * Description:
 * date: 2019/7/11 18:47
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ApplicationContextUtils {
	private ApplicationContextUtils() {
	}

	public static SendCallback getSendCallback(ApplicationContext applicationContext, String callbackBeanName) {
		SendCallback sendCallback;
		try {
			if (StringUtils.isEmpty(callbackBeanName)) {
				sendCallback = applicationContext.getBean(SendCallback.class);
			} else {
				sendCallback = applicationContext.getBean(callbackBeanName, SendCallback.class);
			}
		} catch (BeansException e) {
			sendCallback = new DefaultSendCallback();
		}
		return sendCallback;
	}

	public static LocalTransactionChecker getLocalTransactionChecker(ApplicationContext applicationContext, TransactionStatus transactionStatus, String checkerBeanName) {
		LocalTransactionChecker localTransactionChecker;
		try {
			if (StringUtils.isEmpty(checkerBeanName)) {
				localTransactionChecker = applicationContext.getBean(LocalTransactionChecker.class);
			} else {
				localTransactionChecker = applicationContext.getBean(checkerBeanName, LocalTransactionChecker.class);
			}
		} catch (BeansException e) {
			localTransactionChecker = new DefaultLocalTransactionChecker(transactionStatus);
		}
		return localTransactionChecker;
	}

	public static LocalTransactionExecuter getLocalTransactionExecuter(ApplicationContext applicationContext, String executerBeanName) {
		LocalTransactionExecuter localTransactionExecuter;
		try {
			if (StringUtils.isEmpty(executerBeanName)) {
				localTransactionExecuter = applicationContext.getBean(LocalTransactionExecuter.class);
			} else {
				localTransactionExecuter = applicationContext.getBean(executerBeanName, LocalTransactionExecuter.class);
			}
		} catch (BeansException e) {
			localTransactionExecuter = new DefaultLocalTransactionExecuter();
		}
		return localTransactionExecuter;
	}
}
