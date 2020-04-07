package cs455.hadoop.maxMonitoringSites;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

public class CountySiteNumWritable implements Writable {
    private int countyCode;
    private int siteNum;

    public CountySiteNumWritable() {
    }

    public CountySiteNumWritable(int countyCode, int siteNum) {
        this.countyCode = countyCode;
        this.siteNum = siteNum;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        WritableUtils.writeVInt(out, countyCode);
        WritableUtils.writeVInt(out, siteNum);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        countyCode = WritableUtils.readVInt(in);
        siteNum = WritableUtils.readVInt(in);
    }

    @Override
    public String toString() {
        return "CountySiteNumWritable{" +
                "countyCode=" + countyCode +
                ", siteNum=" + siteNum +
                '}';
    }

    public int getCountyCode() {
        return countyCode;
    }

    public int getSiteNum() {
        return siteNum;
    }
}
