package ra.entity;

import ra.run.Main;

import java.util.Comparator;
import java.util.Scanner;

public class LaptopType {
    private int typeId;
    private String typeName, description;
    private boolean isDeleted;

    public LaptopType() {
    }

    public LaptopType(int typeId, String typeName, String description, boolean isDeleted) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.description = description;
        this.isDeleted = isDeleted;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    // Phương thức nhập thông tin cho loại laptop
    public void inputData(Scanner scanner) {
        this.typeId = inputTypeId(scanner);
        this.typeName = inputTypeName(scanner);
        this.description = inputDescription(scanner);
        this.isDeleted = inputStatus(scanner);
    }

    // Phương thức nhập typeId từ bàn phím
    public int inputTypeId(Scanner scanner) {
        if (Main.laptopTypeList.isEmpty())
            return 1;
        int idMax = Main.laptopTypeList.stream().max(Comparator.comparing(LaptopType::getTypeId)).get().getTypeId();
        return idMax;
    }

    // Phương thức nhập typeName từ bàn phím
    public String inputTypeName(Scanner scanner) {
        System.out.print("Nhập tên loại laptop: ");
        do {
            String ipTypeName = scanner.nextLine();
            if (ipTypeName.trim().isEmpty())
                System.err.print("Tên thể loại laptop không được để trống: ");
            else {
                boolean checkName = Main.laptopTypeList.stream().anyMatch(laptopType -> laptopType.getTypeName().equals(ipTypeName));
                if (checkName)
                    System.err.print("Tên thể loại này đã tồn tại, nhập lại: ");
                else
                    return ipTypeName;
            }
        } while (true);
    }

    // Phương thức nhập phần mô tả
    public String inputDescription(Scanner scanner) {
        System.out.print("Nhập phần mô tả cho loại laptop: ");
        do {
            String ipDescription = scanner.nextLine();
            if (ipDescription.trim().isEmpty())
                System.err.print("Phần mô tả không được để trống: ");
            else
                return ipDescription;
        } while (true);
    }

    // Phương thưức nhập trạng thái
    public boolean inputStatus(Scanner scanner) {
        System.out.print("Nhập trạng thái của loại laptop: ");
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
        return "Mã thể loại: " + typeId + ", tên thể loại: " + typeName + ", mô tả: " + description + ", trạng thái: " + isDeleted;
    }
}
