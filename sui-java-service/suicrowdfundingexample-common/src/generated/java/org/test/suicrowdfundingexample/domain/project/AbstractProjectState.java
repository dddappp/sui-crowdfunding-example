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
import org.test.suicrowdfundingexample.specialization.*;
import org.test.suicrowdfundingexample.domain.project.ProjectEvent.*;

public abstract class AbstractProjectState implements ProjectState.SqlProjectState, Saveable {

    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String owner;

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private BigInteger target;

    public BigInteger getTarget() {
        return this.target;
    }

    public void setTarget(BigInteger target) {
        this.target = target;
    }

    private BigInteger deadline;

    public BigInteger getDeadline() {
        return this.deadline;
    }

    public void setDeadline(BigInteger deadline) {
        this.deadline = deadline;
    }

    private String image;

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private java.math.BigInteger vault;

    public java.math.BigInteger getVault() {
        return this.vault;
    }

    public void setVault(java.math.BigInteger vault) {
        this.vault = vault;
    }

    private BigInteger version;

    public BigInteger getVersion() {
        return this.version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    private Long offChainVersion;

    public Long getOffChainVersion() {
        return this.offChainVersion;
    }

    public void setOffChainVersion(Long offChainVersion) {
        this.offChainVersion = offChainVersion;
    }

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

    private String updatedBy;

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    private Date updatedAt;

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    private Boolean active;

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    private Boolean deleted;

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    private String typeArgumentT;

    public String getTypeArgumentT() {
        return this.typeArgumentT;
    }

    public void setTypeArgumentT(String typeArgumentT) {
        this.typeArgumentT = typeArgumentT;
    }

    public boolean isStateUnsaved() {
        return this.getOffChainVersion() == null;
    }

    private Set<DonationState> protectedDonations = new HashSet<>();

    protected Set<DonationState> getProtectedDonations() {
        return this.protectedDonations;
    }

    protected void setProtectedDonations(Set<DonationState> protectedDonations) {
        this.protectedDonations = protectedDonations;
    }

    private EntityStateCollection<String, DonationState> donations;

    public EntityStateCollection<String, DonationState> getDonations() {
        return this.donations;
    }

    public void setDonations(EntityStateCollection<String, DonationState> donations) {
        this.donations = donations;
    }

    private Boolean stateReadOnly;

    public Boolean getStateReadOnly() { return this.stateReadOnly; }

    public void setStateReadOnly(Boolean readOnly) { this.stateReadOnly = readOnly; }

    private boolean forReapplying;

    public boolean getForReapplying() {
        return forReapplying;
    }

    public void setForReapplying(boolean forReapplying) {
        this.forReapplying = forReapplying;
    }

    public AbstractProjectState(List<Event> events) {
        initializeForReapplying();
        if (events != null && events.size() > 0) {
            this.setId(((ProjectEvent.SqlProjectEvent) events.get(0)).getProjectEventId().getId());
            for (Event e : events) {
                mutate(e);
                this.setOffChainVersion((this.getOffChainVersion() == null ? ProjectState.VERSION_NULL : this.getOffChainVersion()) + 1);
            }
        }
    }


    public AbstractProjectState() {
        initializeProperties();
    }

    protected void initializeForReapplying() {
        this.forReapplying = true;

        initializeProperties();
    }
    
    protected void initializeProperties() {
        donations = new SimpleDonationStateCollection();
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj instanceof ProjectState) {
            return Objects.equals(this.getId(), ((ProjectState)obj).getId());
        }
        return false;
    }


    public void mutate(Event e) {
        setStateReadOnly(false);
        if (false) { 
            ;
        } else if (e instanceof AbstractProjectEvent.ProjectCreated) {
            when((AbstractProjectEvent.ProjectCreated)e);
        } else if (e instanceof AbstractProjectEvent.ProjectUpdated) {
            when((AbstractProjectEvent.ProjectUpdated)e);
        } else if (e instanceof AbstractProjectEvent.ProjectStarted) {
            when((AbstractProjectEvent.ProjectStarted)e);
        } else {
            throw new UnsupportedOperationException(String.format("Unsupported event type: %1$s", e.getClass().getName()));
        }
    }

