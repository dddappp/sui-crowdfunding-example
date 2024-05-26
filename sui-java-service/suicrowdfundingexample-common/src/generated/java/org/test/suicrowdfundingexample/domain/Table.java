// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suicrowdfundingexample.domain;

import java.io.Serializable;
import org.test.suicrowdfundingexample.domain.*;

public class Table implements Serializable {
    private String id;

    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    private Long size;

    public Long getSize()
    {
        return this.size;
    }

    public void setSize(Long size)
    {
        this.size = size;
    }

    public Table()
    {
    }

    public Table(String id, Long size)
    {
        this.id = id;
        this.size = size;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Table other = (Table)obj;
        return true 
            && (id == other.id || (id != null && id.equals(other.id)))
            && (size == other.size || (size != null && size.equals(other.size)))
            ;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        if (this.id != null) {
            hash += 13 * this.id.hashCode();
        }
        if (this.size != null) {
            hash += 13 * this.size.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + '\'' + id + '\'' +
                ", size=" + size +
                '}';
    }


}

