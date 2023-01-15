import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.*;

public class Wholesaler extends Producer {
    int w_id;
    String w_name;
//    int prod_id;
    ArrayList<Producer> whole_productlist=new ArrayList<>();
    Wholesaler(){}
    Wholesaler(int id,String name){
        this.w_id=id;
        this.w_name=name;
        this.whole_productlist= new ArrayList<Producer>();
        Producer prod1=new Producer(100,"iphone12",1000,45000,12);
        Producer prod2=new Producer(101,"iphone13",1000,50000,15);
        whole_productlist.add(prod1);
        whole_productlist.add(prod2);
    }
    Scanner sc=new Scanner(System.in);
    void add_stock(Wholesaler whole){
        ArrayList<Producer> billing_section= new ArrayList<>();
        System.out.println("Enter the needed Product id:");
        boolean check_product_id=true;
        while(check_product_id) {
            int choose_product=sc.nextInt();
            for(Producer temp_product: apple){
                if(temp_product.id == choose_product){
                    check_product_id=false;
                    System.out.println("Enter the needed Product quantity:");
                    boolean is_quantity=true;
                    while (is_quantity){
                        int product_quantity= sc.nextInt();
                        if(product_quantity >= 0 && temp_product.stock>product_quantity){
                            is_quantity=false;
                            if(whole_productlist.isEmpty()){
                                System.out.println("New product---Enter your price :");
                                int wholesaler_price= sc.nextInt();
                                Producer add_prod = new Producer(temp_product.id, temp_product.name, product_quantity, wholesaler_price,temp_product.gst);
                                whole.whole_productlist.add(add_prod);
                                temp_product.mang_warehouse(product_quantity);
                            }
                            else{
                                boolean flag=true;
                                for (Producer prod : whole_productlist) {
                                    if (prod.id == choose_product) {
                                        flag=false;
                                        prod.stock = prod.stock + product_quantity;
                                        temp_product.mang_warehouse(product_quantity);
                                    }
                                }
                                if(flag) {
                                    System.out.println("New product---Enter your price :");
                                    int wholesaler_price= sc.nextInt();
                                    Producer add_prod = new Producer(temp_product.id, temp_product.name, product_quantity, wholesaler_price, temp_product.gst);
                                    whole.whole_productlist.add(add_prod);
                                    temp_product.mang_warehouse(product_quantity);
                                }
                            }
                            if(billing_section.isEmpty()){
                                Producer add_prod = new Producer(temp_product.id, temp_product.name, product_quantity, temp_product.price,temp_product.gst);
                                billing_section.add(add_prod);
                                System.out.println("*** Add to bill ***");
                            }
                            else{
                                boolean flag=true;
                                for (Producer prod1 : billing_section) {
                                    if (prod1.id == choose_product) {
                                        flag=false;
                                        prod1.stock = prod1.stock + product_quantity;
                                        System.out.println("*** Add to bill ***");
                                    }
                                }
                                if(flag) {
                                    Producer add_prod = new Producer(temp_product.id, temp_product.name, product_quantity, temp_product.price,temp_product.gst);
                                    billing_section.add(add_prod);
                                    System.out.println("*** Add to bill ***");
                                }
                            }

                        }
                        else{
                            System.out.println("Availability stock is :"+temp_product.stock);
                        }
                    }
                }
            }
            if(check_product_id){
                System.out.println("Enter the Valid Product id:");
            }
            if(!check_product_id) {
                System.out.println("Press\n1. Continue adding products\n2. Checkout");
                int check_billing = sc.nextInt();
                if (check_billing == 1) {
                    System.out.println("Enter the needed Product id:");
                    check_product_id = true;
                } else if (check_billing == 2) {
                    for(Producer pro:billing_section){
                        System.out.println(pro.name+" "+pro.stock);
                    }

                    try {
                        generate_Bill_wholesaler(whole.w_id, whole.w_name,billing_section);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
    void mang_whole_stock(int val,Producer prod){
        for(Producer prod2: whole_productlist){
            if(prod2.id==prod.id){
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
