package org.uxcrowd.education.pojo;

import java.util.ArrayList;
import java.util.Date;

public class DoneTask{
    public Integer id;
    public String url;
    public Date doneDate;
    public Integer cost;
    public String testerTaskStatus;
    public String testerTaskPaymentStatus;
    public String type;
    public ArrayList<String> rollbackComments;
}