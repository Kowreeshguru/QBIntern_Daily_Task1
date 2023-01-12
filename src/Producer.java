public class Producer {
    int id;
    String name;
    int stock;
    int price;
    Producer(){}
    Producer(int id,String name,int stock,int price){
        this.id=id;
        this.name=name;
        this.stock=stock;
        this.price=price;
    }
    void mang_warehouse(int val){
            this.stock = this.stock - val;
            System.out.println("Updated successfully\n");
    }
    void disp_warehouse(){
        System.out.println(id+"\t"+name+"\t"+stock+"\t"+price);
    }
}
