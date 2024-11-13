package tr.producttracking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private int ID;
    private String full_name;
    private String phone_no;
    private String email;
    private LocalDate payment_date;
    private String product_status;
    private String comment;

    public Task(int id, String full_name) {
        this.ID = id;
        this.full_name = full_name;
    }

    public int getID() {
        return ID;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getPayment_date() {
        return payment_date;
    }

    public String getPayment_datetoString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return payment_date.format(formatter);
    }

    public void setPayment_date(LocalDate payment_date) {
        this.payment_date = payment_date;
    }

    public void setPayment_date(String payment_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.payment_date = LocalDate.parse(payment_date, formatter);
    }

    public String getProduct_status() {
        return product_status;
    }

    public void setProduct_status(String product_status) {
        this.product_status = product_status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toCSV() {
        return ID + "," + full_name + "," + phone_no + "," + email + "," + getPayment_datetoString() + "," + product_status + "," + comment;
    }

    @Override
    public String toString() {
        return full_name;
    }
}
