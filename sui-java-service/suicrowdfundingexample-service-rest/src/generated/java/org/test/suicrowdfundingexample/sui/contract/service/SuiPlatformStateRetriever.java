// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.sui.contract.service;

import com.github.wubuku.sui.bean.*;
import com.github.wubuku.sui.utils.*;
import org.test.suicrowdfundingexample.domain.platform.*;
import org.test.suicrowdfundingexample.domain.*;
import org.test.suicrowdfundingexample.sui.contract.DomainBeanUtils;
import org.test.suicrowdfundingexample.sui.contract.Platform;

import java.util.*;
import java.math.*;
import java.util.function.*;

public class SuiPlatformStateRetriever {

    private SuiJsonRpcClient suiJsonRpcClient;

    private Function<String, PlatformState.MutablePlatformState> platformStateFactory;

    public SuiPlatformStateRetriever(SuiJsonRpcClient suiJsonRpcClient,
                                  Function<String, PlatformState.MutablePlatformState> platformStateFactory
    ) {
        this.suiJsonRpcClient = suiJsonRpcClient;
        this.platformStateFactory = platformStateFactory;
    }

    public PlatformState retrievePlatformState(String objectId) {
        SuiMoveObjectResponse<Platform> getObjectDataResponse = suiJsonRpcClient.getMoveObject(
                objectId, new SuiObjectDataOptions(true, true, true, true, true, true, true), Platform.class
        );
        if (getObjectDataResponse.getData() == null) {
            return null;
        }
        Platform platform = getObjectDataResponse.getData().getContent().getFields();
        return toPlatformState(platform);
    }

    private PlatformState toPlatformState(Platform platform) {
        PlatformState.MutablePlatformState platformState = platformStateFactory.apply(platform.getId().getId());
        platformState.setVersion(platform.getVersion());
        platformState.setName(platform.getName());
        platformState.setProjects(Arrays.asList(platform.getProjects()));
        return platformState;
    }

    
}

