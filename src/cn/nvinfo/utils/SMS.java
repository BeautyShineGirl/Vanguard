package cn.nvinfo.utils;

import org.apache.log4j.Logger;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
/**
 * 发送验证码	
 * @author 杨立	2017-11-07
 *
 */
public class SMS {
	/*public static void main(String[] args) {
		sendSms("杨立 ","2017-11-07","lol世界总决赛门票","1","1245678963","123456523"," 北京","18789455937");
	}*/
	private static Logger log=Logger.getLogger(SMS.class);
	public static void sendSms(String name,String date,String ticketname,String quantity,
			String orderid,String ticketcode,String scenic,String telephone) {
		/**
		 * Step 1. 获取主题引用
		 */
		CloudAccount account = new CloudAccount("LTAIXQzwKxw2xRnB", "kverHP6HY1V3gKEEQECwydFgynXNOT",
				"http://1086158643880738.mns.cn-hangzhou.aliyuncs.com/");
		MNSClient client = account.getMNSClient();
		CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");
		/**
		 * Step 2. 设置SMS消息体（必须）
		 *
		 * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
		 */
		RawTopicMessage msg = new RawTopicMessage();
		msg.setMessageBody("sms-message");
		/**
		 * Step 3. 生成SMS消息属性
		 */
		MessageAttributes messageAttributes = new MessageAttributes();
		BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
		// 3.1 设置发送短信的签名（SMSSignName）
		batchSmsAttributes.setFreeSignName("E旅通");
		// 3.2 设置发送短信使用的模板（SMSTempateCode）
		batchSmsAttributes.setTemplateCode("SMS_85790043");
		// 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
		BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
		smsReceiverParams.setParam("name", name);//用户姓名
		smsReceiverParams.setParam("date", date);//出发日期
		smsReceiverParams.setParam("ticketname", ticketname);//产品名称 如冰雪大世界成人票
		smsReceiverParams.setParam("quantity", quantity);//票数
		smsReceiverParams.setParam("orderid", orderid);//订单号
		smsReceiverParams.setParam("ticketcode", ticketcode);//入园凭证码
		smsReceiverParams.setParam("scenic", scenic);//景区名称

		// 3.4 增加接收短信的号码
		batchSmsAttributes.addSmsReceiver(telephone, smsReceiverParams);
		messageAttributes.setBatchSmsAttributes(batchSmsAttributes);

		/**
		 * Step 4. 发布SMS消息
		 * 
		 */
		try {
			TopicMessage ret = topic.publishMessage(msg, messageAttributes);
			//System.out.println("MessageId: " + ret.getMessageId());
			//System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
			log.info("MessageId: " + ret.getMessageId());
			log.info("MessageMD5: " + ret.getMessageBodyMD5());
		} catch (ServiceException se) {
			log.info(se.getErrorCode() + se.getRequestId());	
			log.info(se.getMessage());
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.close();

	}

	public static void toYunYing(String name,String date,String orderid,String message,String telephone) {
		/**
		 * Step 1. 获取主题引用
		 */
		CloudAccount account = new CloudAccount("LTAIXQzwKxw2xRnB", "kverHP6HY1V3gKEEQECwydFgynXNOT",
				"http://1086158643880738.mns.cn-hangzhou.aliyuncs.com/");
		MNSClient client = account.getMNSClient();
		CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");
		/**
		 * Step 2. 设置SMS消息体（必须）
		 *
		 * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
		 */
		RawTopicMessage msg = new RawTopicMessage();
		msg.setMessageBody("sms-message");
		/**
		 * Step 3. 生成SMS消息属性
		 */
		MessageAttributes messageAttributes = new MessageAttributes();
		BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
		// 3.1 设置发送短信的签名（SMSSignName）
		batchSmsAttributes.setFreeSignName("E旅通");
		// 3.2 设置发送短信使用的模板（SMSTempateCode）
		batchSmsAttributes.setTemplateCode("SMS_85790043");
		// 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
		BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
		smsReceiverParams.setParam("name", name);//供应商名字
		smsReceiverParams.setParam("date", date);//通知日期
		smsReceiverParams.setParam("orderid", orderid);//订单号
		smsReceiverParams.setParam("msg", message);//订单支付失败的原因
		// 3.4 增加接收短信的号码
		batchSmsAttributes.addSmsReceiver(telephone, smsReceiverParams);
		messageAttributes.setBatchSmsAttributes(batchSmsAttributes);

		/**
		 * Step 4. 发布SMS消息
		 * 
		 */
		try {
			TopicMessage ret = topic.publishMessage(msg, messageAttributes);
			//System.out.println("MessageId: " + ret.getMessageId());
			//System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
			log.info("MessageId: " + ret.getMessageId());
			log.info("MessageMD5: " + ret.getMessageBodyMD5());
		} catch (ServiceException se) {
			log.info(se.getErrorCode() + se.getRequestId());	
			log.info(se.getMessage());
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.close();

	}
	
	public static void toYunYingRefund(String name,String date,String orderid,String message,String telephone) {
		/**
		 * Step 1. 获取主题引用
		 */
		CloudAccount account = new CloudAccount("LTAIXQzwKxw2xRnB", "kverHP6HY1V3gKEEQECwydFgynXNOT",
				"http://1086158643880738.mns.cn-hangzhou.aliyuncs.com/");
		MNSClient client = account.getMNSClient();
		CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");
		/**
		 * Step 2. 设置SMS消息体（必须）
		 *
		 * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
		 */
		RawTopicMessage msg = new RawTopicMessage();
		msg.setMessageBody("sms-message");
		/**
		 * Step 3. 生成SMS消息属性
		 */
		MessageAttributes messageAttributes = new MessageAttributes();
		BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
		// 3.1 设置发送短信的签名（SMSSignName）
		batchSmsAttributes.setFreeSignName("E旅通");
		// 3.2 设置发送短信使用的模板（SMSTempateCode）
		batchSmsAttributes.setTemplateCode("SMS_85790043");
		// 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
		BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
		smsReceiverParams.setParam("name", name);//供应商名字
		smsReceiverParams.setParam("date", date);//通知日期
		smsReceiverParams.setParam("orderid", orderid);//订单号
		smsReceiverParams.setParam("msg", message);//退款失败的原因
		// 3.4 增加接收短信的号码
		batchSmsAttributes.addSmsReceiver(telephone, smsReceiverParams);
		messageAttributes.setBatchSmsAttributes(batchSmsAttributes);

		/**
		 * Step 4. 发布SMS消息
		 * 
		 */
		try {
			TopicMessage ret = topic.publishMessage(msg, messageAttributes);
			//System.out.println("MessageId: " + ret.getMessageId());
			//System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
			log.info("MessageId: " + ret.getMessageId());
			log.info("MessageMD5: " + ret.getMessageBodyMD5());
		} catch (ServiceException se) {
			log.info(se.getErrorCode() + se.getRequestId());	
			log.info(se.getMessage());
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.close();

	}
}
