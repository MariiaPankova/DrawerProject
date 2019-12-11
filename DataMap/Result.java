package DataMap;

public class Result{
    private int count;
    private int max;

    public int getCount() {
        return count;
    }

    public int getMax() {
        return max;
    }

    public DataMap getDm() {
        return dm;
    }

    private DataMap dm;

    public Result(int count, int max, DataMap dm) {
        this.count = count;
        this.max = max;
        this.dm = dm;
    }


}