    public void merge(ProjectState s) {
        if (s == this) {
            return;
        }
        this.setOwner(s.getOwner());
        this.setTitle(s.getTitle());
        this.setDescription(s.getDescription());
        this.setTarget(s.getTarget());
        this.setDeadline(s.getDeadline());
        this.setImage(s.getImage());
        this.setVault(s.getVault());
        this.setVersion(s.getVersion());
        this.setActive(s.getActive());
        this.setTypeArgumentT(s.getTypeArgumentT());

        if (s.getDonations() != null) {
            Iterable<DonationState> iterable;
            if (s.getDonations().isLazy()) {
                iterable = s.getDonations().getLoadedStates();
            } else {
                iterable = s.getDonations();
            }
            if (iterable != null) {
                for (DonationState ss : iterable) {
                    DonationState thisInnerState = ((EntityStateCollection.ModifiableEntityStateCollection<String, DonationState>)this.getDonations()).getOrAddDefault(ss.getDonator());
                    ((AbstractDonationState) thisInnerState).merge(ss);
                }
            }
        }
        if (s.getDonations() != null) {
            if (s.getDonations() instanceof EntityStateCollection.RemovalLoggedEntityStateCollection) {
                if (((EntityStateCollection.RemovalLoggedEntityStateCollection)s.getDonations()).getRemovedStates() != null) {
                    for (DonationState ss : ((EntityStateCollection.RemovalLoggedEntityStateCollection<String, DonationState>)s.getDonations()).getRemovedStates()) {
                        DonationState thisInnerState = ((EntityStateCollection.ModifiableEntityStateCollection<String, DonationState>)this.getDonations()).getOrAddDefault(ss.getDonator());
                        ((EntityStateCollection.ModifiableEntityStateCollection)this.getDonations()).removeState(thisInnerState);
                    }
                }
            } else {
                if (s.getDonations().isAllLoaded()) {
                    Set<String> removedStateIds = new HashSet<>(this.getDonations().stream().map(i -> i.getDonator()).collect(java.util.stream.Collectors.toList()));
                    s.getDonations().forEach(i -> removedStateIds.remove(i.getDonator()));
                    for (String i : removedStateIds) {
                        DonationState thisInnerState = ((EntityStateCollection.ModifiableEntityStateCollection<String, DonationState>)this.getDonations()).getOrAddDefault(i);
                        ((EntityStateCollection.ModifiableEntityStateCollection)this.getDonations()).removeState(thisInnerState);
                    }
                } else {
                    throw new UnsupportedOperationException();
                }
            }
        }
    }

