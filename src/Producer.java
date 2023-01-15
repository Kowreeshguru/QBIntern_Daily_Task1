import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class Producer extends Test{
    int id;
    String name;
    int stock;
    int price;
    int gst;
    Producer(){}
    Producer(int id,String name,int stock,int price,int gst){
        this.id=id;
        this.name=name;
        this.stock=stock;
        this.price=price;
        this.gst=gst;
    }
    void mang_warehouse(int val){
            this.stock = this.stock - val;
//            System.out.println("Updated successfully\n");
    }
    void disp_warehouse(){
        System.out.println(id+"\t"+name+"\t"+stock+"\t"+price+"\t"+gst);
    }
    void generate_Bill_wholesaler(int clientId,String clientName ,ArrayList<Producer> pro) throws IOException, DocumentException {
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("Bill_"+clientName+".pdf"));
                document.open();
                document.add(new Paragraph("Welcome - " + clientName));
                for(Producer pro1:pro) {
                    int total_price=pro1.stock*pro1.price;
                    int after_dis=0;
                    if(pro1.stock < 10){
                        System.out.println("less 10");
                        after_dis=total_price;
                    }
                    else if(pro1.stock>=10 && pro1.stock<50){
                        System.out.println("less 500");
                        after_dis=total_price-((total_price*10)/100);
                    }
                    else if(pro1.stock>=50 && pro1.stock<100){
                        System.out.println("less 00");
                        after_dis=total_price-((total_price*20)/100);
                    }
                    else if(pro1.stock>=100){
                        System.out.println("less 100");
                        after_dis=total_price-((total_price*30)/100);
                    }

                    int after_gst= after_dis +((after_dis*pro1.gst)/100);
                    document.add(new Paragraph( pro1.id + " - " + pro1.name + " - " + pro1.stock+" - "+total_price+" - "+after_dis+" - "+pro1.gst+"% - "+after_gst));
                }
                document.add(new Paragraph("***** Thank you - Visit again *****"));
                document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    void generate_Bill_retailer(int clientId,String clientName ,ArrayList<Producer> pro) throws IOException, DocumentException {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Bill_"+clientName+".pdf"));
            document.open();
            document.add(new Paragraph("Welcome - " + clientName));
            for(Producer pro1:pro) {
                int total_price=pro1.stock*pro1.price;
                int after_dis=0;
                if(pro1.stock < 10){
                    System.out.println("less 10");
                    after_dis=total_price;
                }
                else if(pro1.stock>=10 && pro1.stock<50){
                    System.out.println("less 500");
                    after_dis=total_price-((total_price*5)/100);
                }
                else if(pro1.stock>=50 && pro1.stock<100){
                    System.out.println("less 00");
                    after_dis=total_price-((total_price*10)/100);
                }
                else if(pro1.stock>=100){
                    System.out.println("less 100");
                    after_dis=total_price-((total_price*15)/100);
                }

                int after_gst= after_dis +((after_dis*pro1.gst)/100);
                document.add(new Paragraph( pro1.id + " - " + pro1.name + " - " + pro1.stock+" - "+total_price+" - "+after_dis+" - "+pro1.gst+"% - "+after_gst));
            }
            document.add(new Paragraph("***** Thank you - Visit again *****"));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
class Test{
    public static ArrayList<Producer> apple;
    public static ArrayList<Wholesaler> wholesaler;
    public static ArrayList<Retailer> retailer;
    public static void main(String args[]){
        String prod_fileName = "/Users/kowreesh/Downloads/Producer.csv";
        File prod_file = new File(prod_fileName);
        List<List<String>> prod = new ArrayList<>();
        Scanner prod_inputStream;
        try {

            prod_inputStream = new Scanner(prod_file);

            while (prod_inputStream.hasNext()) {
                String prod_line = prod_inputStream.next();
                String[] values = prod_line.split(",");
                prod.add(Arrays.asList(values));
            }
            prod_inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("hello1");
            e.printStackTrace();
        }
        apple = new ArrayList<>();
        for (int i = 0; i < prod.size(); i++) {
            apple.add(new Producer((Integer.parseInt(prod.get(i).get(0))),prod.get(i).get(1),Integer.parseInt(prod.get(i).get(2)),Integer.parseInt(prod.get(i).get(3)),Integer.parseInt(prod.get(i).get(4))));
        }


        String whole_fileName = "/Users/kowreesh/Downloads/Wholesaler.csv";
        File whole_file = new File(whole_fileName);
        List<List<String>> whole = new ArrayList<>();
        Scanner whole_inputStream;
        try {

            whole_inputStream = new Scanner(whole_file);

            while (whole_inputStream.hasNext()) {
                String whole_line = whole_inputStream.next();
                String[] whole_values = whole_line.split(",");
                whole.add(Arrays.asList(whole_values));
            }
            whole_inputStream.close();
        } catch (FileNotFoundException e1) {
            System.out.println("hello2");
            e1.printStackTrace();
        }

        wholesaler = new ArrayList<>();
        for (int i = 0; i < whole.size(); i++) {
            wholesaler.add(new Wholesaler(Integer.parseInt(whole.get(i).get(0)),whole.get(i).get(1)));
        }


        String ret_fileName = "/Users/kowreesh/Downloads/Retailer.csv";
        File ret_file = new File(ret_fileName);
        List<List<String>> ret = new ArrayList<>();
        Scanner ret_inputStream;
        try {
            ret_inputStream = new Scanner(ret_file);

            while (ret_inputStream.hasNext()) {
                String ret_line = ret_inputStream.next();
                String[] ret_values = ret_line.split(",");
                ret.add(Arrays.asList(ret_values));
            }
            ret_inputStream.close();
        } catch (FileNotFoundException e2) {
            System.out.println("hello3");
            e2.printStackTrace();
        }
        retailer = new ArrayList<>();
        for (int i = 0; i < ret.size(); i++) {
            retailer.add(new Retailer(Integer.parseInt(ret.get(i).get(0)),ret.get(i).get(1)));
        }

        Scanner sc=new Scanner(System.in);
        boolean check=true;

        while(check) {
            System.out.println("--------------Choose action--------------\n1. View Producer\n2. View Wholesaler\n3. View Retailer\n4. Add to wholesaler\n5. Add to Retailer\n6. Exit");
            int role = sc.nextInt();
            switch (role) {
                case 1:
                    for(Producer product_obj: apple){
                        product_obj.disp_warehouse();
                    }
                    break;
                case 2:
                    for(Wholesaler wholesaler_obj: wholesaler){
                        wholesaler_obj.disp_whole_stock();
                    }
                    break;
                case 3:
                    for(Retailer retail_obj: retailer){
                        retail_obj.disp_ret_stock();
                    }
                    break;
                case 4:
                    boolean check_wholesaler_id=true;
                    System.out.println("Enter wholesaler id: ");
                    while(check_wholesaler_id) {
                        int wholesaler_id = sc.nextInt();
                        for(Wholesaler temp_whole: wholesaler){
                            if(temp_whole.w_id == wholesaler_id){
                                check_wholesaler_id=false;
                                temp_whole.add_stock(temp_whole);
                            }
                        }
                        if(check_wholesaler_id){
                            System.out.println("Enter the Valid Wholesaler id:");
                        }
                    }
                    break;
                case 5:
                    boolean check_retailer_id=true;
                    System.out.println("Enter Retailer id: ");
                    while(check_retailer_id) {
                        int retailer_id = sc.nextInt();
                        for(Retailer temp_retail: retailer){
                            if(temp_retail.r_id == retailer_id){
                                check_retailer_id=false;
                                temp_retail.add_retailer_stock(temp_retail);
                            }
                        }
                        if(check_retailer_id){
                            System.out.println("Enter the Valid Retailer id:");
                        }
                    }
                    break;
                case 6:
                    check = false;
            }
        }
    }
}

