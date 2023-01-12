import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Retailer extends Wholesaler{
    static int no_of_Wholesaler;
    static int no_of_retailer;
    int r_id;
    String r_name;
    int ret_stock;
    String ret_pname;
    int ret_price;
    Retailer(int id,String name,int stock,String p_name,int price){
        this.r_id=id;
        this.r_name=name;
        this.ret_stock=stock;
        this.ret_pname=p_name;
        this.ret_price=price;
    }
    void add_stock(int ret_stock){
        this.ret_stock=this.ret_stock+ret_stock;
    }
    void mang_ret_stock(int val){
        this.ret_stock=this.ret_stock+val;
    }
    void disp_ret_stock(){
        System.out.println(r_id+"\t"+r_name+"\t"+ret_stock+"\t"+ret_pname+"\t"+ret_price);
    }
    public static void main(String args[]){

        String prod_fileName= "/Users/kowreesh/Downloads/Producer.csv";
        File prod_file= new File(prod_fileName);
        List<List<String>> prod = new ArrayList<>();
        Scanner prod_inputStream;
        try{
            prod_inputStream = new Scanner(prod_file);

            while(prod_inputStream.hasNext()){
                String prod_line= prod_inputStream.next();
                String[] values = prod_line.split(",");
                prod.add(Arrays.asList(values));
            }
            prod_inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Producer apple[] = new Producer[30];
        for(int i=0;i<prod.size();i++){
            apple[i]= new Producer(Integer.parseInt(prod.get(i).get(0)),prod.get(i).get(1),Integer.parseInt(prod.get(i).get(2)),Integer.parseInt(prod.get(i).get(3)));
        }


        String whole_fileName= "/Users/kowreesh/Downloads/Wholesaler.csv";
        File whole_file= new File(whole_fileName);
        List<List<String>> whole = new ArrayList<>();
        Scanner whole_inputStream;
        try{
            whole_inputStream = new Scanner(whole_file);

            while(whole_inputStream.hasNext()){
                String whole_line= whole_inputStream.next();
                String[] whole_values = whole_line.split(",");
                whole.add(Arrays.asList(whole_values));
            }
            whole_inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        no_of_Wholesaler=whole.size();
        Wholesaler wholesaler[] = new Wholesaler[30];
        for(int i=0;i<whole.size();i++){
            wholesaler[i]= new Wholesaler(Integer.parseInt(whole.get(i).get(0)),whole.get(i).get(1),Integer.parseInt(whole.get(i).get(2)),whole.get(i).get(3),Integer.parseInt(whole.get(i).get(4)));
        }


        String ret_fileName= "/Users/kowreesh/Downloads/Retailer.csv";
        File ret_file= new File(ret_fileName);
        List<List<String>> ret = new ArrayList<>();
        Scanner ret_inputStream;
        try{
            ret_inputStream = new Scanner(ret_file);

            while(ret_inputStream.hasNext()){
                String ret_line= ret_inputStream.next();
                String[] ret_values = ret_line.split(",");
                ret.add(Arrays.asList(ret_values));
            }
            ret_inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        no_of_retailer = ret.size();
        Retailer retailer[] = new Retailer[30];
        for(int i=0;i<ret.size();i++){
            retailer[i]= new Retailer(Integer.parseInt(ret.get(i).get(0)),ret.get(i).get(1),Integer.parseInt(ret.get(i).get(2)),ret.get(i).get(3),Integer.parseInt(ret.get(i).get(4)));
        }



        Scanner sc=new Scanner(System.in);
        boolean check=true;

        while(check) {
            System.out.println("Choose action\n1. View Producer\n2. View Wholesaler\n3. Retailer\n4. Add to wholesaler\n5. Add to Retailer\n6. Exit");
            int role = sc.nextInt();
            switch (role) {
                case 1:
                    for(int i=0;i<prod.size();i++){
                        apple[i].disp_warehouse();
                    }
                    break;
                case 2:
                    for(int i=0;i<no_of_Wholesaler;i++){
                        wholesaler[i].disp_whole_stock();
                    }
                    break;
                case 3:
                    for(int i=0;i<no_of_retailer;i++){
                        retailer[i].disp_ret_stock();
                    }
                    break;
                case 4:
                    System.out.println("Enter Wholesaler name: ");
                    String choose_wholesaler = sc.next();
                    for(int i=0;i<no_of_Wholesaler;i++){
                        System.out.println(choose_wholesaler.getClass().getSimpleName());
                        System.out.println((wholesaler[i].w_name).getClass().getSimpleName());
                        if(choose_wholesaler == wholesaler[i].w_name){
                            System.out.println("hello");
                            System.out.println("Enter product name: ");
                            String choose_prod = sc.next();
                            System.out.println("Enter product quantity: ");
                            int product_qty = sc.nextInt();
                            if(choose_prod == wholesaler[i].prod_name){
                                wholesaler[i].add_stock(product_qty);
                                for(int j=0;j<3;j++){
                                    if(apple[i].name == choose_prod){
                                        apple[i].mang_warehouse(product_qty);
                                    }
                                }
                            }
                            else {
                                wholesaler[i]= new Wholesaler(152,choose_wholesaler,product_qty,choose_prod,0);
                                no_of_Wholesaler=no_of_Wholesaler+1;
                                apple[i].mang_warehouse(product_qty);
                            }
                        }
                    }
                case 5:
                    System.out.println("Enter retailer name: ");
                    String choose_retailer = sc.next();
                    for(int i=0;i<no_of_retailer;i++){
                        System.out.println(choose_retailer.getClass().getSimpleName());
                        System.out.println((retailer[i].r_name).getClass().getSimpleName());
                        if(choose_retailer == retailer[i].r_name){
                            System.out.println("hello");
                            System.out.println("Enter product name: ");
                            String choose_prod = sc.next();
                            System.out.println("Enter product quantity: ");
                            int product_qty = sc.nextInt();
                            if(choose_prod == retailer[i].ret_pname){
                                retailer[i].add_stock(product_qty);
                                for(int j=0;j<no_of_Wholesaler;j++){
                                    if(wholesaler[i].prod_name == choose_prod){
                                        wholesaler[i].mang_whole_stock(product_qty);
                                    }
                                }
                            }
                            else {
                                retailer[i]= new Retailer(152,choose_retailer,product_qty,choose_prod,0);
                                no_of_retailer=no_of_retailer+1;
                                wholesaler[i].mang_whole_stock(product_qty);
                            }
                        }
                    }
                case 6:
                    check = false;
            }
        }

    }
}
