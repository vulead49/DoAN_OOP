import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DSHDT {
    static HoaDonThu[] a;
    int n;

    DSChiTietHDT ct;
    
    Scanner sc = new Scanner(System.in);
    
    public DSHDT() {
        
    }
    
    public DSHDT(int n) {
        this.n = n;
        a = new HoaDonThu[n];
    }
    
    public boolean isIdExist(String id, int x) {
        for(int i = 0; i < x; i++) {
            if(a[i].getMahd().equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isIdExist(String id) {
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(a[i].getMahd().equals(id)) {
                count++;
            }
        }
        if(count == 2) {
            return true;
        }
        return false;
    }

    public void checkId (int i) {
        while(isIdExist(a[i].getMahd(), i)) {
            System.err.println("Ma hoa don vua nhap bi trung voi ma ve khac!!!");
            System.err.println("Nhan Enter de nhap lai!!!");
            sc.nextLine();
            System.out.println("Nhap lai ma hoa don:");
            String id = sc.nextLine();
            a[i].setMahd(id);
        }
    }
    
    public void changeIdKh(String makhCu,String makhMoi) {
        for(int i = 0; i < n; i++) {
            if(a[i].getMakh().equals(makhCu)) {
                a[i].setMakh(makhMoi);
            }
        }
    }
    
    public void nhap() throws IOException{
        System.out.print("Nhap n hoa don dau tien: ");
        n = sc.nextInt();
        sc.nextLine();
        a = new HoaDonThu[n];
        for(int i = 0; i < n; i++){
            a[i]= new HoaDonThu();
            a[i].nhap();
            if(i > 0) {
                checkId(i);
            }
        }
        writeDataToFile();
    }

    public void xuat() {
        System.out.println("=================================-Danh sach hoa don-==================================");
		System.out.format("|| %5s | %10s | %13s | %12s | %10s | %15s ||\n", "Stt", "Ma hoa don", "Ma khach hang", "Ma nhan vien", "Ngay mua", "Tong tien");
        for(int i = 0; i < n; i++) {
            System.out.format("|| %5d |", i + 1);
            a[i].xuat();
        }
        System.out.println("======================================================================================");
    }

    public void them() throws IOException {
        n = a.length;
        a = Arrays.copyOf(a, n + 1);
        a[n] = new HoaDonThu();
        System.out.println("Nhap thong tin hoa don can them: ");
        a[n].nhap();
        checkId(n);
        n++;
        writeDataToFile();
    }

    public void them(HoaDonThu x) throws IOException{
        n = a.length;
        a = Arrays.copyOf(a, n + 1);
        a[n] = new HoaDonThu(x);
        n++;
        checkId(n);
        writeDataToFile();
    }
    
    public void sua() throws IOException{
        System.out.print("Nhap ma hoa don can duoc sua: ");
        String id = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        for (int i = 0; i < n; i++) {
            if(a[i].getMahd().equals(id)) {
                isExisted = true;
                a[i].nhap();
                while(isIdExist(a[i].getMahd(), i)) {
                    System.err.println("Ma hoa don vua nhap bi trung voi ma ve khac!!!");
                    System.err.println("Nhan Enter de nhap lai!!!");
                    sc.nextLine();
                    System.out.println("Nhap lai ma hoa don:");
                    String ma = sc.nextLine();
                    a[i].setMahd(ma);
                }
                ct.readDataFromFile();
                ct.changeIdHd(id, a[i].getMahd());
                ct.writeDataToFile();
                writeDataToFile();
                break;
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma hoa don!");
    }

    public void sua(String id) throws IOException{
        boolean isExisted = false;
        n = a.length;
        for (int i = 0; i < n; i++) {
            if(a[i].getMahd().equals(id)) {
                isExisted = true;
                a[i].nhap();
                while(isIdExist(a[i].getMahd(), i)) {
                    System.err.println("Ma hoa don vua nhap bi trung voi ma ve khac!!!");
                    System.err.println("Nhan Enter de nhap lai!!!");
                    sc.nextLine();
                    System.out.println("Nhap lai ma hoa don:");
                    String ma = sc.nextLine();
                    a[i].setMahd(ma);
                }
                ct.readDataFromFile();
                ct.changeIdHd(id, a[i].getMahd());
                ct.writeDataToFile();
                writeDataToFile();
                break;
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma hoa don!");
    }
    
    public void timkiem(){
        System.out.print("Nhap ma hoa don muon tim: ");
        String ma = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        for (int i = 0; i < n; i++) {
            if(a[i].getMahd().equals(ma)) {
                isExisted = true;
                System.out.println("Thong tin hoa don can tim: ");
                a[i].xuat();
                break;
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma hoa don!");
    }
    
    public int timkiemma(String ma){
        n = a.length;
        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if(a[i].getMahd().equals(ma)) {
                vitri = i;
                break;
            }
        }
        return vitri;
    }
    
    public HoaDonThu timkiem(String ma){
        n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i].getMahd().equals(ma)) {
                return a[i];
            }
        }  
        return null;
    }
    
    public void timkiemkh(){
        System.out.print("Nhap ma khach hang co hoa don muon tim: ");
        String makh = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        for(int i = 0; i < n; i++) {
            if (a[i].getMakh().equals(makh)) {
                isExisted = true;
                a[i].xuat();
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma khach hang!");
    }

    public void timkiemkh(String makh){
        boolean isExisted = false;
        n = a.length;
        for(int i = 0; i < n; i++) {
            if (a[i].getMakh().equals(makh)) {
                isExisted = true;
                a[i].xuat();
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma khach hang!");
    }

    public void timkiemnv(){
        System.out.print("Nhap ma nhan vien thu tien hoa don muon tim: ");
        String manv = sc.nextLine();
        boolean isExisted = false;
        n = a.length;
        for(int i = 0; i < n; i++) {
            if (a[i].getManv().equals(manv)) {
                isExisted = true;
                a[i].xuat();
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma nhan vien!");
    }

    public void timkiemnv(String manv) {
        boolean isExisted = false;
        n = a.length;
        for(int i = 0; i < n; i++) {
            if (a[i].getManv().equals(manv)) {
                isExisted = true;
                a[i].xuat();
            }
        }
        if(!isExisted) 
            System.out.println("Khong tim thay ma nhan vien!");
    }

    private Date changeDate(String dateString) {
        String pattern = "dd/MM/yyyy"; 
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void swapDate(String date1, String date2) {
        if(changeDate(date1).compareTo(changeDate(date2)) > 0) {
            String temp = date1;
            date1 = date2;
            date2 = temp;
        }
    }

    public void timkiemtheongay() {
        System.out.println("Nhap khung thoi gian muon xuat hoa don: ");
        System.out.println("Nhap thoi gian dau: ");
        String time1 = sc.nextLine();
        NgayThangNam value = new KiemTraDinhDang("dd/MM/yyyy");
        while(!value.Check(time1)) {
            System.err.println("Dinh dang cua ngay phai la dd/MM/yyyy!!!");
            System.err.println("Nhan Enter de nhap lai!!!");
            sc.nextLine();
            System.out.println("Nhap lai ngay mua: ");
            time1 = sc.nextLine();
        }
        System.out.println("Nhap thoi gian sau: ");
        String time2 = sc.nextLine();
        while(!value.Check(time2)) {
            System.err.println("Dinh dang cua ngay phai la dd/MM/yyyy!!!");
            System.err.println("Nhan Enter de nhap lai!!!");
            sc.nextLine();
            System.out.println("Nhap lai ngay mua: ");
            time2 = sc.nextLine();
        }
        swapDate(time1, time2);
        n = a.length;
        for(int i = 0; i < n; i++) {
            if(changeDate(a[i].getNgaymua()).compareTo(changeDate(time1)) > 0 && changeDate(a[i].getNgaymua()).compareTo(changeDate(time2)) < 0) {
                a[i].xuat();
            }
        }
    }

    public void timkiemtheogia() {
        System.out.println("Nhap khung gia muon tim: ");
        int gia1 = sc.nextInt();
        int gia2 = sc.nextInt();
        sc.nextLine();
        n = a.length;
        for(int i = 0; i < n; i++) {
            if(a[i].getTonggia() > gia1 && a[i].getTonggia() < gia2) {
                a[i].xuat();
            }
        }
    }

    public void xoa(){
        System.out.print("Nhap ma hoa don muon xoa: ");
        String id = sc.nextLine();
        n = a.length;
        int vitri = timkiemma(id);
        if(vitri == -1) 
            System.out.println("Khong tim thay ma hoa don!");
        else {
            a = Arrays.copyOf(a, n + 2);
            for(int i = vitri; i < n; i++) {
                a[i] = a[i + 1];
            }
            a = Arrays.copyOf(a, n - 1);
            n--;
        }
    }
    
    public void xoa(String id){
        n = a.length;
        int vitri = timkiemma(id);
        if(vitri == -1) 
            System.out.println("Khong tim thay ma hoa don!");
        else {
            a = Arrays.copyOf(a, n + 2);
            for(int i = vitri; i < n; i++) {
                a[i] = a[i + 1];
            }
            a = Arrays.copyOf(a, n - 1);
            n--;
        }
    }

    public void thongkecacquy() {
        System.out.println("Nhap nam can thong ke: ");
        String nam = sc.nextLine();
        int sum1 = 0 , sum2 = 0, sum3 = 0, sum4 = 0;
        for(int i = 0; i < n; i++) {
            if(changeDate(a[i].getNgaymua()).compareTo(changeDate("01/01/" + nam)) > 0 && changeDate(a[i].getNgaymua()).compareTo(changeDate("31/03/" + nam)) < 0) {
                sum1 += a[i].getTonggia();
            }
            if(changeDate(a[i].getNgaymua()).compareTo(changeDate("01/04/" + nam)) > 0 && changeDate(a[i].getNgaymua()).compareTo(changeDate("30/06/" + nam)) < 0) {
                sum2 += a[i].getTonggia();
            }
            if(changeDate(a[i].getNgaymua()).compareTo(changeDate("01/07/" + nam)) > 0 && changeDate(a[i].getNgaymua()).compareTo(changeDate("30/09/" + nam)) < 0) {
                sum3 += a[i].getTonggia();
            }
            if(changeDate(a[i].getNgaymua()).compareTo(changeDate("01/10/" + nam)) > 0 && changeDate(a[i].getNgaymua()).compareTo(changeDate("31/12/" + nam)) < 0) {
                sum4 += a[i].getTonggia();
            }
        }
        System.out.println("Tong tien thu quy 1 nam " + nam + ": " + sum1);
        System.out.println("Tong tien thu quy 2 nam " + nam + ": " + sum2);
        System.out.println("Tong tien thu quy 3 nam " + nam + ": " + sum3);
        System.out.println("Tong tien thu quy 4 nam " + nam + ": " + sum4);
    }

    public void writeDataToFile() throws IOException {
        n = a.length;
        DataOutputStream out = new DataOutputStream(new FileOutputStream("doan/dataHdt.txt"));
        for(int i = 0; i < n; i++) {
            out.writeUTF(a[i].getMahd());
            out.writeUTF(a[i].getMakh());
            out.writeUTF(a[i].getNgaymua());
            out.writeUTF(a[i].getManv());
        }
        out.close();
    }

    public void readDataFromFile() {
        a = new HoaDonThu[500];
        int i = 0;
        try {
            DataInputStream in = new DataInputStream(new FileInputStream("doan/dataHdt.txt"));
            try {
                while(true) {
                    a[i] = new HoaDonThu();
                    a[i].setMahd(in.readUTF());
                    a[i].setMakh(in.readUTF());
                    a[i].setNgaymua(in.readUTF());
                    a[i].setManv(in.readUTF());
                    i++;
                }
            } catch (EOFException e) {

            } finally {
                n = i;
                a = Arrays.copyOf(a, n);
                in.close();
            }
        } catch (IOException e) {
    
        }
    }

//Menu quan li

public void showMenu() {
    System.out.println("============-Option-===========");
    System.out.println("||     1. Them hoa don.      ||");
    System.out.println("||      2. Xoa hoa don.      ||");
    System.out.println("|| 3. Sua thong tin hoa don. ||");
    System.out.println("||   4. Tim kiem hoa don.    ||");
    System.out.println("|| 5. Xem danh sach hoa don. ||");
    System.out.println("||         0. Thoat.         ||");
    System.out.println("===============================");
}
public void showMenutimkiem(){
    System.out.println("==================-Option-================");
    System.out.println("||      1. Tim kiem theo ma hoa don.    ||");
    System.out.println("||     2. Tim kiem theo ma khach hang.  ||");
    System.out.println("||    3. Tim kiem theo ma nhan vien.    ||");
    System.out.println("||     4. Tim kiem theo khung gia.      ||");
    System.out.println("||   5. Tim kiem theo khung thoi gian.  ||");
    System.out.println("||              0. Thoat.               ||");
    System.out.println("==========================================");
    System.out.print("Choose: ");
}

public void Menutimkiem(){
    String choose = null;
    boolean exit = false;
    showMenutimkiem();
    while (true) {
        choose = sc.nextLine();
        switch (choose) {
        case "1":
            timkiem();
            break;
        case "2":
            timkiemkh();
            break;
        case "3":
            timkiemnv();
            break;
        case "4":
            timkiemtheogia();
            break;
        case "5":
            timkiemtheongay();
            break;
        case "0":
            System.out.println("Da thoat!");
            exit = true;
            break;
        default:
            System.err.println("Loi! Hay chon lai:");
            break;
        }
        if (exit) {
            break;
        }
        showMenutimkiem();
    }  
}

public void Menu() throws IOException {
    String choose = null;
    boolean exit = false;
    showMenu();
    while (true) {
        choose = sc.nextLine();
        switch (choose) {
        case "1":
            them();
            break;
        case "2":
            xoa();
            break;
        case "3":
            sua();
            break;
        case "4":
            Menutimkiem();
            break;
        case "5":
            xuat();
            break;
        case "0":
            System.out.println("Da thoat!");
            exit = true;
            break;
        default:
            System.err.println("Loi! Hay chon lai:");
            break;
        }
        if (exit) {
            break;
        }
        showMenu();
    }
}
}
