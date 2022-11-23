package com.example.kiemtra3;

public class DanhSach {
    String tenkhoahoc, tenthuonggoi, dactinh, maula, congdung, duoctinh,chuy;
    String id;

    String hinhanh;

    public DanhSach() {

    }

    public DanhSach(String tenkhoahoc, String tenthuonggoi, String dactinh, String maula, String congdung, String duoctinh, String chuy, String hinhanh) {
        this.tenkhoahoc = tenkhoahoc;
        this.tenthuonggoi = tenthuonggoi;
        this.dactinh = dactinh;
        this.maula = maula;
        this.congdung = congdung;
        this.duoctinh = duoctinh;
        this.chuy = chuy;
        this.hinhanh = hinhanh;
    }

    public String getTenkhoahoc() {
        return tenkhoahoc;
    }

    public void setTenkhoahoc(String tenkhoahoc) {
        this.tenkhoahoc = tenkhoahoc;
    }

    public String getTenthuonggoi() {
        return tenthuonggoi;
    }

    public void setTenthuonggoi(String tenthuonggoi) {
        this.tenthuonggoi = tenthuonggoi;
    }

    public String getDactinh() {
        return dactinh;
    }

    public void setDactinh(String dactinh) {
        this.dactinh = dactinh;
    }

    public String getMaula() {
        return maula;
    }

    public void setMaula(String maula) {
        this.maula = maula;
    }

    public String getCongdung() {
        return congdung;
    }

    public void setCongdung(String congdung) {
        this.congdung = congdung;
    }

    public String getDuoctinh() {
        return duoctinh;
    }

    public void setDuoctinh(String duoctinh) {
        this.duoctinh = duoctinh;
    }

    public String getChuy() {
        return chuy;
    }

    public void setChuy(String chuy) {
        this.chuy = chuy;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
