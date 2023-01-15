import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Retailer extends Wholesaler{
    int r_id;
    String r_name;
    ArrayList<Producer> retail_productlist=new ArrayList<>();
    Retailer(int id,String name){
        this.r_id=id;
        this.r_name=name;
        this.retail_productlist= new ArrayList<Producer>();
//        }
    }
    void add_retailer_stock(Retailer retail){
        boolean check_wholesaler_id=true;
        System.out.println("Enter wholesaler id: ");
        while(check_wholesaler_id) {
            int wholesaler_id = sc.nextInt();
            for(Wholesaler temp_whole: wholesaler){
                if(temp_whole.w_id == wholesaler_id) {
                    check_wholesaler_id = false;
                    ArrayList<Producer> billing_section = new ArrayList<>();
                    temp_whole.disp_whole_stock();
                    if (temp_whole.whole_productlist.isEmpty()) {
                        check_wholesaler_id = true;
                    } else {
                        System.out.println("Enter the needed Product id:");
                        boolean check_product_id = true;
                        while (check_product_id) {
                            int choose_product = sc.nextInt();
//                            for (Producer temp_product : apple) {
//                                if (temp_product.id == choose_product) {
                                    for (Producer whole_product : temp_whole.whole_productlist) {
                                        if (whole_product.id == choose_product) {

                                            System.out.println("Enter the needed Product quantity:");
                                            boolean is_quantity = true;
                                            while (is_quantity) {
                                                int product_quantity = sc.nextInt();
                                                if (product_quantity >= 0 && whole_product.stock > product_quantity) {
                                                    is_quantity = false;
                                                    if (retail_productlist.isEmpty()) {
                                                        System.out.println("New product---Enter your price :");
                                                        int retailer_price = sc.nextInt();
                                                        Producer add_prod = new Producer(whole_product.id, whole_product.name, product_quantity, retailer_price, whole_product.gst);
                                                        retail.retail_productlist.add(add_prod);
                                                        temp_whole.mang_whole_stock(product_quantity,whole_product);
                                                    } else {
                                                        boolean flag = true;
                                                        for (Producer prod : retail_productlist) {
                                                            if (prod.id == choose_product) {
                                                                flag = false;
                                                                prod.stock = prod.stock + product_quantity;
//                                                            temp_product.mang_warehouse(product_quantity);
                                                            }
                                                        }
                                                        if (flag) {
                                                            System.out.println("New product---Enter your price :");
                                                            int retailer_price = sc.nextInt();
                                                            Producer add_prod = new Producer(whole_product.id, whole_product.name, product_quantity, retailer_price, whole_product.gst);
                                                            retail.retail_productlist.add(add_prod);
                                                            whole_product.mang_warehouse(product_quantity);
                                                        }
                                                    }
                                                    if (billing_section.isEmpty()) {
                                                        Producer add_prod = new Producer(whole_product.id, whole_product.name, product_quantity, whole_product.price, whole_product.gst);
                                                        billing_section.add(add_prod);
                                                        System.out.println("*** Added to bill ***");
                                                    } else {
                                                        boolean flag = true;
                                                        for (Producer prod1 : billing_section) {
                                                            if (prod1.id == choose_product) {
                                                                flag = false;
                                                                prod1.stock = prod1.stock + product_quantity;
                                                                System.out.println("*** Added to bill ***");
                                                            }
                                                        }
                                                        if (flag) {
                                                            Producer add_prod = new Producer(whole_product.id, whole_product.name, product_quantity, whole_product.price, whole_product.gst);
                                                            billing_section.add(add_prod);
                                                            System.out.println("*** Added to bill ***");
                                                        }
                                                    }

                                                } else {
                                                    System.out.println("Availability stock is :" + whole_product.stock);
                                                }
                                            }check_product_id = false;
                                        }

                                    }

//                                }
//                            }
                            if (check_product_id) {
                                System.out.println("Enter the Valid Product id:");
                            }
//                        for(Producer i:billing_section){
//                            System.out.println(i.id+""+i.name+""+i.stock);
//                        }
                            if (!check_product_id) {
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
                                        generate_Bill_retailer(retail.r_id, retail.r_name,billing_section);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (DocumentException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(check_wholesaler_id){
                System.out.println("Enter the Valid Wholesaler id:");
            }
        }
    }

    void disp_ret_stock(){
        System.out.println(r_id+"\t"+r_name+" has products like");
        try {
            if (retail_productlist.size() == 0) {
                System.out.println("No product available");
            } else {
                for (Producer r_list : this.retail_productlist) {
                    System.out.println("\t"+r_list.id + "\t" + r_list.name + "\t" + r_list.stock + "\t" + r_list.price);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
