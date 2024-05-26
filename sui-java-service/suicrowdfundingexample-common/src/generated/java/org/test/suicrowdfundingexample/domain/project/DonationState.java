// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.project;

import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suicrowdfundingexample.domain.*;
import org.test.suicrowdfundingexample.specialization.Event;

public interface DonationState
{
    Long VERSION_ZERO = 0L;

    Long VERSION_NULL = VERSION_ZERO - 1;

    String getDonator();

    BigInteger getAmount();

    Long getOffChainVersion();

    String getCreatedBy();

    Date getCreatedAt();

    String getUpdatedBy();

    Date getUpdatedAt();

    Boolean getActive();

    Boolean getDeleted();

    String getProjectId();

    interface MutableDonationState extends DonationState {
        void setDonator(String donator);

        void setAmount(BigInteger amount);

        void setOffChainVersion(Long offChainVersion);

        void setCreatedBy(String createdBy);

        void setCreatedAt(Date createdAt);

        void setUpdatedBy(String updatedBy);

        void setUpdatedAt(Date updatedAt);

        void setActive(Boolean active);

        void setDeleted(Boolean deleted);

        void setProjectId(String projectId);


        void mutate(Event e);

        //void when(DonationEvent.DonationStateCreated e);

        //void when(DonationEvent.DonationStateMergePatched e);

        //void when(DonationEvent.DonationStateRemoved e);
    }

    interface SqlDonationState extends MutableDonationState {
        ProjectDonationId getProjectDonationId();

        void setProjectDonationId(ProjectDonationId projectDonationId);


        boolean isStateUnsaved();

        boolean getForReapplying();
    }
}

