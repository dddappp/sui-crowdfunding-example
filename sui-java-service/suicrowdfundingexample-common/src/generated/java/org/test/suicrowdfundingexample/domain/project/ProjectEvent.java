// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.project;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suicrowdfundingexample.domain.*;
import org.test.suicrowdfundingexample.specialization.Event;

public interface ProjectEvent extends Event, SuiEventEnvelope, SuiMoveEvent, HasStatus {

    interface SqlProjectEvent extends ProjectEvent {
        ProjectEventId getProjectEventId();

        boolean getEventReadOnly();

        void setEventReadOnly(boolean readOnly);
    }

    interface ProjectCreated extends ProjectEvent {
        String getPlatformId();

        void setPlatformId(String value);

        String getOwner();

        void setOwner(String value);

        String getTitle();

        void setTitle(String value);

        String getDescription();

        void setDescription(String value);

        BigInteger getTarget();

        void setTarget(BigInteger value);

        String getImage();

        void setImage(String value);

        String getTokenType();

        void setTokenType(String value);

    }

    interface ProjectUpdated extends ProjectEvent {
        String getTitle();

        void setTitle(String value);

        String getDescription();

        void setDescription(String value);

        BigInteger getTarget();

        void setTarget(BigInteger value);

        String getImage();

        void setImage(String value);

    }

    interface ProjectStarted extends ProjectEvent {
        BigInteger getDeadline();

        void setDeadline(BigInteger value);

    }

    interface DonationReceived extends ProjectEvent {
        String getDonator();

        void setDonator(String value);

        BigInteger getAmount();

        void setAmount(BigInteger value);

    }

    interface VaultWithdrawn extends ProjectEvent {
        BigInteger getAmount();

        void setAmount(BigInteger value);

    }

    interface DonationRefunded extends ProjectEvent {
        String getDonator();

        void setDonator(String value);

        BigInteger getAmount();

        void setAmount(BigInteger value);

    }

    String getId();

    //void setId(String id);

    BigInteger getVersion();
    
    //void setVersion(BigInteger version);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    String getCommandId();

    void setCommandId(String commandId);


}