    public void when(AbstractProjectEvent.ProjectCreated e) {
        throwOnWrongEvent(e);

        String platformId = e.getPlatformId();
        String PlatformId = platformId;
        String owner = e.getOwner();
        String Owner = owner;
        String title = e.getTitle();
        String Title = title;
        String description = e.getDescription();
        String Description = description;
        BigInteger target = e.getTarget();
        BigInteger Target = target;
        String image = e.getImage();
        String Image = image;
        String tokenType = e.getTokenType();
        String TokenType = tokenType;
        Long suiTimestamp = e.getSuiTimestamp();
        Long SuiTimestamp = suiTimestamp;
        String suiTxDigest = e.getSuiTxDigest();
        String SuiTxDigest = suiTxDigest;
        BigInteger suiEventSeq = e.getSuiEventSeq();
        BigInteger SuiEventSeq = suiEventSeq;
        String suiPackageId = e.getSuiPackageId();
        String SuiPackageId = suiPackageId;
        String suiTransactionModule = e.getSuiTransactionModule();
        String SuiTransactionModule = suiTransactionModule;
        String suiSender = e.getSuiSender();
        String SuiSender = suiSender;
        String suiType = e.getSuiType();
        String SuiType = suiType;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        ProjectState updatedProjectState = (ProjectState) ReflectUtils.invokeStaticMethod(
                    "org.test.suicrowdfundingexample.domain.project.CreateLogic",
                    "mutate",
                    new Class[]{ProjectState.class, String.class, String.class, String.class, String.class, BigInteger.class, String.class, String.class, Long.class, String.class, BigInteger.class, String.class, String.class, String.class, String.class, String.class, MutationContext.class},
                    new Object[]{this, platformId, owner, title, description, target, image, tokenType, suiTimestamp, suiTxDigest, suiEventSeq, suiPackageId, suiTransactionModule, suiSender, suiType, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.suicrowdfundingexample.domain.project;
//
//public class CreateLogic {
//    public static ProjectState mutate(ProjectState projectState, String platformId, String owner, String title, String description, BigInteger target, String image, String tokenType, Long suiTimestamp, String suiTxDigest, BigInteger suiEventSeq, String suiPackageId, String suiTransactionModule, String suiSender, String suiType, String status, MutationContext<ProjectState, ProjectState.MutableProjectState> mutationContext) {
//    }
//}

        if (this != updatedProjectState) { merge(updatedProjectState); } //else do nothing

    }

    public void when(AbstractProjectEvent.ProjectUpdated e) {
        throwOnWrongEvent(e);

        String title = e.getTitle();
        String Title = title;
        String description = e.getDescription();
        String Description = description;
        BigInteger target = e.getTarget();
        BigInteger Target = target;
        String image = e.getImage();
        String Image = image;
        Long suiTimestamp = e.getSuiTimestamp();
        Long SuiTimestamp = suiTimestamp;
        String suiTxDigest = e.getSuiTxDigest();
        String SuiTxDigest = suiTxDigest;
        BigInteger suiEventSeq = e.getSuiEventSeq();
        BigInteger SuiEventSeq = suiEventSeq;
        String suiPackageId = e.getSuiPackageId();
        String SuiPackageId = suiPackageId;
        String suiTransactionModule = e.getSuiTransactionModule();
        String SuiTransactionModule = suiTransactionModule;
        String suiSender = e.getSuiSender();
        String SuiSender = suiSender;
        String suiType = e.getSuiType();
        String SuiType = suiType;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        ProjectState updatedProjectState = (ProjectState) ReflectUtils.invokeStaticMethod(
                    "org.test.suicrowdfundingexample.domain.project.UpdateLogic",
                    "mutate",
                    new Class[]{ProjectState.class, String.class, String.class, BigInteger.class, String.class, Long.class, String.class, BigInteger.class, String.class, String.class, String.class, String.class, String.class, MutationContext.class},
                    new Object[]{this, title, description, target, image, suiTimestamp, suiTxDigest, suiEventSeq, suiPackageId, suiTransactionModule, suiSender, suiType, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.suicrowdfundingexample.domain.project;
//
//public class UpdateLogic {
//    public static ProjectState mutate(ProjectState projectState, String title, String description, BigInteger target, String image, Long suiTimestamp, String suiTxDigest, BigInteger suiEventSeq, String suiPackageId, String suiTransactionModule, String suiSender, String suiType, String status, MutationContext<ProjectState, ProjectState.MutableProjectState> mutationContext) {
//    }
//}

        if (this != updatedProjectState) { merge(updatedProjectState); } //else do nothing

    }

    public void when(AbstractProjectEvent.ProjectStarted e) {
        throwOnWrongEvent(e);

        BigInteger deadline = e.getDeadline();
        BigInteger Deadline = deadline;
        Long suiTimestamp = e.getSuiTimestamp();
        Long SuiTimestamp = suiTimestamp;
        String suiTxDigest = e.getSuiTxDigest();
        String SuiTxDigest = suiTxDigest;
        BigInteger suiEventSeq = e.getSuiEventSeq();
        BigInteger SuiEventSeq = suiEventSeq;
        String suiPackageId = e.getSuiPackageId();
        String SuiPackageId = suiPackageId;
        String suiTransactionModule = e.getSuiTransactionModule();
        String SuiTransactionModule = suiTransactionModule;
        String suiSender = e.getSuiSender();
        String SuiSender = suiSender;
        String suiType = e.getSuiType();
        String SuiType = suiType;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        ProjectState updatedProjectState = (ProjectState) ReflectUtils.invokeStaticMethod(
                    "org.test.suicrowdfundingexample.domain.project.StartLogic",
                    "mutate",
                    new Class[]{ProjectState.class, BigInteger.class, Long.class, String.class, BigInteger.class, String.class, String.class, String.class, String.class, String.class, MutationContext.class},
                    new Object[]{this, deadline, suiTimestamp, suiTxDigest, suiEventSeq, suiPackageId, suiTransactionModule, suiSender, suiType, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.suicrowdfundingexample.domain.project;
//
//public class StartLogic {
//    public static ProjectState mutate(ProjectState projectState, BigInteger deadline, Long suiTimestamp, String suiTxDigest, BigInteger suiEventSeq, String suiPackageId, String suiTransactionModule, String suiSender, String suiType, String status, MutationContext<ProjectState, ProjectState.MutableProjectState> mutationContext) {
//    }
//}

        if (this != updatedProjectState) { merge(updatedProjectState); } //else do nothing

    }

    public void when(AbstractProjectEvent.DonationReceived e) {
        throwOnWrongEvent(e);

        String donator = e.getDonator();
        String Donator = donator;
        BigInteger amount = e.getAmount();
        BigInteger Amount = amount;
        Long suiTimestamp = e.getSuiTimestamp();
        Long SuiTimestamp = suiTimestamp;
        String suiTxDigest = e.getSuiTxDigest();
        String SuiTxDigest = suiTxDigest;
        BigInteger suiEventSeq = e.getSuiEventSeq();
        BigInteger SuiEventSeq = suiEventSeq;
        String suiPackageId = e.getSuiPackageId();
        String SuiPackageId = suiPackageId;
        String suiTransactionModule = e.getSuiTransactionModule();
        String SuiTransactionModule = suiTransactionModule;
        String suiSender = e.getSuiSender();
        String SuiSender = suiSender;
        String suiType = e.getSuiType();
        String SuiType = suiType;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        ProjectState updatedProjectState = (ProjectState) ReflectUtils.invokeStaticMethod(
                    "org.test.suicrowdfundingexample.domain.project.DonateLogic",
                    "mutate",
                    new Class[]{ProjectState.class, String.class, BigInteger.class, Long.class, String.class, BigInteger.class, String.class, String.class, String.class, String.class, String.class, MutationContext.class},
                    new Object[]{this, donator, amount, suiTimestamp, suiTxDigest, suiEventSeq, suiPackageId, suiTransactionModule, suiSender, suiType, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.suicrowdfundingexample.domain.project;
//
//public class DonateLogic {
//    public static ProjectState mutate(ProjectState projectState, String donator, BigInteger amount, Long suiTimestamp, String suiTxDigest, BigInteger suiEventSeq, String suiPackageId, String suiTransactionModule, String suiSender, String suiType, String status, MutationContext<ProjectState, ProjectState.MutableProjectState> mutationContext) {
//    }
//}

        if (this != updatedProjectState) { merge(updatedProjectState); } //else do nothing

    }

    public void save() {
        if (donations instanceof Saveable) {
            ((Saveable)donations).save();
        }
    }

    protected void throwOnWrongEvent(ProjectEvent event) {
        String stateEntityId = this.getId(); // Aggregate Id
        String eventEntityId = ((ProjectEvent.SqlProjectEvent)event).getProjectEventId().getId(); // EntityBase.Aggregate.GetEventIdPropertyIdName();
        if (!stateEntityId.equals(eventEntityId)) {
            throw DomainError.named("mutateWrongEntity", "Entity Id %1$s in state but entity id %2$s in event", stateEntityId, eventEntityId);
        }


        Long stateVersion = this.getOffChainVersion();

    }


    public static class SimpleProjectState extends AbstractProjectState {

        public SimpleProjectState() {
        }

        public SimpleProjectState(List<Event> events) {
            super(events);
        }

        public static SimpleProjectState newForReapplying() {
            SimpleProjectState s = new SimpleProjectState();
            s.initializeForReapplying();
            return s;
        }

    }


    class SimpleDonationStateCollection implements EntityStateCollection.ModifiableEntityStateCollection<String, DonationState>, Collection<DonationState> {

        @Override
        public DonationState get(String donator) {
            return protectedDonations.stream().filter(
                            e -> e.getDonator().equals(donator))
                    .findFirst().orElse(null);
        }

        @Override
        public boolean isLazy() {
            return false;
        }

        @Override
        public boolean isAllLoaded() {
            return true;
        }

        @Override
        public Collection<DonationState> getLoadedStates() {
            return protectedDonations;
        }

        @Override
        public DonationState getOrAddDefault(String donator) {
            DonationState s = get(donator);
            if (s == null) {
                ProjectDonationId globalId = new ProjectDonationId(getId(), donator);
                AbstractDonationState state = new AbstractDonationState.SimpleDonationState();
                state.setProjectDonationId(globalId);
                add(state);
                s = state;
            }
            return s;
        }

        @Override
        public int size() {
            return protectedDonations.size();
        }

        @Override
        public boolean isEmpty() {
            return protectedDonations.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return protectedDonations.contains(o);
        }

        @Override
        public Iterator<DonationState> iterator() {
            return protectedDonations.iterator();
        }

        @Override
        public java.util.stream.Stream<DonationState> stream() {
            return protectedDonations.stream();
        }

        @Override
        public Object[] toArray() {
            return protectedDonations.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return protectedDonations.toArray(a);
        }

        @Override
        public boolean add(DonationState s) {
            if (s instanceof AbstractDonationState) {
                AbstractDonationState state = (AbstractDonationState) s;
                state.setProtectedProjectState(AbstractProjectState.this);
            }
            return protectedDonations.add(s);
        }

        @Override
        public boolean remove(Object o) {
            if (o instanceof AbstractDonationState) {
                AbstractDonationState s = (AbstractDonationState) o;
                s.setProtectedProjectState(null);
            }
            return protectedDonations.remove(o);
        }

        @Override
        public boolean removeState(DonationState s) {
            return remove(s);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return protectedDonations.contains(c);
        }

        @Override
        public boolean addAll(Collection<? extends DonationState> c) {
            return protectedDonations.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return protectedDonations.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return protectedDonations.retainAll(c);
        }

        @Override
        public void clear() {
            protectedDonations.clear();
        }
    }


}

