// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain.project;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suicrowdfundingexample.domain.*;

public class ProjectCommands {
    private ProjectCommands() {
    }

    public static class Create extends AbstractProjectCommand implements ProjectCommand {

        public String getCommandType() {
            return "Create";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Id
         */
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * Platform
         */
        private String platform;

        public String getPlatform() {
            return this.platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        /**
         * Title
         */
        private String title;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * Description
         */
        private String description;

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * Target
         */
        private BigInteger target;

        public BigInteger getTarget() {
            return this.target;
        }

        public void setTarget(BigInteger target) {
            this.target = target;
        }

        /**
         * Image
         */
        private String image;

        public String getImage() {
            return this.image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class Update extends AbstractProjectCommand implements ProjectCommand {

        public String getCommandType() {
            return "Update";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Id
         */
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * Title
         */
        private String title;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * Description
         */
        private String description;

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * Target
         */
        private BigInteger target;

        public BigInteger getTarget() {
            return this.target;
        }

        public void setTarget(BigInteger target) {
            this.target = target;
        }

        /**
         * Image
         */
        private String image;

        public String getImage() {
            return this.image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class Start extends AbstractProjectCommand implements ProjectCommand {

        public String getCommandType() {
            return "Start";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Id
         */
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * Clock
         */
        private String clock;

        public String getClock() {
            return this.clock;
        }

        public void setClock(String clock) {
            this.clock = clock;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class Donate extends AbstractProjectCommand implements ProjectCommand {

        public String getCommandType() {
            return "Donate";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Id
         */
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * Clock
         */
        private String clock;

        public String getClock() {
            return this.clock;
        }

        public void setClock(String clock) {
            this.clock = clock;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class Withdraw extends AbstractProjectCommand implements ProjectCommand {

        public String getCommandType() {
            return "Withdraw";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Id
         */
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * Clock
         */
        private String clock;

        public String getClock() {
            return this.clock;
        }

        public void setClock(String clock) {
            this.clock = clock;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class Refund extends AbstractProjectCommand implements ProjectCommand {

        public String getCommandType() {
            return "Refund";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Id
         */
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * Clock
         */
        private String clock;

        public String getClock() {
            return this.clock;
        }

        public void setClock(String clock) {
            this.clock = clock;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

}

