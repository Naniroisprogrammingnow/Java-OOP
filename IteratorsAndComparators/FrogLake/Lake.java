package Froggy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lake implements Iterable<Integer> {
    private List<Integer> lake;

    public Lake(){
        this.lake = new ArrayList<>();
    }

    public void add(List<Integer> lake){
        this.lake = lake;
    }

    private final class Frog implements Iterator{
        private int position;

        @Override
        public boolean hasNext() {
            return this.position<lake.size();
        }

        @Override
        public Integer next() {
            int res = lake.get(this.position);
            if(this.position%2==0 && this.position+2>=lake.size()){
                this.position=1;
                return res;
            }
            this.position+=2;
            return res;
        }
    }
    @Override
    public Iterator iterator() {
        return new Frog();
    }
}
