// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.project;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suicrowdfundingexample.domain.*;
import org.test.suicrowdfundingexample.domain.AbstractCommand;

public abstract class AbstractDonationCommand extends AbstractCommand implements DonationCommand {

    private String donator;

    public String getDonator()
    {
        return this.donator;
    }

    public void setDonator(String donator)
    {
        this.donator = donator;
    }

    private String projectId;

    public String getProjectId()
    {
        return this.projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }


}

