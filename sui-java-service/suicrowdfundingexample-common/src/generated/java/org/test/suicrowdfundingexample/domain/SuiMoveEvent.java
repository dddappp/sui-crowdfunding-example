// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain;

import java.math.*;
import java.util.*;

public interface SuiMoveEvent {

    String getSuiPackageId();

    String getSuiTransactionModule();

    String getSuiSender();

    String getSuiType();

    interface MutableSuiMoveEvent {

        void setSuiPackageId(String p);

        void setSuiTransactionModule(String p);

        void setSuiSender(String p);

        void setSuiType(String p);

    }

}
