package ra.run;

import ra.entity.Laptop;
import ra.entity.LaptopType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<LaptopType> laptopTypeList = new ArrayList<>();
    public static List<Laptop> laptopList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("******************LAPTOP-MANAGEMENT******************");
            System.out.println("1. Quản lý loại laptop");
            System.out.println("2. Quản lý laptop");
            System.out.println("3. Thoát");

            System.out.print("Lựa chọn của bạn: ");
            int choice = inputChoice(sc.nextLine());
            switch (choice) {
                case 1:
                    laptopTypeMenu();
                    break;
                case 2:
                    laptopMenu();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Chọn mục có trong menu");
            }
        } while (true);

    }

    public static int inputChoice(String choiStr) {
        while (true) {
            try {
                return Integer.parseInt(choiStr);
            } catch (NumberFormatException numberFormatException) {
                System.err.print("Chưa nhập đúng định dạng số nguyên: ");
                choiStr = sc.nextLine();
            }
        }
    }

    // Laptop Type Menu
    public static void laptopTypeMenu() {
        boolean isExit = true;
        do {
            System.out.println("*******************LAPTOP_TYPE-MENU********************");
            System.out.println("1. Hiển thị danh sách các loại laptop");
            System.out.println("2. Thêm mới loại laptop");
            System.out.println("3. Cập nhật thông tin loại laptop");
            System.out.println("4. Xóa loại Laptop");
            System.out.println("5. Thoát");

            System.out.print("Lựa chọn của bạn: ");
            int choice = inputChoice(sc.nextLine());
            switch (choice) {
                case 1:
                    displayLaptopTypeList();
                    break;
                case 2:
                    inputLaptopType();
                    break;
                case 3:
                    updateLaptopTypeInfor();
                    break;
                case 4:
                    deleteLaptopType();
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Chọn mục có trong menu");
            }
        } while (isExit);
    }

    public static void displayLaptopTypeList() {
        if (laptopTypeList.isEmpty()) {
            System.err.println("Chưa có thể loại trong danh sách");
        } else
            laptopTypeList.stream()
                    .filter(laptopType -> !laptopType.isDeleted())
                    .forEach(System.out::println);
    }

    // Nhập thể loại mới
    public static void inputLaptopType() {
        LaptopType laptopType = new LaptopType();
        laptopType.inputData(sc);
        laptopTypeList.add(laptopType);
        System.out.println("Đã thêm thành công thể loại mới");
        System.out.println();
    }

    // Lấy ra chỉ số muốn tìm trong danh sách thể loại
    public static int getIndexTypeLaptop(int ipId) {
        for (int i = 0; i < laptopTypeList.size(); i++) {
            if (laptopTypeList.get(i).getTypeId() == ipId)
                return i;
        }
        return -1;
    }

    // Phương thức cập nhật thông tin thể loại laptop theo mã
    public static void updateLaptopTypeInfor() {
        System.out.print("Nhập mã loại laptop muốn cập nhật: ");
        int ipUpdateId = Integer.parseInt(sc.nextLine());
        int indexUpdate = getIndexTypeLaptop(ipUpdateId);
        boolean isExit = true;
        if (indexUpdate >= 0) {
            System.out.println(laptopTypeList.get(indexUpdate));
            do {
                System.out.println("CHỌN THÔNG TIN MUỐN SỬA");
                System.out.println("1. Cập nhật tên thể loại");
                System.out.println("2. Cập nhật phần mô tả");
                System.out.println("3. Thoát");

                System.out.print("Lựa chọn của bạn: ");
                int choice = inputChoice(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên thể loại: ");
                        laptopTypeList.get(indexUpdate).setTypeName(sc.nextLine());
                        System.out.println("Đã cập nhật thành công");
                        System.out.println();
                        break;
                    case 2:
                        System.out.print("Nhập phần mô tả muốn cập nhật: ");
                        laptopTypeList.get(indexUpdate).setDescription(sc.nextLine());
                        System.out.println("Đã cập nhật thành công");
                        System.out.println();
                        break;
                    case 3:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Chọn mục có trong menu");
                }
            } while (isExit);
        } else
            System.err.println("Mã loại laptop muốn cập nhật không tồn tại");
    }

    // Xoá thể loại laptop theo mã
    public static void deleteLaptopType() {
        System.out.print("Nhập mã loại laptop muốn xoá: ");
        int ipDeleteId = Integer.parseInt(sc.nextLine());
        int indexDelete = getIndexTypeLaptop(ipDeleteId);
        if (indexDelete >= 0) {
            laptopTypeList.get(indexDelete).setDeleted(true);
            laptopTypeList.remove(indexDelete);
            System.out.printf("Đã xoá thành công mã: %d\n" + indexDelete);
        } else
            System.err.println("Mã thể loại muốn xoá không tồn tại");
    }

    public static void laptopMenu() {
        boolean isExit = true;
        do {
            System.out.println("********************LAPTOP-MENU*********************");
            System.out.println("1. Danh sách Laptop");
            System.out.println("2. Thêm mới Laptop");
            System.out.println("3. Cập nhật thông tin Laptop");
            System.out.println("4. Xóa Laptop");
            System.out.println("5. Thống kê số lượng laptop theo từng loại");
            System.out.println("6. Thoát");

            System.out.print("Lựa chọn của bạn: ");
            int choice = inputChoice(sc.nextLine());
            switch (choice) {
                case 1:
                    displayLaptopList();
                    break;
                case 2:
                    inputLaptopInfor();
                    break;
                case 3:
                    updateLaptopInfor();
                    break;
                case 4:
                    deleteLaptop();
                    break;
                case 5:
                    statisticsLatop();
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Chọn mục có trong menu");
            }
        } while (isExit);
    }

    // Hiển thị danh sách laptop
    public static void displayLaptopList() {
        if (laptopList.isEmpty())
            System.err.println("Chưa có laptop trong danh sách");
        else
            laptopList.stream()
                    .filter(laptop -> !laptop.isDeleted())
                    .forEach(System.out::println);
    }

    // Phương thức thêm mới laptop
    public static void inputLaptopInfor() {
        Laptop laptop = new Laptop();
        laptop.inputData(sc);
        laptopList.add(laptop);
        System.out.println("Đã thêm thành công laptop");
        System.out.println(laptop);
    }

    // Lấy ra chỉ số muốn tìm trong danh sách laptop
    public static int getIndexLaptop(int ipId) {
        for (int i = 0; i < laptopList.size(); i++) {
            if (laptopList.get(i).getTypeId() == ipId)
                return i;
        }
        return -1;
    }

    // Cập nhật laptop theo mã
    public static void updateLaptopInfor() {
        System.out.print("Nhập mã laptop muốn cập nhật: ");
        int ipUpdateId = Integer.parseInt(sc.nextLine());
        int indexUpdate = getIndexLaptop(ipUpdateId);
        boolean isExit = true;
        if (indexUpdate >= 0) {
            System.out.println(laptopList.get(indexUpdate));
            do {
                System.out.println("CHỌN THÔNG TIN MUỐN SỬA");
                System.out.println("1. Cập nhật tên laptop");
                System.out.println("2. Cập nhật phần mô tả");
                System.out.println("3. Cập nhật ram");
                System.out.println("4. Cập nhật cân nặng");
                System.out.println("5. Cập nhật ngày tháng");
                System.out.println("6. Cập nhật giá");
                System.out.println("7. Thoát");

                System.out.print("Lựa chọn của bạn: ");
                int choice = inputChoice(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên thể loại: ");
                        laptopList.get(indexUpdate).setLaptopName(sc.nextLine());
                        System.out.println("Đã cập nhật thành công");
                        System.out.println();
                        break;
                    case 2:
                        System.out.print("Nhập phần mô tả muốn cập nhật: ");
                        laptopList.get(indexUpdate).setDescription(sc.nextLine());
                        System.out.println("Đã cập nhật thành công");
                        System.out.println();
                        break;
                    case 3:
                        System.out.print("Cập nhật ram: ");
                        laptopList.get(indexUpdate).setRam(Integer.parseInt(sc.nextLine()));
                        System.out.println("Đã cập nhật thành công");
                        System.out.println();
                        break;
                    case 4:
                        System.out.print("Cập nhật cân nặng: ");
                        laptopList.get(indexUpdate).setWeight(Double.parseDouble(sc.nextLine()));
                        System.out.println("Đã cập nhật thành công");
                        System.out.println();
                        break;
                    case 5:
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                        System.out.print("Cập nhật ngày tháng: ");
                        try {
                            laptopList.get(indexUpdate).setCreateAt(simpleDateFormat.parse(sc.nextLine()));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Đã cập nhật thành công");
                        System.out.println();
                        break;
                    case 6:
                        System.out.print("Cập nhật giá: ");
                        laptopList.get(indexUpdate).setLaptopPrice(Double.parseDouble(sc.nextLine()));
                        System.out.println("Đã cập nhật thành công");
                        System.out.println();
                        break;
                    case 7:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Chọn mục có trong menu");
                }
            } while (isExit);
        } else
            System.err.println("Mã loại laptop muốn cập nhật không tồn tại");
    }

    // Xoá laptop theo mã
    public static void deleteLaptop() {
        System.out.print("Nhập mã laptop muốn xoá: ");
        int ipDeleteId = Integer.parseInt(sc.nextLine());
        int indexDelete = getIndexLaptop(ipDeleteId);
        if (indexDelete >= 0) {
            laptopList.get(indexDelete).setDeleted(true);
            laptopList.remove(indexDelete);
            System.out.printf("Đã xoá thành công mã: %d\n" + indexDelete);
        } else
            System.err.println("Mã thể loại muốn xoá không tồn tại");
    }

    // Thống kê
    public static void statisticsLatop() {
        for (LaptopType laptopType : laptopTypeList) {
            //Thống kê thể loại - số lượng laptop
            System.out.printf("%d - %s: %d laptop\n", laptopType.getTypeId(), laptopType.getTypeName(),
                    laptopList.stream().filter(laptop -> laptop.getTypeId() == laptopType.getTypeId()).count());
        }
    }


}
