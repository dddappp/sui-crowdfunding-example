// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.platform;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import java.util.Date;
import java.math.BigInteger;
import org.test.suicrowdfundingexample.domain.*;

public interface PlatformStateQueryRepository {
    PlatformState get(String id);

    Iterable<PlatformState> getAll(Integer firstResult, Integer maxResults);
    
    Iterable<PlatformState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<PlatformState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    PlatformState getFirst(Iterable<Map.Entry<String, Object>> filter, List<String> orders);

    PlatformState getFirst(Map.Entry<String, Object> keyValue, List<String> orders);

    Iterable<PlatformState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

}

