// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.project;

import java.math.BigInteger;
import java.util.Date;
import org.test.suicrowdfundingexample.domain.*;

public interface DonationEventDao {
    void save(DonationEvent e);

    Iterable<DonationEvent> findByProjectEventId(ProjectEventId projectEventId);

}

