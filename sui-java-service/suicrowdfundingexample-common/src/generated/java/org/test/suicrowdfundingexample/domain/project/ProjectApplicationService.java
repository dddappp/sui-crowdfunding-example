// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.project;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.suicrowdfundingexample.domain.*;
import org.test.suicrowdfundingexample.specialization.Event;
import org.test.suicrowdfundingexample.domain.Command;

public interface ProjectApplicationService {
    void when(ProjectCommands.Create c);

    void when(ProjectCommands.Update c);

    void when(ProjectCommands.Start c);

    ProjectState get(String id);

    Iterable<ProjectState> getAll(Integer firstResult, Integer maxResults);

    Iterable<ProjectState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<ProjectState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<ProjectState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

    ProjectEvent getEvent(String id, long version);

    ProjectState getHistoryState(String id, long version);

    DonationState getDonation(String projectId, String donator);

    Iterable<DonationState> getDonations(String projectId, Criterion filter, List<String> orders);

}

