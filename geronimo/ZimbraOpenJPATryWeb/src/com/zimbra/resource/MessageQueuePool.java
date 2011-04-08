package com.zimbra.resource;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.zimbra.SMS.SMS;
import com.zimbra.rest.m.m;

public class MessageQueuePool {

    public static ConcurrentLinkedQueue<SMS> linkedqueue;

    public static void create() {
	linkedqueue = new ConcurrentLinkedQueue<SMS>();
    }

    public static ConcurrentLinkedQueue<SMS> getLinkedqueue() {
	return linkedqueue;
    }

    public MessageQueuePool() {

    }
}
