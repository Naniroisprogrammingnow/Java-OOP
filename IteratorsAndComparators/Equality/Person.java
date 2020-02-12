package EqualityLogic;

import java.util.Comparator;

public class Person implements Comparable<Person> {
    private String name;
    private Integer age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o){
        if (this==o){
            return true;
        }

        if (o instanceof Person){
            if (this.name.equals(((Person) o).name) &&
                    this.age==((Person) o).getAge()){
                    return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode(){
        return this.name.hashCode()+this.age.hashCode();
    }

    @Override
    public int compareTo(Person o) {
        if (this.name.compareTo(o.getName())==0){
            return this.age.compareTo(o.getAge());
        }
        return this.name.compareTo(o.getName());
    }
}
