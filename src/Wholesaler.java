import java.util.ArrayList;

public class Wholesaler extends Producer {
    int w_id;
    String w_name;
//    int prod_id;
    ArrayList<Producer> whole_productlist=new ArrayList<>();
    Wholesaler(){}
    Wholesaler(int id,String name){
        this.w_id=id;
        this.w_name=name;
//        ArrayList<Producer> producer= new ArrayList<>();
//        Producer init=new Producer(100,"iphone12",1000,45000);
        this.whole_productlist.add(new Producer(100,"iphone12",1000,50000));
        this.whole_productlist.add(new Producer(101,"iphone13",1000,55000));
        this.whole_productlist.add(new Producer(102,"iphone14",1000,60000));
    }
    void add_stock(Producer whole_stock){
        for(Producer prod1: whole_productlist) {
            if (prod1.id == whole_stock.id){
//                System.out.println("hello");
                prod1.stock=prod1.stock+whole_stock.stock;
//                super.mang_warehouse(whole_stock.id,whole_stock.stock);
            }
        }
    }
    void mang_whole_stock(int val,int id){
        for(Producer prod2: whole_productlist){
            if(prod2.id==id){
                prod2.stock = prod2.stock - val;
                System.out.println("Updated successfully\n");
            }
        }
    }
    void disp_whole_stock(){
        System.out.println(w_id+"\t"+w_name+" has products like");
        try {
            if (whole_productlist.size() == 0) {
                System.out.println("No product available");
            } else {
                for (Producer w_list : this.whole_productlist) {
                    System.out.println("\t"+w_list.id + "\t" + w_list.name + "\t" + w_list.stock + "\t" + w_list.price);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
