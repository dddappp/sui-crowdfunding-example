// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.project;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suicrowdfundingexample.domain.*;
import org.test.suicrowdfundingexample.domain.Command;
import org.test.suicrowdfundingexample.specialization.DomainError;

public interface ProjectCommand extends Command {

    String getId();

    void setId(String id);

    Long getOffChainVersion();

    void setOffChainVersion(Long offChainVersion);

    static void throwOnInvalidStateTransition(ProjectState state, Command c) {
        if (state.getOffChainVersion() == null) {
            if (isCommandCreate((ProjectCommand)c)) {
                return;
            }
            throw DomainError.named("premature", "Can't do anything to unexistent aggregate");
        }
        if (state.getDeleted() != null && state.getDeleted()) {
            throw DomainError.named("zombie", "Can't do anything to deleted aggregate.");
        }
        if (isCommandCreate((ProjectCommand)c))
            throw DomainError.named("rebirth", "Can't create aggregate that already exists");
    }

    static boolean isCommandCreate(ProjectCommand c) {
        if (c.getOffChainVersion().equals(ProjectState.VERSION_NULL))
            return true;
        return false;
    }

}

