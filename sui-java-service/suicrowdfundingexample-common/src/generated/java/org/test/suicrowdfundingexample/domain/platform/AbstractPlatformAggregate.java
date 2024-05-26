// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.platform;

import java.util.*;
import java.util.Date;
import java.math.BigInteger;
import org.test.suicrowdfundingexample.domain.*;
import org.test.suicrowdfundingexample.specialization.*;

public abstract class AbstractPlatformAggregate extends AbstractAggregate implements PlatformAggregate {
    private PlatformState.MutablePlatformState state;

    private List<Event> changes = new ArrayList<Event>();

    public AbstractPlatformAggregate(PlatformState state) {
        this.state = (PlatformState.MutablePlatformState)state;
    }

    public PlatformState getState() {
        return this.state;
    }

    public List<Event> getChanges() {
        return this.changes;
    }

    public void throwOnInvalidStateTransition(Command c) {
        PlatformCommand.throwOnInvalidStateTransition(this.state, c);
    }

    protected void apply(Event e) {
        onApplying(e);
        state.mutate(e);
        changes.add(e);
    }


    ////////////////////////

    public static class SimplePlatformAggregate extends AbstractPlatformAggregate {
        public SimplePlatformAggregate(PlatformState state) {
            super(state);
        }

        @Override
        public void addProject(String projectId, Long offChainVersion, String commandId, String requesterId, PlatformCommands.AddProject c) {
            java.util.function.Supplier<PlatformEvent.ProjectAddedToPlatform> eventFactory = () -> newProjectAddedToPlatform(projectId, offChainVersion, commandId, requesterId);
            PlatformEvent.ProjectAddedToPlatform e;
            try {
                e = verifyAddProject(eventFactory, projectId, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void create(String[] projects, Long offChainVersion, String commandId, String requesterId, PlatformCommands.Create c) {
            java.util.function.Supplier<PlatformEvent.PlatformCreated> eventFactory = () -> newPlatformCreated(projects, offChainVersion, commandId, requesterId);
            PlatformEvent.PlatformCreated e;
            try {
                e = verifyCreate(eventFactory, projects, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        protected PlatformEvent.InitPlatformEvent verify__Init__(java.util.function.Supplier<PlatformEvent.InitPlatformEvent> eventFactory, PlatformCommands.__Init__ c) {

            PlatformEvent.InitPlatformEvent e = (PlatformEvent.InitPlatformEvent) ReflectUtils.invokeStaticMethod(
                    "org.test.suicrowdfundingexample.domain.platform.__Init__Logic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, PlatformState.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), VerificationContext.forCommand(c)}
            );

//package org.test.suicrowdfundingexample.domain.platform;
//
//public class __Init__Logic {
//    public static PlatformEvent.InitPlatformEvent verify(java.util.function.Supplier<PlatformEvent.InitPlatformEvent> eventFactory, PlatformState platformState, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected PlatformEvent.ProjectAddedToPlatform verifyAddProject(java.util.function.Supplier<PlatformEvent.ProjectAddedToPlatform> eventFactory, String projectId, PlatformCommands.AddProject c) {
            String ProjectId = projectId;

            PlatformEvent.ProjectAddedToPlatform e = (PlatformEvent.ProjectAddedToPlatform) ReflectUtils.invokeStaticMethod(
                    "org.test.suicrowdfundingexample.domain.platform.AddProjectLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, PlatformState.class, String.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), projectId, VerificationContext.forCommand(c)}
            );

//package org.test.suicrowdfundingexample.domain.platform;
//
//public class AddProjectLogic {
//    public static PlatformEvent.ProjectAddedToPlatform verify(java.util.function.Supplier<PlatformEvent.ProjectAddedToPlatform> eventFactory, PlatformState platformState, String projectId, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected PlatformEvent.PlatformCreated verifyCreate(java.util.function.Supplier<PlatformEvent.PlatformCreated> eventFactory, String[] projects, PlatformCommands.Create c) {
            String[] Projects = projects;

            PlatformEvent.PlatformCreated e = (PlatformEvent.PlatformCreated) ReflectUtils.invokeStaticMethod(
                    "org.test.suicrowdfundingexample.domain.platform.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, PlatformState.class, String[].class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), projects, VerificationContext.forCommand(c)}
            );

//package org.test.suicrowdfundingexample.domain.platform;
//
//public class CreateLogic {
//    public static PlatformEvent.PlatformCreated verify(java.util.function.Supplier<PlatformEvent.PlatformCreated> eventFactory, PlatformState platformState, String[] projects, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected AbstractPlatformEvent.InitPlatformEvent newInitPlatformEvent(Long offChainVersion, String commandId, String requesterId) {
            PlatformEventId eventId = new PlatformEventId(getState().getId(), null);
            AbstractPlatformEvent.InitPlatformEvent e = new AbstractPlatformEvent.InitPlatformEvent();

            e.setSuiTimestamp(null);
            e.setSuiTxDigest(null);
            e.setSuiEventSeq(null);
            e.setSuiPackageId(null);
            e.setSuiTransactionModule(null);
            e.setSuiSender(null);
            e.setSuiType(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setPlatformEventId(eventId);
            return e;
        }

        protected AbstractPlatformEvent.ProjectAddedToPlatform newProjectAddedToPlatform(String projectId, Long offChainVersion, String commandId, String requesterId) {
            PlatformEventId eventId = new PlatformEventId(getState().getId(), null);
            AbstractPlatformEvent.ProjectAddedToPlatform e = new AbstractPlatformEvent.ProjectAddedToPlatform();

            e.setProjectId(projectId);
            e.setSuiTimestamp(null);
            e.setSuiTxDigest(null);
            e.setSuiEventSeq(null);
            e.setSuiPackageId(null);
            e.setSuiTransactionModule(null);
            e.setSuiSender(null);
            e.setSuiType(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setPlatformEventId(eventId);
            return e;
        }

        protected AbstractPlatformEvent.PlatformCreated newPlatformCreated(String[] projects, Long offChainVersion, String commandId, String requesterId) {
            PlatformEventId eventId = new PlatformEventId(getState().getId(), null);
            AbstractPlatformEvent.PlatformCreated e = new AbstractPlatformEvent.PlatformCreated();

            e.setProjects(projects);
            e.setSuiTimestamp(null);
            e.setSuiTxDigest(null);
            e.setSuiEventSeq(null);
            e.setSuiPackageId(null);
            e.setSuiTransactionModule(null);
            e.setSuiSender(null);
            e.setSuiType(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setPlatformEventId(eventId);
            return e;
        }

    }

}

