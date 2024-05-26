// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.sui.contract.taskservice;

import org.test.suicrowdfundingexample.sui.contract.service.ProjectEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PullProjectEventsTaskService {

    @Autowired
    private ProjectEventService projectEventService;

    @Scheduled(fixedDelayString = "${sui.contract.pull-project-events.project-created.fixed-delay:5000}")
    public void pullProjectCreatedEvents() {
        projectEventService.pullProjectCreatedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-project-events.project-updated.fixed-delay:5000}")
    public void pullProjectUpdatedEvents() {
        projectEventService.pullProjectUpdatedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-project-events.project-started.fixed-delay:5000}")
    public void pullProjectStartedEvents() {
        projectEventService.pullProjectStartedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-project-events.donation-received.fixed-delay:5000}")
    public void pullDonationReceivedEvents() {
        projectEventService.pullDonationReceivedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-project-events.vault-withdrawn.fixed-delay:5000}")
    public void pullVaultWithdrawnEvents() {
        projectEventService.pullVaultWithdrawnEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-project-events.donation-refunded.fixed-delay:5000}")
    public void pullDonationRefundedEvents() {
        projectEventService.pullDonationRefundedEvents();
    }

}
