// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.platform;

import java.util.*;
import org.dddml.support.criterion.Criterion;
import java.util.Date;
import java.math.BigInteger;
import org.test.suicrowdfundingexample.domain.*;

public interface PlatformStateRepository {
    PlatformState get(String id, boolean nullAllowed);

    void save(PlatformState state);

    void merge(PlatformState detached);
}

