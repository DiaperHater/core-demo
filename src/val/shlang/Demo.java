package val.shlang;

public abstract class Demo {

    protected abstract void demo();

    public void run(){
        System.out.println("++++++++++++"+ this.getClass().getSimpleName() +"++++++++++++++++");

        demo();

        System.out.println("-------------END_OF "+ this.getClass().getSimpleName() +"---------------");
    }
}
