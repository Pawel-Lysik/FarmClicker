package utils;
public class Resource {
    public String name;
    public int cost, value, count=0;
    Resource(String n, int c, int v) {
        name=n;
        cost=c;
        value=v;
    }
    public void buy(State state) {
        count++;
        state.budget -= cost;
    }
    public void sell(State state) {
        count--;
        state.budget += value;
    }
}
