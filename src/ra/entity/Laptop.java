package ra.entity;

import ra.run.Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Laptop {
    private String laptopId, laptopName, description;
    private int ram, typeId;
    private double weight, laptopPrice;
    private Date createAt;
    private boolean isDeleted;

    public Laptop() {
    }

    public Laptop(String laptopId, String laptopName, String description, int ram, int typeId, double weight, double laptopPrice, Date createAt, boolean isDeleted) {
        this.laptopId = laptopId;
        this.laptopName = laptopName;
        this.description = description;
        this.ram = ram;
        this.typeId = typeId;
        this.weight = weight;
        this.laptopPrice = laptopPrice;
        this.createAt = createAt;
        this.isDeleted = isDeleted;
    }

    public String getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(String laptopId) {
        this.laptopId = laptopId;
    }

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLaptopPrice() {
        return laptopPrice;
    }

    public void setLaptopPrice(double laptopPrice) {
        this.laptopPrice = laptopPrice;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    // Phương thức nhập thông tin laptop từ bàn phím
    public void inputData(Scanner scanner) {
        this.laptopId = inputLaptopId(scanner);
        this.laptopName = inputLaptopName(scanner);
        this.description = inputDescription(scanner);
        this.ram = inputRam(scanner);
        this.weight = inputWeight(scanner);
        this.createAt = inputCreateAt(scanner);
        this.laptopPrice = inputLaptopPrice(scanner);
        this.typeId = inputTypeId(scanner);
        this.isDeleted = inputStatus(scanner);
    }

    // Phương thức nhập mã laptop
    public String inputLaptopId(Scanner scanner) {
        String laptopIdRegex = "L\\d{4}";
        System.out.print("Nhập mã laptop: ");
        do {
            String ipLaptopId = scanner.nextLine();
            if (ipLaptopId.trim().isEmpty())
                System.err.print("Mã laptop không được để trống: ");
            else {
                if (Pattern.matches(laptopIdRegex, ipLaptopId)) {
                    boolean isExist = Main.laptopList.stream().anyMatch(laptop -> laptop.getLaptopId().equals(ipLaptopId));
                    if (isExist)
                        System.err.print("Mã laptop này đã tồn tại, nhập lại: ");
                    else
                        return ipLaptopId;
                } else
                    System.err.print("Mã laptop chưa đúng định dạng, nhập lại: ");
            }
        } while (true);
    }

    // Phương thức nhập tên laptop
    public String inputLaptopName(Scanner scanner) {
        System.out.print("Nhập tên laptop: ");
        do {
            String ipLaptopName = scanner.nextLine();
            if (ipLaptopName.trim().isEmpty())
                System.err.print("Tên laptop không được để trống: ");
            else {
                boolean isExist = Main.laptopList.stream().anyMatch(laptop -> laptop.getLaptopName().equalsIgnoreCase(ipLaptopName));
                if (isExist)
                    System.err.print("Tên laptop này đã tồn tại, nhập lại: ");
                else
                    return ipLaptopName;
            }
        } while (true);
    }

    // Phương thức nhập phần mô tả
    public String inputDescription(Scanner scanner) {
        System.out.print("Nhập phần mô tả laptop: ");
        do {
            String ipDescription = scanner.nextLine();
            if (ipDescription.trim().isEmpty())
                System.err.print("Phần mô tả không được để trống: ");
            else
                return ipDescription;
        } while (true);
    }

    public int inputRam(Scanner scanner) {
        System.out.print("Nhập bộ nhớ của máy tính: ");
        do {
            try {
                int ipRam = Integer.parseInt(scanner.nextLine());
                if (ipRam > 0)
                    return ipRam;
                else
                    System.err.print("Ram phải lớn hơn 0: ");
            } catch (NumberFormatException exception) {
                System.out.print("Chưa nhập đúng định dạng số nguyên: ");
            }
        } while (true);
    }

    public double inputWeight(Scanner scanner) {
        System.out.print("Nhập cân nặng máy tính: ");
        do {
            try {
                double ipWeight = Double.parseDouble(scanner.nextLine());
                if (ipWeight > 0)
                    return ipWeight;
                else
                    System.err.print("Cân nặng phải lớn hơn 0: ");

            } catch (NumberFormatException e) {
                System.err.print("Chưa nhập đúng định dạng số: ");
            }
        } while (true);
    }

    public Date inputCreateAt(Scanner scanner) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Nhập ngày mua máy tính: ");
        do {
            try {
                return simpleDateFormat.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.err.print("Ngày chưa đúng định dạng, nhập lại: ");
            }
        } while (true);
    }

    public double inputLaptopPrice(Scanner scanner) {
        System.out.print("Nhập giá tiền của laptop: ");
        do {
            try {
                double ipLaptopPrice = Double.parseDouble(scanner.nextLine());
                if (ipLaptopPrice > 0)
                    return ipLaptopPrice;
                else
                    System.err.print("Giá laptop phải lớn hơn 0: ");
            } catch (NumberFormatException e) {
                System.err.print("Bạn chưa nhập đúng định dạng số: ");
            }
        } while (true);
    }

    public int inputTypeId(Scanner scanner) {
        System.out.println("Danh mục loại laptop: ");
        for (int i = 0; i < Main.laptopTypeList.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), Main.laptopTypeList.get(i).getTypeName());
        }
        System.out.print("Lựa chọn của bạn: ");
        do {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1)
                return Main.laptopTypeList.get(choice - 1).getTypeId();
            else
                System.err.print("Lựa chọn của bạn phải lớn hơn 0: ");
        } while (true);
    }

    // Phương thức nhập trạng thái
    public boolean inputStatus(Scanner scanner) {
        System.out.print("Nhập trạng thái của laptop: ");
        do {
            String ipStatus = scanner.nextLine();
            if (ipStatus.equalsIgnoreCase("true") || ipStatus.equalsIgnoreCase("false"))
                return Boolean.parseBoolean(ipStatus);
            else
                System.err.print("Trạng thái chỉ nhận true || false: ");

        } while (true);
    }

    @Override
    public String toString() {
        return "Mã thể loại: " + typeId + ", mã laptop: " + laptopId + ", tên: " + laptopName +
                ", mô tả: " + description + ", ram: " + ram + ", cân nặng:" + weight +
                ", giá: " + laptopPrice + ", ngày tháng: " + createAt + ", trạng thái:" + isDeleted;
    }
}
