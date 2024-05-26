// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.sui.contract.service;

import com.github.wubuku.sui.utils.SuiJsonRpcClient;
import org.test.suicrowdfundingexample.domain.*;
import org.test.suicrowdfundingexample.domain.project.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.math.*;

@Service
public class SuiProjectService {

    @Autowired
    private ProjectStateRepository projectStateRepository;

    private SuiProjectStateRetriever suiProjectStateRetriever;

    @Autowired
    public SuiProjectService(SuiJsonRpcClient suiJsonRpcClient) {
        this.suiProjectStateRetriever = new SuiProjectStateRetriever(suiJsonRpcClient,
                id -> {
                    ProjectState.MutableProjectState s = new AbstractProjectState.SimpleProjectState();
                    s.setId(id);
                    return s;
                },
                (projectState, donator) -> (DonationState.MutableDonationState)
                        ((EntityStateCollection.ModifiableEntityStateCollection<String, DonationState>) projectState.getDonations()).getOrAddDefault(donator)
        );
    }

    @Transactional
    public void updateProjectState(String objectId) {
        ProjectState projectState = suiProjectStateRetriever.retrieveProjectState(objectId);
        if (projectState == null) {
            return;
        }
        projectStateRepository.merge(projectState);
    }

}

