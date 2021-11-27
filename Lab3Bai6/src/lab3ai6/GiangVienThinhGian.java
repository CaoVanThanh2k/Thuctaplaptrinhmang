/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3ai6;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class GiangVienThinhGian extends GiangVien {
     private String coQuanLamViec;         
     @Override     
     public GiangVien inputq() {        
         super.input();         
         Scanner sc=new Scanner(System.in);         
         System.out.println("Nhap vao co quan lam viec :");         
         coQuanLamViec=sc.nextLine();              
         return this;     
     }     
     @Override     
     public void displayq() {     
         super.display();         
         System.out.println("co quan lam viec :"+coQuanLamViec); 
     }     
     @Override     
     public void tinhluong() {        
         float luong;             
         luong=this.getSoGioGiang()*200000;                      
         this.setTongLuong(luong);     
     }
}
