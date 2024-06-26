// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.project.hibernate;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suicrowdfundingexample.domain.*;
import org.hibernate.Session;
import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.SessionFactory;
import org.test.suicrowdfundingexample.domain.project.*;
import org.test.suicrowdfundingexample.specialization.*;
import org.test.suicrowdfundingexample.specialization.hibernate.*;
import org.springframework.transaction.annotation.Transactional;

public class HibernateProjectStateRepository implements ProjectStateRepository {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() { return this.sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    private static final Set<String> readOnlyPropertyPascalCaseNames = new HashSet<String>(Arrays.asList("Id", "Owner", "Title", "Description", "Target", "Deadline", "Image", "Vault", "Donations", "Version", "OffChainVersion", "CreatedBy", "CreatedAt", "UpdatedBy", "UpdatedAt", "Active", "Deleted", "TypeArgumentT"));
    
    private ReadOnlyProxyGenerator readOnlyProxyGenerator;
    
    public ReadOnlyProxyGenerator getReadOnlyProxyGenerator() {
        return readOnlyProxyGenerator;
    }

    public void setReadOnlyProxyGenerator(ReadOnlyProxyGenerator readOnlyProxyGenerator) {
        this.readOnlyProxyGenerator = readOnlyProxyGenerator;
    }

    @Transactional(readOnly = true)
    public ProjectState get(String id, boolean nullAllowed) {
        ProjectState.SqlProjectState state = (ProjectState.SqlProjectState)getCurrentSession().get(AbstractProjectState.SimpleProjectState.class, id);
        if (!nullAllowed && state == null) {
            state = new AbstractProjectState.SimpleProjectState();
            state.setId(id);
        }
        if (getReadOnlyProxyGenerator() != null && state != null) {
            return (ProjectState) getReadOnlyProxyGenerator().createProxy(state, new Class[]{ProjectState.SqlProjectState.class, Saveable.class}, "getStateReadOnly", readOnlyPropertyPascalCaseNames);
        }
        return state;
    }

    public void save(ProjectState state) {
        ProjectState s = state;
        if (getReadOnlyProxyGenerator() != null) {
            s = (ProjectState) getReadOnlyProxyGenerator().getTarget(state);
        }
        if(s.getOffChainVersion() == null) {
            getCurrentSession().save(s);
        } else {
            getCurrentSession().update(s);
        }

        if (s instanceof Saveable)
        {
            Saveable saveable = (Saveable) s;
            saveable.save();
        }
        getCurrentSession().flush();
    }

    public void merge(ProjectState detached) {
        ProjectState persistent = getCurrentSession().get(AbstractProjectState.SimpleProjectState.class, detached.getId());
        if (persistent != null) {
            merge(persistent, detached);
            getCurrentSession().save(persistent);
        } else {
            getCurrentSession().save(detached);
        }
        getCurrentSession().flush();
    }

    private void merge(ProjectState persistent, ProjectState detached) {
        ((AbstractProjectState) persistent).merge(detached);
    }

}

