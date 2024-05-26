// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.project;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suicrowdfundingexample.domain.*;
import org.test.suicrowdfundingexample.specialization.*;
import org.test.suicrowdfundingexample.domain.AbstractEvent;

public abstract class AbstractDonationEvent extends AbstractEvent implements DonationEvent.SqlDonationEvent {
    private DonationEventId donationEventId = new DonationEventId();

    public DonationEventId getDonationEventId() {
        return this.donationEventId;
    }

    public void setDonationEventId(DonationEventId eventId) {
        this.donationEventId = eventId;
    }
    
    public String getDonator() {
        return getDonationEventId().getDonator();
    }

    public void setDonator(String donator) {
        getDonationEventId().setDonator(donator);
    }

    private boolean eventReadOnly;

    public boolean getEventReadOnly() { return this.eventReadOnly; }

    public void setEventReadOnly(boolean readOnly) { this.eventReadOnly = readOnly; }

    private String createdBy;

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private Date createdAt;

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    private String commandId;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    protected AbstractDonationEvent() {
    }

    protected AbstractDonationEvent(DonationEventId eventId) {
        this.donationEventId = eventId;
    }


    public abstract String getEventType();


}

