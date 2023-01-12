public class Wholesaler extends Producer {
    int w_id;
    String w_name;
    int whole_stock;
    String prod_name;
    int whole_price;
    Wholesaler(){}
    Wholesaler(int id,String name,int stock,String p_name,int price){
        this.w_id=id;
        this.w_name=name;
        this.whole_stock=stock;
        this.prod_name=p_name;
        this.whole_price=price;
    }
    void add_stock(int whole_stock){
        this.whole_stock=this.whole_stock+whole_stock;
    }
    void mang_whole_stock(int val){
        this.whole_stock=this.whole_stock-val;
    }
    void disp_whole_stock(){
        System.out.println(w_id+"\t"+w_name+"\t"+whole_stock+"\t"+prod_name+"\t"+whole_price);
    }
}
