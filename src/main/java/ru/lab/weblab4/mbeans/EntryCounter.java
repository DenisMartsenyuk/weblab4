package ru.lab.weblab4.mbeans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

@Component
@ManagedResource
@Slf4j
public class EntryCounter extends NotificationBroadcasterSupport implements EntryCounterMBean {

    private int summary = 0;
    private int missed = 0;
    private boolean previousEntry = true;

    public void apply(boolean isInArea) {
        summary++;
        if (!isInArea) {
            missed++;
            if (!previousEntry) {
                Notification notif = new Notification(
                        "ru.lab.weblab4.mbeans.secondMissInARow",
                        this,
                        0,
                        System.currentTimeMillis(),
                        "2 misses in a row! LOH!!!!!!!!"
                );
                this.sendNotification(notif);
            }
        }
        previousEntry = isInArea;
    }

    @ManagedAttribute
    @Override
    public int getSummaryCountOfEntries() {
        return summary;
    }

    @ManagedAttribute
    @Override
    public int getCountOfMissedEntries() {
        return missed;
    }

}
