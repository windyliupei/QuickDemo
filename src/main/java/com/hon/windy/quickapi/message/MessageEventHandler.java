package com.hon.windy.quickapi.message;


import com.lmax.disruptor.WorkHandler;
import com.windowsazure.messaging.Notification;
import com.windowsazure.messaging.NotificationHub;
import com.windowsazure.messaging.NotificationOutcome;

public class MessageEventHandler implements WorkHandler<MessageEvent> {

    private static String azureNotificationName = "WindyBaidu";
    private static String azureNotificationConnStr
            = "Endpoint=sb://baidunotification-ns.servicebus.chinacloudapi.cn/;SharedAccessKeyName=DefaultFullSharedAccessSignature;SharedAccessKey=+Z3RfHztOd5BRNrVTMw4saduVck+N1LyVZAkh7uTwo8=";

    private String name;

    public MessageEventHandler(String handlerName) {
        this.name = handlerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void onEvent(MessageEvent messageEvent) throws Exception {
        System.out.println(name+"|Event Handler process: " + messageEvent.getValue());
        String value = messageEvent.getValue();

        NotificationHub hub = new NotificationHub(azureNotificationConnStr,azureNotificationName);
        Notification notification = Notification.createBaiduNotification("{\"title\":\"(Notification title)\",\"description\":\"Notification Hub test notification\"}");
        NotificationOutcome notificationOutcome = hub.sendNotification(notification);




    }
}
